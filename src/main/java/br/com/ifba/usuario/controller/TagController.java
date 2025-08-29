package br.com.ifba.usuario.controller;

import br.com.ifba.usuario.entity.Tag;
import br.com.ifba.usuario.service.TagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService service;

    public TagController(TagService service) {
        this.service = service;
    }

    @GetMapping
    public List<Tag> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Tag> getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Tag create(@RequestBody Tag tag) {
        return service.save(tag);
    }

    @PutMapping("/{id}")
    public Tag update(@PathVariable Long id, @RequestBody Tag tag) {
        tag.setId(id);
        return service.save(tag);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
