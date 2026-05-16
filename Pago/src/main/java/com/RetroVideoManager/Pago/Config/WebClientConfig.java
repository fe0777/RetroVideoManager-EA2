package com.RetroVideoManager.Pago.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${services.autenticacion.url}")
    private String autenticacionUrl;

    @Value("${services.peliculas.url}")
    private String peliculasUrl;

    @Value("${services.usuarios.url}")
    private String usuariosUrl;

    @Bean
    public WebClient autenticacionClient() {
        return WebClient.builder().baseUrl(autenticacionUrl).build();
    }

    @Bean
    public WebClient peliculasClient() {
        return WebClient.builder().baseUrl(peliculasUrl).build();
    }

    @Bean
    public WebClient usuariosClient() {
        return WebClient.builder().baseUrl(usuariosUrl).build();
    }
}
