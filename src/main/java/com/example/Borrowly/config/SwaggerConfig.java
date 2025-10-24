package com.example.Borrowly.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI borrowlyAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Borrowly API")
                        .version("1.0")
                        .description("API documentation for Borrowly application"));
    }
}
