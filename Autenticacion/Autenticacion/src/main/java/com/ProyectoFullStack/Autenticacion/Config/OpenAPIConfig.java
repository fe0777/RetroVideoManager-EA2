package com.ProyectoFullStack.Autenticacion.Config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI autenticacionOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Autenticación - RetroVideoManager")
                        .description("Microservicio de autenticación y gestión de usuarios (JWT) de RetroVideoManager")
                        .version("v1")
                        .contact(new Contact()
                                .name("RetroVideoManager")
                                .email("ashranka@rosaagustina.cl"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Introduce el token JWT obtenido en /api/auth/login")));
    }
}
