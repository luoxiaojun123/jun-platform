package com.xiaojun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.util.Arrays;
import java.util.List;

/**
 * @author long.luo
 * @date 2018/8/7 18:04
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .anyRequest()
                .and()
                .authorizeRequests()
                .accessDecisionManager(accessDecisionManager())
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                        fsi.setSecurityMetadataSource(mySecurityMetadataSource());
                        return fsi;
                    }
                })
                .antMatchers("/oauth/*").permitAll();
    }

    @Bean
    public AppFilterInvocationSecurityMetadataSource mySecurityMetadataSource() {
        AppFilterInvocationSecurityMetadataSource appFilterInvocationSecurityMetadataSource =
                new AppFilterInvocationSecurityMetadataSource();
        return appFilterInvocationSecurityMetadataSource;
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<? extends Object>> decisionVoters
                = Arrays.asList(
                new WebExpressionVoter(),
                new RoleVoter());
        return new UnanimousBased(decisionVoters);
    }
}
