package com.lucasDev.auth.controller;

import com.lucasDev.auth.dto.UsuarioDTO;
import com.lucasDev.auth.model.RolTipo;
import com.lucasDev.auth.model.Usuario;
import com.lucasDev.auth.service.serviceImpl.UsuarioServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;

    public UsuarioController(UsuarioServiceImpl usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Necesito un método que asigne un rol a un usuario
    @PostMapping("/{id}/roles/{tipo}")
    public ResponseEntity<Usuario> asignarRol(@PathVariable Long id, @PathVariable RolTipo tipo) {
        Usuario usuarioNuevo = usuarioService.asignarRolUsuario(id, tipo);

        return ResponseEntity.ok(usuarioNuevo);
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody @Valid UsuarioDTO usuario) {
        return ResponseEntity.ok("Usuario "+usuario.getUsuario()+" registrado.");
    }
}
