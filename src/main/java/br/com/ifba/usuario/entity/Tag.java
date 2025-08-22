package br.com.ifba.usuario.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tags")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tag extends PersistenceEntity {

    private String nome;
    private String slug;

    @ManyToMany(mappedBy = "tags")
    private List<Post> posts = new ArrayList<>();
}
