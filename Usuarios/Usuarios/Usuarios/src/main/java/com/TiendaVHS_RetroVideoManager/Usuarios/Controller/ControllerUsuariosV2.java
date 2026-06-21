package com.TiendaVHS_RetroVideoManager.Usuarios.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.TiendaVHS_RetroVideoManager.Usuarios.Service.ServiceUsuarios;
import com.TiendaVHS_RetroVideoManager.Usuarios.assemblers.UsuarioModelAsembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.TiendaVHS_RetroVideoManager.Usuarios.DTO.UsuarioInternalRegisterDTO;
import com.TiendaVHS_RetroVideoManager.Usuarios.DTO.UsuarioRequestDTO;
import com.TiendaVHS_RetroVideoManager.Usuarios.DTO.UsuarioResponseDTO;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/usuarios")
public class ControllerUsuariosV2 {
    @Autowired
    private ServiceUsuarios serviceUsuarios;

    @Autowired
    private UsuarioModelAsembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<UsuarioResponseDTO>> listar() {
        List<EntityModel<UsuarioResponseDTO>> usuarios = serviceUsuarios.findAll().stream()
                .map(assembler::toModel)
                .toList();
        return CollectionModel.of(usuarios, linkTo(methodOn(ControllerUsuariosV2.class).listar()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<UsuarioResponseDTO>> buscar(@PathVariable Long id) {
        UsuarioResponseDTO usuario = serviceUsuarios.findById(id);
        if (usuario == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(assembler.toModel(usuario));
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<EntityModel<UsuarioResponseDTO>> buscarPorUsername(@PathVariable String username) {
        return serviceUsuarios.findByUsername(username)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** Endpoint interno: Autenticación lo llama al registrar un nuevo usuario */
    @PostMapping("/internal/register")
    public ResponseEntity<EntityModel<UsuarioResponseDTO>> registroInterno(@RequestBody UsuarioInternalRegisterDTO dto) {
        EntityModel<UsuarioResponseDTO> model = assembler.toModel(serviceUsuarios.createFromAuth(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }
@PutMapping("/{id}")
    public ResponseEntity<EntityModel<UsuarioResponseDTO>> actualizar(@PathVariable Long id,
            @RequestBody UsuarioRequestDTO usuario) {
        UsuarioResponseDTO actualizado = serviceUsuarios.update(id, usuario);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            serviceUsuarios.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /// Otros endpoints específicos de la versión 2 podrían ir aquí
    @GetMapping("/by-email/{email}")
    public ResponseEntity<EntityModel<UsuarioResponseDTO>> buscarPorEmail(@PathVariable String email) {
        return serviceUsuarios.findByEmail(email)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-run/{run}")
    public ResponseEntity<EntityModel<UsuarioResponseDTO>> buscarPorRun(@PathVariable String run) {
        return serviceUsuarios.findByRun(run)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
