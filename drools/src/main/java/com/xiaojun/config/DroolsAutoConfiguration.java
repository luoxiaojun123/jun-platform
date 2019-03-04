package com.xiaojun.config;

import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * @author long.luo
 * @date 2018/9/17 16:47
 */
@Configuration
public class DroolsAutoConfiguration {

    private static final String DRL_FILE = "rules/";


    @Bean
    @ConditionalOnMissingBean(KieContainer.class)
    public KieContainer kieContainer() throws IOException{

        final KieRepository kieRepository = getKieServices().getRepository();

        kieRepository.addKieModule(() -> kieRepository.getDefaultReleaseId());

        KieBuilder kieBuilder = getKieServices().newKieBuilder(kieFileSystem());
        kieBuilder.buildAll();

        return getKieServices().newKieContainer(kieRepository.getDefaultReleaseId());
    }

    @Bean
    @ConditionalOnMissingBean(KieFileSystem.class)
    public KieFileSystem kieFileSystem() throws IOException {

        KieFileSystem kieFileSystem = getKieServices().newKieFileSystem();
        for (Resource file : getRuleFiles()) {
            kieFileSystem.write(ResourceFactory.newClassPathResource(DRL_FILE + file.getFilename(), "UTF-8"));
        }
        return kieFileSystem;
    }

    private Resource[] getRuleFiles() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        return resourcePatternResolver.getResources("classpath*:" + DRL_FILE + "**/*.*");
    }

    private KieServices getKieServices() {
        return KieServices.Factory.get();
    }
}
