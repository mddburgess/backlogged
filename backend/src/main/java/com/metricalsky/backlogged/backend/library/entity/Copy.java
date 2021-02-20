package com.metricalsky.backlogged.backend.library.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "copies")
public class Copy {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private String platform;

    private String service;
}
