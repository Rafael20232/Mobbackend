package br.com.ifba.comentario.controller;

import br.com.ifba.comentario.dto.ComentarioRequestDTO;
import br.com.ifba.comentario.dto.ComentarioResponseDTO;
import br.com.ifba.comentario.service.ComentarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    private final ComentarioService service;

    @Autowired
    public ComentarioController(ComentarioService service) {
        this.service = service;
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<Page<ComentarioResponseDTO>> findByPostId(@PathVariable Long postId, @PageableDefault(sort = "dataCriacao", direction = org.springframework.data.domain.Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(service.findByPostId(postId, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComentarioResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ComentarioResponseDTO> save(@Valid @RequestBody ComentarioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}