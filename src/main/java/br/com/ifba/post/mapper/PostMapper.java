package br.com.ifba.post.mapper;

// imports...
import br.com.ifba.autor.entity.Autor;
import br.com.ifba.autor.mapper.AutorMapper;
import br.com.ifba.categoria.entity.Categoria;
import br.com.ifba.categoria.mapper.CategoriaMapper;
import br.com.ifba.post.dto.PostRequestDTO;
import br.com.ifba.post.dto.PostResponseDTO;
import br.com.ifba.post.entity.Post;
import br.com.ifba.tag.entity.Tag;
import br.com.ifba.tag.mapper.TagMapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;

public class PostMapper {
    public static Post toEntity(PostRequestDTO dto, Autor autor, Categoria categoria, List<Tag> tags) {
        Post entity = new Post();
        entity.setTitulo(dto.getTitulo());
        entity.setConteudo(dto.getConteudo());
        entity.setAutor(autor);
        entity.setCategoria(categoria);
        entity.setTags(tags);
        return entity;
    }

    public static PostResponseDTO toResponseDTO(Post entity) {
        PostResponseDTO dto = new PostResponseDTO();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setConteudo(entity.getConteudo());
        dto.setDataPublicacao(entity.getDataPublicacao());

        if (entity.getAutor() != null) {
            dto.setAutor(AutorMapper.toResponseDTO(entity.getAutor()));
        }
        if (entity.getCategoria() != null) {
            dto.setCategoria(CategoriaMapper.toResponseDTO(entity.getCategoria()));
        }
        if (entity.getTags() != null) {
            dto.setTags(entity.getTags().stream().map(TagMapper::toResponseDTO).collect(Collectors.toList()));
        } else {
            dto.setTags(Collections.emptyList());
        }
        return dto;
    }

    public static void updateFromDto(PostRequestDTO dto, Post entity, Autor autor, Categoria categoria, List<Tag> tags) {
        entity.setTitulo(dto.getTitulo());
        entity.setConteudo(dto.getConteudo());
        entity.setAutor(autor);
        entity.setCategoria(categoria);
        entity.setTags(tags);
    }
}