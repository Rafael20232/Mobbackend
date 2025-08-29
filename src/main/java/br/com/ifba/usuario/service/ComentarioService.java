package br.com.ifba.usuario.service;

import br.com.ifba.usuario.dao.ComentarioRepository;
import br.com.ifba.usuario.entity.Comentario;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    private final ComentarioRepository repository;

    public ComentarioService(ComentarioRepository repository) {
        this.repository = repository;
    }

    public List<Comentario> findAll() {
        return repository.findAll();
    }

    public Optional<Comentario> findById(Long id) {
        return repository.findById(id);
    }

    public Comentario save(Comentario comentario) {
        return repository.save(comentario);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
