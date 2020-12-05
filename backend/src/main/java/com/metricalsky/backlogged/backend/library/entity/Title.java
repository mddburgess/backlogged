package com.metricalsky.backlogged.backend.library.entity;

import java.util.List;
import java.util.UUID;
import javax.persistence.*;

import lombok.Data;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "titles")
public class Title {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private UUID token;

    private String name;

    @OneToMany(mappedBy = "title", cascade = ALL, orphanRemoval = true)
    private List<Copy> copies;

    public void linkCopies() {
        if (copies != null) {
            copies.forEach(copy -> copy.setTitle(this));
        }
    }
}
