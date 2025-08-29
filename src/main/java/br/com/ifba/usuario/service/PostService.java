package br.com.ifba.usuario.service;

import br.com.ifba.usuario.dao.PostRepository;
import br.com.ifba.usuario.entity.Post;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> findAll() {
        return repository.findAll();
    }

    public Optional<Post> findById(Long id) {
        return repository.findById(id);
    }

    public Post save(Post post) {
        return repository.save(post);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
