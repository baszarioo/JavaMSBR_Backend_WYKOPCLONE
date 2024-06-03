package com.b1lbudinhox.wykopclone.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI blogAPI() {
        return new OpenAPI()
                .info(new Info().title("Wykop Clong blog API")
                        .description("API for wykop clone app")
                        .version("v0.0.1 alpha")
                        .license(new License().name("Apache License Version 2.0")
                                 .url("github@baszarioo")));
    }
}
