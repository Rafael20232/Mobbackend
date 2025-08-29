package br.com.ifba.usuario.service;

import br.com.ifba.usuario.dao.TagRepository;
import br.com.ifba.usuario.entity.Tag;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    private final TagRepository repository;

    public TagService(TagRepository repository) {
        this.repository = repository;
    }

    public List<Tag> findAll() {
        return repository.findAll();
    }

    public Optional<Tag> findById(Long id) {
        return repository.findById(id);
    }

    public Tag save(Tag tag) {
        return repository.save(tag);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
