package br.com.ifba.autor.mapper;

import br.com.ifba.autor.dto.AutorRequestDTO;
import br.com.ifba.autor.dto.AutorResponseDTO;
import br.com.ifba.autor.entity.Autor;

public class AutorMapper {
    public static Autor toEntity(AutorRequestDTO dto) {
        Autor entity = new Autor();
        entity.setNome(dto.getNome());
        entity.setBio(dto.getBio());
        return entity;
    }

    public static AutorResponseDTO toResponseDTO(Autor entity) {
        AutorResponseDTO dto = new AutorResponseDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setBio(entity.getBio());
        return dto;
    }

    public static void updateFromDto(AutorRequestDTO dto, Autor entity) {
        entity.setNome(dto.getNome());
        entity.setBio(dto.getBio());
    }
}