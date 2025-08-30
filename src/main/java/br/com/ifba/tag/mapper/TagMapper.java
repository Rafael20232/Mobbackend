package br.com.ifba.tag.mapper;

import br.com.ifba.tag.dto.TagRequestDTO;
import br.com.ifba.tag.dto.TagResponseDTO;
import br.com.ifba.tag.entity.Tag;

public class TagMapper {
    public static Tag toEntity(TagRequestDTO dto) {
        Tag entity = new Tag();
        entity.setNome(dto.getNome());
        return entity;
    }

    public static TagResponseDTO toResponseDTO(Tag entity) {
        TagResponseDTO dto = new TagResponseDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        return dto;
    }

    public static void updateFromDto(TagRequestDTO dto, Tag entity) {
        entity.setNome(dto.getNome());
    }
}