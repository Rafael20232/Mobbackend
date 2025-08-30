package br.com.ifba.usuario.service;

import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.infrastructure.exception.ResourceNotFoundException;
import br.com.ifba.usuario.dto.UsuarioRequestDTO;
import br.com.ifba.usuario.dto.UsuarioResponseDTO;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.mapper.UsuarioMapper;
import br.com.ifba.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Page<UsuarioResponseDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(UsuarioMapper::toResponseDTO);
    }

    public UsuarioResponseDTO findById(Long id) {
        return repository.findById(id)
                .map(UsuarioMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));
    }

    public UsuarioResponseDTO save(UsuarioRequestDTO dto) {
        if (repository.existsByEmail(dto.getEmail())) {
            throw new BusinessException("Email já cadastrado.");
        }
        Usuario usuario = UsuarioMapper.toEntity(dto);
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        return UsuarioMapper.toResponseDTO(repository.save(usuario));
    }

    public UsuarioResponseDTO update(Long id, UsuarioRequestDTO dto) {
        if (repository.existsByEmailAndIdNot(dto.getEmail(), id)) {
            throw new BusinessException("Email já cadastrado para outro usuário.");
        }
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setRole(dto.getRole() != null ? dto.getRole().toUpperCase() : "USER");
        // Opcional: só atualiza a senha se uma nova for fornecida
        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        }

        return UsuarioMapper.toResponseDTO(repository.save(usuario));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }
}