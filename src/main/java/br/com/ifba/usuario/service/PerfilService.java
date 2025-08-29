package br.com.ifba.usuario.service;

import br.com.ifba.usuario.entity.Perfil;
import br.com.ifba.usuario.dao.PerfilRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilService {

    private final PerfilRepository perfilRepository;

    public PerfilService(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    public List<Perfil> listarTodos() {
        return perfilRepository.findAll();
    }

    public Perfil buscarPorId(Long id) {
        return perfilRepository.findById(id).orElse(null);
    }

    public Perfil salvar(Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    public Perfil atualizar(Long id, Perfil perfilAtualizado) {
        return perfilRepository.findById(id).map(perfil -> {
            perfil.setBio(perfilAtualizado.getBio());
            perfil.setFotoUrl(perfilAtualizado.getFotoUrl());
            perfil.setLinkedin(perfilAtualizado.getLinkedin());
            perfil.setGithub(perfilAtualizado.getGithub());
            perfil.setUsuario(perfilAtualizado.getUsuario());
            return perfilRepository.save(perfil);
        }).orElse(null);
    }

    public void deletar(Long id) {
        perfilRepository.deleteById(id);
    }
}
