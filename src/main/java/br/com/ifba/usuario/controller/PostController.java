package br.com.ifba.usuario.controller;

import br.com.ifba.usuario.entity.Post;
import br.com.ifba.usuario.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public List<Post> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Post> getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Post create(@RequestBody Post post) {
        return service.save(post);
    }

    @PutMapping("/{id}")
    public Post update(@PathVariable Long id, @RequestBody Post post) {
        post.setId(id);
        return service.save(post);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
