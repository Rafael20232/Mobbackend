package br.com.ifba.usuario.service;

import br.com.ifba.infrastructure.entity.exception.BusinessException;
import br.com.ifba.infrastructure.entity.mapper.ObjectMapperUtil;
import br.com.ifba.usuario.dao.UsuarioDao;
import br.com.ifba.usuario.dto.UsuarioGetResponseDto;
import br.com.ifba.usuario.dto.UsuarioPostRequestDto;
import br.com.ifba.usuario.entity.Autor;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioDao usuarioDao;
    private final ObjectMapperUtil mapper;

    public List<UsuarioGetResponseDto> listar() {
        return mapper.mapAll(usuarioDao.findAll(), UsuarioGetResponseDto.class);
    }

    public UsuarioGetResponseDto buscarPorId(Long id) {
        Autor autor = usuarioDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        return mapper.map(autor, UsuarioGetResponseDto.class);
    }

    @Transactional
    public UsuarioGetResponseDto criar(UsuarioPostRequestDto dto) {
        if (usuarioDao.existsByEmail(dto.getEmail())) {
            throw new BusinessException("E-mail já cadastrado");
        }
        Autor autor = mapper.map(dto, Autor.class);
        Autor salvo = usuarioDao.save(autor);
        return mapper.map(salvo, UsuarioGetResponseDto.class);
    }

    @Transactional
    public void atualizar(Long id, UsuarioPostRequestDto dto) {
        Autor autor = usuarioDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        autor.setNome(dto.getNome());
        autor.setEmail(dto.getEmail());
        autor.setSenha(dto.getSenha());

        usuarioDao.save(autor);
    }

    @Transactional
    public void deletar(Long id) {
        if (!usuarioDao.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado para exclusão");
        }
        usuarioDao.deleteById(id);
    }
}
