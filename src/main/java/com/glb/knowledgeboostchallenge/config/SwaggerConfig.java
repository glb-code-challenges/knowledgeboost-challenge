package com.glb.knowledgeboostchallenge.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.AuthorizationScopeBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.glb.knowledgeboostchallenge.controller"))
            .paths(PathSelectors.any())
            .build()
            .securitySchemes(Arrays.asList(new ApiKey("Authorization-Key", "apiKey", "header")))
            .securityContexts(getSecurityContexts())
            .apiInfo(apiInfo());
    }

    private List<SecurityContext> getSecurityContexts() {
        AuthorizationScope[] authScopes = new AuthorizationScope[1];
        authScopes[0] = new AuthorizationScopeBuilder().scope("global").description("full access").build();
        SecurityReference securityReference = SecurityReference.builder().reference("Authorization-Key")
            .scopes(authScopes).build();

        return Collections.singletonList(
            SecurityContext.builder()
                .securityReferences(Collections.singletonList(securityReference))
                .build());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
            "Knowledge Boost API",
            "This is a microservice that retrieves the weather of a particular city. The data is from https://openweathermap.org/",
            "1",
            "Terms of service",
            new Contact("Monse Pluma", "www.globant.com", "rosario.pluma@globant.com"),
            "License of API", "API license URL", Collections.emptyList());
    }

}
