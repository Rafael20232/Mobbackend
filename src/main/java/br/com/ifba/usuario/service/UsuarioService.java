package br.com.ifba.usuario.service;

import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.dao.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    // Criar ou atualizar usuário
    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    // Buscar todos
    public List<Usuario> findAll() {
        return repository.findAll();
    }

    // Buscar por ID
    public Optional<Usuario> findById(Long id) {
        return repository.findById(id);
    }

    // Atualizar
    public Optional<Usuario> update(Long id, Usuario usuarioAtualizado) {
        return repository.findById(id).map(usuario -> {
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setSenha(usuarioAtualizado.getSenha());
            usuario.setRole(usuarioAtualizado.getRole());
            return repository.save(usuario);
        });
    }

    // Deletar
    public boolean delete(Long id) {
        return repository.findById(id).map(usuario -> {
            repository.delete(usuario);
            return true;
        }).orElse(false);
    }
}
