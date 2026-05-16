package com.RetroVideoManager.Peliculas.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Value("${autenticacion.service.url}")
    private String autenticacionServiceUrl;

    @Bean
    public WebClient autenticacionClient() {
        return WebClient.builder()
                .baseUrl(autenticacionServiceUrl)
                .build();
    }
}
