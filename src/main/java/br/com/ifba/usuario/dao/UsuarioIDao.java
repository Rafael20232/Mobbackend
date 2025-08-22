package br.com.ifba.usuario.dao;

import br.com.ifba.usuario.entity.Autor;

import java.util.Optional;

public interface UsuarioIDao {
    // Somente métodos que NÃO existem no JpaRepository
    Optional<Autor> findByEmail(String email);
    boolean existsByEmail(String email);
}
