package br.com.ifba.perfil.mapper;

import br.com.ifba.perfil.dto.PerfilRequestDTO;
import br.com.ifba.perfil.dto.PerfilResponseDTO;
import br.com.ifba.perfil.entity.Perfil;
import br.com.ifba.usuario.entity.Usuario;

public class PerfilMapper {

    public static Perfil toEntity(PerfilRequestDTO dto, Usuario usuario) {
        Perfil entity = new Perfil();
        entity.setBio(dto.getBio());
        entity.setFotoUrl(dto.getFotoUrl());
        entity.setLinkedin(dto.getLinkedin());
        entity.setGithub(dto.getGithub());
        entity.setUsuario(usuario);
        return entity;
    }

    public static PerfilResponseDTO toResponseDTO(Perfil entity) {
        PerfilResponseDTO dto = new PerfilResponseDTO();
        dto.setId(entity.getId());
        dto.setBio(entity.getBio());
        dto.setFotoUrl(entity.getFotoUrl());
        dto.setLinkedin(entity.getLinkedin());
        dto.setGithub(entity.getGithub());
        if (entity.getUsuario() != null) {
            dto.setUsuarioId(entity.getUsuario().getId());
            dto.setUsuarioNome(entity.getUsuario().getNome());
        }
        return dto;
    }

    public static void updateFromDto(PerfilRequestDTO dto, Perfil entity, Usuario usuario) {
        entity.setBio(dto.getBio());
        entity.setFotoUrl(dto.getFotoUrl());
        entity.setLinkedin(dto.getLinkedin());
        entity.setGithub(dto.getGithub());
        entity.setUsuario(usuario);
    }
}