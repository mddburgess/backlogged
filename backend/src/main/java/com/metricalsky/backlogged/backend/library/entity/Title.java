package com.metricalsky.backlogged.backend.library.entity;

import java.util.ArrayList;
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

    @OneToMany(mappedBy = "title", cascade = ALL)
    private List<Copy> copies;

    public void addCopy(Copy copy) {
        if (copies == null) {
            copies = new ArrayList<>();
        }
        copies.add(copy);
        copy.setTitle(this);
    }
}
