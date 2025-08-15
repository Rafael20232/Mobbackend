package br.com.ifba.usuario.service;

import br.com.ifba.usuario.dto.UsuarioGetResponseDto;
import br.com.ifba.usuario.dto.UsuarioPostRequestDto;

import java.util.List;

public interface UsuarioIService {
    List<UsuarioGetResponseDto> listar();
    UsuarioGetResponseDto buscarPorId(Long id);
    UsuarioGetResponseDto criar(UsuarioPostRequestDto dto);
    void atualizar(Long id, UsuarioPostRequestDto dto);
    void deletar(Long id);
}
