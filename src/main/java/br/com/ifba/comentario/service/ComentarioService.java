package br.com.ifba.comentario.service;

import br.com.ifba.comentario.dto.ComentarioRequestDTO;
import br.com.ifba.comentario.dto.ComentarioResponseDTO;
import br.com.ifba.comentario.entity.Comentario;
import br.com.ifba.comentario.mapper.ComentarioMapper;
import br.com.ifba.comentario.repository.ComentarioRepository;
import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.infrastructure.exception.ResourceNotFoundException;
import br.com.ifba.post.entity.Post;
import br.com.ifba.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final PostRepository postRepository;

    @Autowired
    public ComentarioService(ComentarioRepository comentarioRepository, PostRepository postRepository) {
        this.comentarioRepository = comentarioRepository;
        this.postRepository = postRepository;
    }

    public Page<ComentarioResponseDTO> findByPostId(Long postId, Pageable pageable) {
        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("Post não encontrado com ID: " + postId);
        }
        return comentarioRepository.findByPostId(postId, pageable).map(ComentarioMapper::toResponseDTO);
    }

    public ComentarioResponseDTO findById(Long id) {
        return comentarioRepository.findById(id)
                .map(ComentarioMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Comentário não encontrado com ID: " + id));
    }

    public ComentarioResponseDTO save(ComentarioRequestDTO dto) {
        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new BusinessException("Post com ID " + dto.getPostId() + " não encontrado."));

        Comentario comentario = ComentarioMapper.toEntity(dto, post);
        return ComentarioMapper.toResponseDTO(comentarioRepository.save(comentario));
    }

    public void delete(Long id) {
        if (!comentarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Comentário não encontrado com ID: " + id);
        }
        comentarioRepository.deleteById(id);
    }
}