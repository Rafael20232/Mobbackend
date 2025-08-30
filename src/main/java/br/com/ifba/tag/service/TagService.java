package br.com.ifba.tag.service;

import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.infrastructure.exception.ResourceNotFoundException;
import br.com.ifba.tag.dto.TagRequestDTO;
import br.com.ifba.tag.dto.TagResponseDTO;
import br.com.ifba.tag.entity.Tag;
import br.com.ifba.tag.mapper.TagMapper;
import br.com.ifba.tag.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    private final TagRepository repository;

    @Autowired
    public TagService(TagRepository repository) {
        this.repository = repository;
    }

    public Page<TagResponseDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(TagMapper::toResponseDTO);
    }

    public TagResponseDTO findById(Long id) {
        return repository.findById(id)
                .map(TagMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Tag não encontrada com ID: " + id));
    }

    public TagResponseDTO save(TagRequestDTO dto) {
        if (repository.existsByNome(dto.getNome())) {
            throw new BusinessException("Já existe uma tag com o nome informado.");
        }
        Tag tag = TagMapper.toEntity(dto);
        return TagMapper.toResponseDTO(repository.save(tag));
    }

    public TagResponseDTO update(Long id, TagRequestDTO dto) {
        if (repository.existsByNomeAndIdNot(dto.getNome(), id)) {
            throw new BusinessException("Já existe outra tag com o nome informado.");
        }
        Tag tag = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tag não encontrada com ID: " + id));

        TagMapper.updateFromDto(dto, tag);
        return TagMapper.toResponseDTO(repository.save(tag));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Tag não encontrada com ID: " + id);
        }
        repository.deleteById(id);
    }
}