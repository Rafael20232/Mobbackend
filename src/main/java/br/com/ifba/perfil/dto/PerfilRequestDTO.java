package br.com.ifba.perfil.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class PerfilRequestDTO {
    private String bio;

    @URL(message = "URL da foto inválida.")
    private String fotoUrl;

    @URL(message = "URL do LinkedIn inválida.")
    private String linkedin;

    @URL(message = "URL do GitHub inválida.")
    private String github;

    @NotNull(message = "O ID do usuário é obrigatório.")
    private Long usuarioId;
}