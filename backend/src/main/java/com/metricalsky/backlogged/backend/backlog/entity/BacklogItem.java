package com.metricalsky.backlogged.backend.backlog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "backlog_items")
public class BacklogItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BacklogItemType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BacklogItemStatus status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BacklogItemType getType() {
        return type;
    }

    public void setType(BacklogItemType type) {
        this.type = type;
    }

    public BacklogItemStatus getStatus() {
        return status;
    }

    public void setStatus(BacklogItemStatus status) {
        this.status = status;
    }
}
