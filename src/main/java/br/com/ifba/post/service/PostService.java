package br.com.ifba.post.service;

// imports...

import br.com.ifba.autor.entity.Autor;
import br.com.ifba.autor.repository.AutorRepository;
import br.com.ifba.categoria.entity.Categoria;
import br.com.ifba.categoria.repository.CategoriaRepository;
import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.infrastructure.exception.ResourceNotFoundException;
import br.com.ifba.post.dto.PostRequestDTO;
import br.com.ifba.post.dto.PostResponseDTO;
import br.com.ifba.post.entity.Post;
import br.com.ifba.post.mapper.PostMapper;
import br.com.ifba.post.repository.PostRepository;
import br.com.ifba.tag.entity.Tag;
import br.com.ifba.tag.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final AutorRepository autorRepository;
    private final CategoriaRepository categoriaRepository;
    private final TagRepository tagRepository;

    @Autowired
    public PostService(PostRepository postRepository, AutorRepository autorRepository, CategoriaRepository categoriaRepository, TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.autorRepository = autorRepository;
        this.categoriaRepository = categoriaRepository;
        this.tagRepository = tagRepository;
    }

    public Page<PostResponseDTO> findAll(Pageable pageable) {
        return postRepository.findAll(pageable).map(PostMapper::toResponseDTO);
    }

    public PostResponseDTO findById(Long id) {
        return postRepository.findById(id)
                .map(PostMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Post não encontrado com ID: " + id));
    }

    public PostResponseDTO save(PostRequestDTO dto) {
        Autor autor = findAutorById(dto.getAutorId());
        Categoria categoria = findCategoriaById(dto.getCategoriaId());
        List<Tag> tags = findTagsByIds(dto.getTagIds());

        Post post = PostMapper.toEntity(dto, autor, categoria, tags);
        return PostMapper.toResponseDTO(postRepository.save(post));
    }

    public PostResponseDTO update(Long id, PostRequestDTO dto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post não encontrado com ID: " + id));

        Autor autor = findAutorById(dto.getAutorId());
        Categoria categoria = findCategoriaById(dto.getCategoriaId());
        List<Tag> tags = findTagsByIds(dto.getTagIds());

        PostMapper.updateFromDto(dto, post, autor, categoria, tags);
        return PostMapper.toResponseDTO(postRepository.save(post));
    }

    public void delete(Long id) {
        if (!postRepository.existsById(id)) {
            throw new ResourceNotFoundException("Post não encontrado com ID: " + id);
        }
        postRepository.deleteById(id);
    }

    // Métodos auxiliares para validação de regras de negócio
    private Autor findAutorById(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Autor com ID " + id + " não encontrado."));
    }

    private Categoria findCategoriaById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Categoria com ID " + id + " não encontrada."));
    }

    private List<Tag> findTagsByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }
        List<Tag> tags = tagRepository.findAllById(ids);
        if (tags.size() != ids.size()) {
            throw new BusinessException("Uma ou mais tags não foram encontradas.");
        }
        return tags;
    }
}