package br.com.ifba.autor.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import br.com.ifba.post.entity.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "autores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Autor extends PersistenceEntity  {

    private String nome;

    private String bio;

    @OneToMany(mappedBy = "autor")
    private List<Post> posts;
}
