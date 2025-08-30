package br.com.ifba.tag.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import br.com.ifba.post.entity.Post;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "tags")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tag extends PersistenceEntity {

    @Column(nullable = false, unique = true)
    private String nome;

    @ManyToMany(mappedBy = "tags")
    private List<Post> posts;
}