package br.com.ifba.perfil.dto;

import lombok.Data;

@Data
public class PerfilResponseDTO {
    private Long id;
    private String bio;
    private String fotoUrl;
    private String linkedin;
    private String github;
    private Long usuarioId;
    private String usuarioNome;
}