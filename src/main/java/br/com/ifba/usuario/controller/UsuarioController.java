package br.com.ifba.usuario.controller;

import org.springframework.web.bind.annotation.*;
import br.com.ifba.usuario.entity.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioController {

    private final List<Usuario> usuarios = new ArrayList<>();

    public UsuarioController() {
        Usuario u1 = new Usuario("João Jose", "jon@email.com", "1234");
        u1.setId(1L);
        Usuario u2 = new Usuario("Maria Vieira", "mary@email.com", "1234");
        u2.setId(2L);
        Usuario u3 = new Usuario("Carlos Pedreiro", "carlitos@email.com", "1234");
        u3.setId(3L);

        usuarios.add(u1);
        usuarios.add(u2);
        usuarios.add(u3);
    }

    // GET - buscar todos os usuários
    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        return ResponseEntity.ok(usuarios);
    }

    // POST - criar novo usuário com ID gerado automaticamente
    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        Long novoId = (long) (usuarios.size() + 1);
        usuario.setId(novoId);
        usuarios.add(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    // PUT - atualizar usuário por ID
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        Optional<Usuario> usuarioExistente = usuarios.stream()
                .filter(u -> u.getId() != null && u.getId().equals(id))
                .findFirst();

        if (usuarioExistente.isPresent()) {
            Usuario u = usuarioExistente.get();
            u.setNome(usuarioAtualizado.getNome());
            u.setEmail(usuarioAtualizado.getEmail());
            u.setSenha(usuarioAtualizado.getSenha());
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    // DELETE - remover usuário por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean removido = usuarios.removeIf(u -> u.getId() != null && u.getId().equals(id));

        if (removido) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}
