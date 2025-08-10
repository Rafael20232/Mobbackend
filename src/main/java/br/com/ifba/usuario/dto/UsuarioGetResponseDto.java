package br.com.ifba.usuario.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioGetResponseDto {
    private String nome;
    private String email;
    // senha não incluída
}

