package com.example.PlexiBook.inventoryservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI inventoryServiceApi()
    {
        return new OpenAPI().info(new io.swagger.v3.oas.models.info.Info()
                .title("Inventory Service Api")
                .description("Inventory Service Api for PlexiBook")
                .version("v1.0.0"));
    }
}
