package com.RetroVideoManager.Peliculas.Config;
import com.RetroVideoManager.Peliculas.DTO.ValidateResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private WebClient autenticacionClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        if ("GET".equals(method) || "OPTIONS".equals(method)) {
            return true;
        }

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Token requerido\"}");
            return false;
        }

        String token = authHeader.substring(7);
        try {
            ValidateResponseDTO validation = autenticacionClient.get()
                    .uri("/api/auth/validate?token=" + token)
                    .retrieve()
                    .bodyToMono(ValidateResponseDTO.class)
                    .block();

            if (validation == null || !validation.isValid()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"Token inválido o expirado\"}");
                return false;
            }

            request.setAttribute("username", validation.getUsername());
            request.setAttribute("rol", validation.getRol());
            return true;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"No se pudo validar el token\"}");
            return false;
        }
    }

}
