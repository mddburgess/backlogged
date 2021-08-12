package com.metricalsky.backlogged.backend.backlog.dto;

import com.metricalsky.backlogged.backend.backlog.entity.BacklogItemStatus;
import com.metricalsky.backlogged.backend.backlog.entity.BacklogItemType;

public class BacklogItemDto {

    private Integer id;
    private String name;
    private BacklogItemType type;
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
