package com.example.mockservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI mockServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Mock Service API")
                        .description("A Spring Boot application for creating and managing HTTP mocks using WireMock and MongoDB")
                        .version("v0.0.1")
                        .contact(new Contact()
                                .name("Mock Service")
                                .email("support@example.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}

