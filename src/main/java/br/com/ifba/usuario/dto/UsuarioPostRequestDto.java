package br.com.ifba.usuario.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioPostRequestDto {
    private String nome;
    private String email;
    private String senha; // recebe
}
