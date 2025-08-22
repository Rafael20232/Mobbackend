package br.com.ifba.usuario.dao;

import br.com.ifba.usuario.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioDao extends JpaRepository<Autor, Long>, UsuarioIDao {
    Optional<Autor> findByEmail(String email);
    boolean existsByEmail(String email);
}
