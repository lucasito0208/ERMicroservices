package com.lucasDev.auth.dto;

import com.lucasDev.auth.model.RolTipo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class UsuarioDTO {

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 3, message = "El nombre de usuario debe tener al menos 3 caracteres")
    private String usuario;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe contener al menos 6 caracteres")
    private String password;

    @Email(message = "Debe introducir un correo válido")
    private String email;

    private Set<RolTipo> roles;
}
