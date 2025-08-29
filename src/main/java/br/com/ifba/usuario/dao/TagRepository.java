package br.com.ifba.usuario.dao;

import br.com.ifba.usuario.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByNome(String nome);
}
