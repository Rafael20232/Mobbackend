package br.com.ifba.comentario.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ComentarioResponseDTO {
    private Long id;
    private String conteudo;
    private String autorNome;
    private LocalDateTime dataCriacao;
    private Long postId;
}