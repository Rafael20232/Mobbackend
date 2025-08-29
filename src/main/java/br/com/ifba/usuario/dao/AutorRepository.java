package br.com.ifba.usuario.dao;

import br.com.ifba.usuario.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Autor findByNome(String nome);
}
