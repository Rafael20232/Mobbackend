package br.com.ifba.usuario.mapper;

import br.com.ifba.usuario.dto.UsuarioRequestDTO;
import br.com.ifba.usuario.dto.UsuarioResponseDTO;
import br.com.ifba.usuario.entity.Usuario;

public class UsuarioMapper {
    public static Usuario toEntity(UsuarioRequestDTO dto) {
        Usuario entity = new Usuario();
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setSenha(dto.getSenha());
        entity.setRole(dto.getRole() != null ? dto.getRole().toUpperCase() : "USER");
        return entity;
    }

    public static UsuarioResponseDTO toResponseDTO(Usuario entity) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setRole(entity.getRole());
        return dto;
    }
}