package br.com.ifba.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.List;

@Data
public class PostRequestDTO {
    @NotBlank(message = "O título é obrigatório.")
    @Size(max = 255)
    private String titulo;

    @NotBlank(message = "O conteúdo é obrigatório.")
    private String conteudo;

    @NotNull(message = "O ID do autor é obrigatório.")
    private Long autorId;

    @NotNull(message = "O ID da categoria é obrigatório.")
    private Long categoriaId;

    private List<Long> tagIds;
}