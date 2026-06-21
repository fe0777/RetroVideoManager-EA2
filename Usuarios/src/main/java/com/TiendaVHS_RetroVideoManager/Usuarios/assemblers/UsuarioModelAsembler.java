package com.TiendaVHS_RetroVideoManager.Usuarios.assemblers;

import org.springframework.stereotype.Component;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import com.TiendaVHS_RetroVideoManager.Usuarios.DTO.UsuarioResponseDTO;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import com.TiendaVHS_RetroVideoManager.Usuarios.Controller.ControllerUsuariosV2;

@Component
public class UsuarioModelAsembler implements RepresentationModelAssembler<UsuarioResponseDTO, EntityModel<UsuarioResponseDTO>> {

    @Override
    public EntityModel<UsuarioResponseDTO> toModel(UsuarioResponseDTO usuario) {
        return EntityModel.of(usuario,
            linkTo(methodOn(ControllerUsuariosV2.class).buscar(usuario.getId())).withSelfRel(),
            linkTo(methodOn(ControllerUsuariosV2.class).listar()).withRel("usuarios"),
            linkTo(methodOn(ControllerUsuariosV2.class).buscarPorUsername(usuario.getUsername())).withRel("by-username")
        );
    }
}