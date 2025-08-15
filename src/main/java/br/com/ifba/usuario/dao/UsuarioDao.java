package br.com.ifba.usuario.dao;

import br.com.ifba.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioDao extends JpaRepository<Usuario, Long>, UsuarioIDao {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}
