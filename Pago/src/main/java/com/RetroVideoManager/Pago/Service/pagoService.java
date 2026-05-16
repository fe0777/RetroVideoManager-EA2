package com.RetroVideoManager.Pago.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.transaction.Transactional;
import com.RetroVideoManager.Pago.DTO.PeliculaClientDTO;
import com.RetroVideoManager.Pago.DTO.UsuarioClientDTO;
import com.RetroVideoManager.Pago.DTO.ValidateResponseDTO;
import com.RetroVideoManager.Pago.DTO.pagoRequestDTO;
import com.RetroVideoManager.Pago.DTO.pagoResponseDTO;
import com.RetroVideoManager.Pago.Repository.pagoRepository;
import com.RetroVideoManager.Pago.model.PagoModel;

import reactor.core.publisher.Mono;

@Service
@Transactional
public class pagoService {

    @Autowired
    private pagoRepository pagoRepository;

    @Autowired
    @Qualifier("autenticacionClient")
    private WebClient autenticacionClient;

    @Autowired
    @Qualifier("peliculasClient")
    private WebClient peliculasClient;

    @Autowired
    @Qualifier("usuariosClient")
    private WebClient usuariosClient;

    public List<pagoResponseDTO> findAll() {
        return pagoRepository.findAll().stream().map(this::toResponseDTO).toList();
    }

    public pagoResponseDTO findById(Integer id) {
        return pagoRepository.findById(id.longValue()).map(this::toResponseDTO).orElse(null);
    }

    public void deleteById(Integer id) {
        pagoRepository.deleteById(id.longValue());
    }

    public pagoResponseDTO save(pagoRequestDTO request, String token) {
        // 1. Validar JWT → obtener username
        String rawToken = token.startsWith("Bearer ") ? token.substring(7) : token;
        ValidateResponseDTO validation = autenticacionClient.get()
                .uri("/api/auth/validate?token=" + rawToken)
                .retrieve()
                .onStatus(HttpStatusCode::isError, resp -> Mono.error(new RuntimeException("Token inválido")))
                .bodyToMono(ValidateResponseDTO.class)
                .block();

        if (validation == null || !validation.isValid()) {
            throw new RuntimeException("Token inválido o expirado");
        }

        // 2. Buscar perfil del usuario en Usuarios por username
        UsuarioClientDTO usuario = usuariosClient.get()
                .uri("/api/v1/usuarios/by-username/" + validation.getUsername())
                .retrieve()
                .onStatus(HttpStatusCode::isError, resp -> Mono.error(new RuntimeException("Perfil de usuario no encontrado")))
                .bodyToMono(UsuarioClientDTO.class)
                .block();

        if (usuario == null) {
            throw new RuntimeException("Perfil de usuario no encontrado para: " + validation.getUsername());
        }

        // 3. Verificar que la película existe
        PeliculaClientDTO pelicula = peliculasClient.get()
                .uri("/peliculas/" + request.getPeliculaId())
                .retrieve()
                .onStatus(HttpStatusCode::isError, resp -> Mono.error(new RuntimeException("Película no encontrada")))
                .bodyToMono(PeliculaClientDTO.class)
                .block();

        if (pelicula == null) {
            throw new RuntimeException("Película no encontrada con id: " + request.getPeliculaId());
        }

        // 4. Guardar el pago
        PagoModel pago = new PagoModel();
        pago.setPeliculaId(request.getPeliculaId());
        pago.setUsuarioId(usuario.getId());
        pago.setUsername(validation.getUsername());
        pago.setMetodoPago(request.getMetodoPago());
        pago.setMonto(request.getMonto());
        pago.setFechaPago(LocalDate.now().toString());
        pago.setEstadoPago("Pendiente");
        pago.setNroTarjeta(request.getNroTarjeta());

        PagoModel saved = pagoRepository.save(pago);

        pagoResponseDTO response = toResponseDTO(saved);
        response.setPeliculaTitulo(pelicula.getTitulo());
        response.setUsuarioNombres(
                usuario.getNombres() != null
                        ? usuario.getNombres() + (usuario.getApellidos() != null ? " " + usuario.getApellidos() : "")
                        : usuario.getUsername());
        return response;
    }

    public pagoResponseDTO update(Integer id, pagoRequestDTO request) {
        return pagoRepository.findById(id.longValue()).map(existing -> {
            existing.setMetodoPago(request.getMetodoPago());
            existing.setMonto(request.getMonto());
            existing.setNroTarjeta(request.getNroTarjeta());
            return toResponseDTO(pagoRepository.save(existing));
        }).orElse(null);
    }

    private pagoResponseDTO toResponseDTO(PagoModel pago) {
        pagoResponseDTO response = new pagoResponseDTO();
        response.setIdPago(pago.getIdPago());
        response.setPeliculaId(pago.getPeliculaId());
        response.setUsuarioId(pago.getUsuarioId());
        response.setUsername(pago.getUsername());
        response.setMetodoPago(pago.getMetodoPago());
        response.setMonto(pago.getMonto());
        response.setFechaPago(pago.getFechaPago());
        response.setEstadoPago(pago.getEstadoPago());
        response.setNroTarjeta(pago.getNroTarjeta());
        return response;
    }
}