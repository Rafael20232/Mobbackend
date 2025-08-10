package br.com.ifba.usuario.controller;

import br.com.ifba.infrastructure.entity.exception.BusinessException;
import br.com.ifba.infrastructure.entity.mapper.ObjectMapperUtil;
import br.com.ifba.usuario.dto.UsuarioGetResponseDto;
import br.com.ifba.usuario.dto.UsuarioPostRequestDto;
import br.com.ifba.usuario.entity.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final List<Usuario> usuarios = new ArrayList<>();
    private final ObjectMapperUtil objectMapperUtil;

    public UsuarioController(ObjectMapperUtil objectMapperUtil) {
        this.objectMapperUtil = objectMapperUtil;

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

    @GetMapping
    public ResponseEntity<List<UsuarioGetResponseDto>> getAll() {
        List<UsuarioGetResponseDto> dtoList = objectMapperUtil.mapAll(usuarios, UsuarioGetResponseDto.class);
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping
    public ResponseEntity<UsuarioGetResponseDto> create(@RequestBody UsuarioPostRequestDto usuarioDto) {
        if (usuarioDto.getEmail() == null || usuarioDto.getEmail().isEmpty()) {
            throw new BusinessException("Email é obrigatório");
        }

        Usuario usuario = objectMapperUtil.map(usuarioDto, Usuario.class);
        usuario.setId((long) (usuarios.size() + 1));
        usuarios.add(usuario);

        UsuarioGetResponseDto responseDto = objectMapperUtil.map(usuario, UsuarioGetResponseDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UsuarioPostRequestDto usuarioDto) {
        Optional<Usuario> usuarioExistente = usuarios.stream()
                .filter(u -> u.getId() != null && u.getId().equals(id))
                .findFirst();

        if (usuarioExistente.isEmpty()) {
            throw new BusinessException("Usuário não encontrado");
        }

        Usuario u = usuarioExistente.get();
        u.setNome(usuarioDto.getNome());
        u.setEmail(usuarioDto.getEmail());
        u.setSenha(usuarioDto.getSenha());

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean removido = usuarios.removeIf(u -> u.getId() != null && u.getId().equals(id));

        if (!removido) {
            throw new BusinessException("Usuário não encontrado para exclusão");
        }

        return ResponseEntity.noContent().build();
    }
}
