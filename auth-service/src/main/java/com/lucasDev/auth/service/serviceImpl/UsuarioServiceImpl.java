package com.lucasDev.auth.service.serviceImpl;

import com.lucasDev.auth.model.RolTipo;
import com.lucasDev.auth.model.Usuario;
import com.lucasDev.auth.repository.RolRepository;
import com.lucasDev.auth.repository.UsuarioRepository;
import com.lucasDev.auth.service.UsuarioService;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    @Override
    public Usuario asignarRolUsuario(Long idUsuario, RolTipo tipoRol) {

        //Encontrar un usuario por su id
        Usuario usuario = usuarioRepository.getUsuarioById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        //Asignamos roles directamente con su tipo
        usuario.addRol(tipoRol);

        return usuarioRepository.save(usuario);
    }


}
