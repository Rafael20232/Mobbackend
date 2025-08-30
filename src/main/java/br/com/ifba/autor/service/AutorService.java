package br.com.ifba.autor.service;

import br.com.ifba.autor.dto.AutorRequestDTO;
import br.com.ifba.autor.dto.AutorResponseDTO;
import br.com.ifba.autor.entity.Autor;
import br.com.ifba.autor.mapper.AutorMapper;
import br.com.ifba.autor.repository.AutorRepository;
import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.infrastructure.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

    private final AutorRepository repository;

    @Autowired
    public AutorService(AutorRepository repository) {
        this.repository = repository;
    }

    public Page<AutorResponseDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(AutorMapper::toResponseDTO);
    }

    public AutorResponseDTO findById(Long id) {
        return repository.findById(id)
                .map(AutorMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado com ID: " + id));
    }

    public AutorResponseDTO save(AutorRequestDTO dto) {
        if (repository.existsByNome(dto.getNome())) {
            throw new BusinessException("Já existe um autor com o nome informado.");
        }
        Autor autor = AutorMapper.toEntity(dto);
        return AutorMapper.toResponseDTO(repository.save(autor));
    }

    public AutorResponseDTO update(Long id, AutorRequestDTO dto) {
        if (repository.existsByNomeAndIdNot(dto.getNome(), id)) {
            throw new BusinessException("Já existe outro autor com o nome informado.");
        }
        Autor autor = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado com ID: " + id));

        AutorMapper.updateFromDto(dto, autor);
        return AutorMapper.toResponseDTO(repository.save(autor));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Autor não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }
}