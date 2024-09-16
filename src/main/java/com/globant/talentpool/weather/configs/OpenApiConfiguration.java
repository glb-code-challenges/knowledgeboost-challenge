package com.globant.talentpool.weather.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Demo Weather API ")
                        .description("Globant Demo Project")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Omar J. Ramírez")
                                .email("omarjonathan.ramirez@globant.com"))
                );
    }
}
