package com.lucasDev.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponseDTO {
    private String token;
}
