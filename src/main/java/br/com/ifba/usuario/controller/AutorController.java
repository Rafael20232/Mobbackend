package br.com.ifba.usuario.controller;

import br.com.ifba.usuario.entity.Autor;
import br.com.ifba.usuario.service.AutorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autor")
public class AutorController {

    private final AutorService service;

    public AutorController(AutorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Autor> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Autor> getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Autor create(@RequestBody Autor autor) {
        return service.save(autor);
    }

    @PutMapping("/{id}")
    public Autor update(@PathVariable Long id, @RequestBody Autor autor) {
        autor.setId(id);
        return service.save(autor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
