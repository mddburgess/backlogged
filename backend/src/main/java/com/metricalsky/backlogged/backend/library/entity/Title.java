package com.metricalsky.backlogged.backend.library.entity;

import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
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

    @OneToMany(mappedBy = "title", cascade = {PERSIST, MERGE}, orphanRemoval = true)
    private List<Copy> copies;

    public void linkCopies() {
        if (copies != null) {
            copies.forEach(copy -> copy.setTitle(this));
        }
    }
}
