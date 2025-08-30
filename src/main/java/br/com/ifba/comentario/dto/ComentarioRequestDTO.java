package br.com.ifba.comentario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ComentarioRequestDTO {
    @NotBlank(message = "O conteúdo é obrigatório.")
    private String conteudo;

    @NotBlank(message = "O nome do autor é obrigatório.")
    private String autorNome;

    @NotNull(message = "O ID do post é obrigatório.")
    private Long postId;
}