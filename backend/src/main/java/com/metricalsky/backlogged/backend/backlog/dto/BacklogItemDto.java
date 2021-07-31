package com.metricalsky.backlogged.backend.backlog.dto;

import com.metricalsky.backlogged.backend.backlog.entity.BacklogItemStatus;

public class BacklogItemDto {

    private Integer id;
    private String name;
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

    public BacklogItemStatus getStatus() {
        return status;
    }

    public void setStatus(BacklogItemStatus status) {
        this.status = status;
    }
}
