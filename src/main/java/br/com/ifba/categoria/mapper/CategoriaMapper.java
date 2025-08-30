package br.com.ifba.categoria.mapper;

import br.com.ifba.categoria.dto.CategoriaRequestDTO;
import br.com.ifba.categoria.dto.CategoriaResponseDTO;
import br.com.ifba.categoria.entity.Categoria;

public class CategoriaMapper {
    public static Categoria toEntity(CategoriaRequestDTO dto) {
        Categoria entity = new Categoria();
        entity.setNome(dto.getNome());
        return entity;
    }

    public static CategoriaResponseDTO toResponseDTO(Categoria entity) {
        CategoriaResponseDTO dto = new CategoriaResponseDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        return dto;
    }

    public static void updateFromDto(CategoriaRequestDTO dto, Categoria entity) {
        entity.setNome(dto.getNome());
    }
}