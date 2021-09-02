package com.metricalsky.backlogged.backend.backlog.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.metricalsky.backlogged.backend.activity.entity.Activity;
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

    @OneToMany(mappedBy = "backlogItem")
    private List<Activity> activities;

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

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
