package br.com.ifba.categoria.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import br.com.ifba.post.entity.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categorias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categoria extends PersistenceEntity {

    @Column(nullable = false, unique = true)
    private String nome;

    @OneToMany(mappedBy = "categoria")
    private List<Post> posts;
}