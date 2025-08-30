package br.com.ifba.perfil.service;

import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.infrastructure.exception.ResourceNotFoundException;
import br.com.ifba.perfil.dto.PerfilRequestDTO;
import br.com.ifba.perfil.dto.PerfilResponseDTO;
import br.com.ifba.perfil.entity.Perfil;
import br.com.ifba.perfil.mapper.PerfilMapper;
import br.com.ifba.perfil.repository.PerfilRepository;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {

    private final PerfilRepository perfilRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public PerfilService(PerfilRepository perfilRepository, UsuarioRepository usuarioRepository) {
        this.perfilRepository = perfilRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Page<PerfilResponseDTO> findAll(Pageable pageable) {
        return perfilRepository.findAll(pageable).map(PerfilMapper::toResponseDTO);
    }

    public PerfilResponseDTO findById(Long id) {
        return perfilRepository.findById(id)
                .map(PerfilMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil não encontrado com ID: " + id));
    }

    public PerfilResponseDTO save(PerfilRequestDTO dto) {
        if (perfilRepository.existsByUsuarioId(dto.getUsuarioId())) {
            throw new BusinessException("Este usuário já possui um perfil.");
        }
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new BusinessException("Usuário não encontrado com ID: " + dto.getUsuarioId()));

        Perfil perfil = PerfilMapper.toEntity(dto, usuario);
        return PerfilMapper.toResponseDTO(perfilRepository.save(perfil));
    }

    public PerfilResponseDTO update(Long id, PerfilRequestDTO dto) {
        if (perfilRepository.existsByUsuarioIdAndIdNot(dto.getUsuarioId(), id)) {
            throw new BusinessException("Este usuário já possui outro perfil associado.");
        }

        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil não encontrado com ID: " + id));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new BusinessException("Usuário não encontrado com ID: " + dto.getUsuarioId()));

        PerfilMapper.updateFromDto(dto, perfil, usuario);
        return PerfilMapper.toResponseDTO(perfilRepository.save(perfil));
    }

    public void delete(Long id) {
        if (!perfilRepository.existsById(id)) {
            throw new ResourceNotFoundException("Perfil não encontrado com ID: " + id);
        }
        perfilRepository.deleteById(id);
    }
}