package com.xiaojun.authority;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义 FilterInvocationSecurityMetadataSource
 *
 * @author long.luo
 * @date 2018-07-31
 */
public class AppFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    public AppFilterInvocationSecurityMetadataSource(FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource) {
        this.filterInvocationSecurityMetadataSource = filterInvocationSecurityMetadataSource;
    }

    /**
     * 这里的需要从DB加载
     */
    private final Map<String, String> urlRoleMap = new HashMap<String, String>() {{
        put("/info/**", "ROLE_ADMIN");
    }};


    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) object;
        String url = fi.getRequestUrl();

        for (Map.Entry<String, String> entry : urlRoleMap.entrySet()) {

            if (antPathMatcher.match(entry.getKey(), url)) {
                return SecurityConfig.createList(entry.getValue());
            }
        }
        return filterInvocationSecurityMetadataSource.getAttributes(object);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
