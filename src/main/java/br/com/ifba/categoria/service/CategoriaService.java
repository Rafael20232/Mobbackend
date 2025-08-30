package br.com.ifba.categoria.service;

import br.com.ifba.categoria.dto.CategoriaRequestDTO;
import br.com.ifba.categoria.dto.CategoriaResponseDTO;
import br.com.ifba.categoria.entity.Categoria;
import br.com.ifba.categoria.mapper.CategoriaMapper;
import br.com.ifba.categoria.repository.CategoriaRepository;
import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.infrastructure.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    @Autowired
    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public Page<CategoriaResponseDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(CategoriaMapper::toResponseDTO);
    }

    public CategoriaResponseDTO findById(Long id) {
        return repository.findById(id)
                .map(CategoriaMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com ID: " + id));
    }

    public CategoriaResponseDTO save(CategoriaRequestDTO dto) {
        if (repository.existsByNome(dto.getNome())) {
            throw new BusinessException("Já existe uma categoria com o nome informado.");
        }
        Categoria categoria = CategoriaMapper.toEntity(dto);
        return CategoriaMapper.toResponseDTO(repository.save(categoria));
    }

    public CategoriaResponseDTO update(Long id, CategoriaRequestDTO dto) {
        if (repository.existsByNomeAndIdNot(dto.getNome(), id)) {
            throw new BusinessException("Já existe outra categoria com o nome informado.");
        }
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com ID: " + id));

        CategoriaMapper.updateFromDto(dto, categoria);
        return CategoriaMapper.toResponseDTO(repository.save(categoria));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Categoria não encontrada com ID: " + id);
        }
        repository.deleteById(id);
    }
}