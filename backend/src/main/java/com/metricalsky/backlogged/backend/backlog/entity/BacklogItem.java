package com.metricalsky.backlogged.backend.backlog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.metricalsky.backlogged.backend.common.entity.IdentifiableEntity;

@Entity
@Table(name = "backlog_items")
public class BacklogItem extends IdentifiableEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BacklogItemType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BacklogItemStatus status;

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
