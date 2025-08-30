package br.com.ifba.post.dto;

import br.com.ifba.autor.dto.AutorResponseDTO;
import br.com.ifba.categoria.dto.CategoriaResponseDTO;
import br.com.ifba.tag.dto.TagResponseDTO;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostResponseDTO {
    private Long id;
    private String titulo;
    private String conteudo;
    private LocalDateTime dataPublicacao;
    private AutorResponseDTO autor;
    private CategoriaResponseDTO categoria;
    private List<TagResponseDTO> tags;
}