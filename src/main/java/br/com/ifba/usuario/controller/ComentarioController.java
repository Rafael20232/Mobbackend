package br.com.ifba.usuario.controller;

import br.com.ifba.usuario.entity.Comentario;
import br.com.ifba.usuario.service.ComentarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    private final ComentarioService service;

    public ComentarioController(ComentarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Comentario> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Comentario> getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Comentario create(@RequestBody Comentario comentario) {
        return service.save(comentario);
    }

    @PutMapping("/{id}")
    public Comentario update(@PathVariable Long id, @RequestBody Comentario comentario) {
        comentario.setId(id);
        return service.save(comentario);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
