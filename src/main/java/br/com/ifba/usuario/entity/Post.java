package br.com.ifba.usuario.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import br.com.ifba.usuario.entity.Autor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post extends PersistenceEntity {

    private String titulo;
    private String slug;
    @Column(columnDefinition = "TEXT")
    private String conteudoMarkdown;
    private LocalDateTime dataPublicacao;
    private String status;
    private String imagemCapaUrl;
    private String episodioReferente;
    private int curtidas;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comentario> comentarios = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<>();
}
