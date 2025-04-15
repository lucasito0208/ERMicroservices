package com.lucasDev.auth.service;


import com.lucasDev.auth.model.RolTipo;
import com.lucasDev.auth.model.Usuario;

public interface UsuarioService {

    Usuario asignarRolUsuario(Long idUsuario, RolTipo tipoRol);

}
