package br.com.ifba.usuario.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "comentarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comentario extends PersistenceEntity {

    private String autorNome;
    private String autorEmail;

    @Column(columnDefinition = "TEXT")
    private String mensagem;

    private LocalDateTime dataCriacao;
    private boolean aprovado;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
