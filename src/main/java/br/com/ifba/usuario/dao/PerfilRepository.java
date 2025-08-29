package br.com.ifba.usuario.dao;

import br.com.ifba.usuario.entity.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    Perfil findByUsuarioId(Long usuarioId);
}
