package com.lucasDev.auth.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestDTO {
    public String usuario;
    public String password;
}
