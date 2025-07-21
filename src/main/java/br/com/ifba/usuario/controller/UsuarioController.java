package br.com.ifba.usuario.controller;

import org.springframework.web.bind.annotation.*;
import br.com.ifba.usuario.entity.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioController {

    private final List<Usuario> usuarios = new ArrayList<>();

    public UsuarioController() {
        usuarios.add(new Usuario("João Jose", "jon@email.com", "1234"));
        usuarios.add(new Usuario("Maria Vieira", "mary@email.com", "1234"));
        usuarios.add(new Usuario("Carlos Pedreiro", "carlitos@email.com", "1234"));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        usuarios.add(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }
}