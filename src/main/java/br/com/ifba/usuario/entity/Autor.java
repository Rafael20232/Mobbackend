package br.com.ifba.usuario.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table(name = "autores")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Autor extends PersistenceEntity {

    @Column(nullable = false)
    private String nome;

    private String bio;

    private String fotoUrl;

    private String redesSociais;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();
}
