package br.com.ifba.autor.dto;

import lombok.Data;

@Data
public class AutorResponseDTO {
    private Long id;
    private String nome;
    private String bio;
}