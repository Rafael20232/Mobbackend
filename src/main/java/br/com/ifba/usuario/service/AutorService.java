package br.com.ifba.usuario.service;

import br.com.ifba.usuario.dao.AutorRepository;
import br.com.ifba.usuario.entity.Autor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    private final AutorRepository repository;

    public AutorService(AutorRepository repository) {
        this.repository = repository;
    }

    public List<Autor> findAll() {
        return repository.findAll();
    }

    public Optional<Autor> findById(Long id) {
        return repository.findById(id);
    }

    public Autor save(Autor autor) {
        return repository.save(autor);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
