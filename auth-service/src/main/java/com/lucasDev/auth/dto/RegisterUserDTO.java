package com.lucasDev.auth.dto;

import com.lucasDev.auth.model.RolTipo;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class RegisterUserDTO {
    private String usuario;
    private String email;
    private String password;
    private Set<RolTipo> roles;
}
