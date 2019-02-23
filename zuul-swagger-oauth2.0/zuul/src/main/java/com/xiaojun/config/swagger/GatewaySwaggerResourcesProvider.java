package com.xiaojun.config.swagger;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import springfox.documentation.schema.ClassSupport;
import springfox.documentation.service.Documentation;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.*;

/**
 * @author xiaojun
 * @date 2019/2/22 23:58
 */
@Component
@Primary
public class GatewaySwaggerResourcesProvider implements SwaggerResourcesProvider {


    private final String swagger1Url;
    private final String swagger2Url;
    @VisibleForTesting
    boolean swagger1Available;
    @VisibleForTesting
    boolean swagger2Available;
    private final DocumentationCache documentationCache;

    private final RouteLocator routeLocator;

    @Autowired
    public GatewaySwaggerResourcesProvider(Environment environment, DocumentationCache documentationCache,RouteLocator routeLocator) {
        this.swagger1Url = environment.getProperty("springfox.documentation.swagger.v1.path", "/api-docs");
        this.swagger2Url = environment.getProperty("springfox.documentation.swagger.v2.path", "/v2/api-docs");
        this.swagger1Available = ClassSupport.classByName("springfox.documentation.swagger1.web.Swagger1Controller").isPresent();
        this.swagger2Available = ClassSupport.classByName("springfox.documentation.swagger2.web.Swagger2Controller").isPresent();
        this.documentationCache = documentationCache;
        this.routeLocator =routeLocator;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList();
        Iterator var2 = this.documentationCache.all().entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry<String, Documentation> entry = (Map.Entry)var2.next();
            String swaggerGroup = (String)entry.getKey();
            SwaggerResource swaggerResource;
            if (this.swagger1Available) {
                swaggerResource = this.resource(swaggerGroup, this.swagger1Url);
                swaggerResource.setSwaggerVersion("1.2");
                resources.add(swaggerResource);
            }

            if (this.swagger2Available) {
                swaggerResource = this.resource(swaggerGroup, this.swagger2Url);
                swaggerResource.setSwaggerVersion("2.0");
                resources.add(swaggerResource);
            }
        }
        List<Route> routes = routeLocator.getRoutes();
        routes.forEach(route -> resources.add(swaggerResource(route.getId(), route.getFullPath().replace("**", "v2/api-docs"))));
        Collections.sort(resources);
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }

    private SwaggerResource resource(String swaggerGroup, String baseUrl) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(swaggerGroup);
        swaggerResource.setUrl(this.swaggerLocation(baseUrl, swaggerGroup));
        return swaggerResource;
    }

    private String swaggerLocation(String swaggerUrl, String swaggerGroup) {
        String base = (String)Optional.of(swaggerUrl).get();
        return "default".equals(swaggerGroup) ? base : base + "?group=" + swaggerGroup;
    }

}
