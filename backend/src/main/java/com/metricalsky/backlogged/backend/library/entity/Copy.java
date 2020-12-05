package com.metricalsky.backlogged.backend.library.entity;

import javax.persistence.*;

import lombok.Data;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "copies")
public class Copy {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @ManyToOne(cascade = ALL)
    @JoinColumn(nullable = false)
    private Title title;

    private String platform;

    private String service;
}
