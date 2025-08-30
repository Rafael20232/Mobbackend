package br.com.ifba.comentario.mapper;

import br.com.ifba.comentario.dto.ComentarioRequestDTO;
import br.com.ifba.comentario.dto.ComentarioResponseDTO;
import br.com.ifba.comentario.entity.Comentario;
import br.com.ifba.post.entity.Post;

public class ComentarioMapper {

    public static Comentario toEntity(ComentarioRequestDTO dto, Post post) {
        Comentario entity = new Comentario();
        entity.setConteudo(dto.getConteudo());
        entity.setAutorNome(dto.getAutorNome());
        entity.setPost(post);
        return entity;
    }

    public static ComentarioResponseDTO toResponseDTO(Comentario entity) {
        ComentarioResponseDTO dto = new ComentarioResponseDTO();
        dto.setId(entity.getId());
        dto.setConteudo(entity.getConteudo());
        dto.setAutorNome(entity.getAutorNome());
        dto.setDataCriacao(entity.getDataCriacao());
        if (entity.getPost() != null) {
            dto.setPostId(entity.getPost().getId());
        }
        return dto;
    }

    public static void updateFromDto(ComentarioRequestDTO dto, Comentario entity, Post post) {
        entity.setConteudo(dto.getConteudo());
        entity.setAutorNome(dto.getAutorNome());
        entity.setPost(post);
    }
}