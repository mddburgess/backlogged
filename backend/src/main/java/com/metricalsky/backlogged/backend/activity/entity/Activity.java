package com.metricalsky.backlogged.backend.activity.entity;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.metricalsky.backlogged.backend.backlog.entity.BacklogItem;
import com.metricalsky.backlogged.backend.common.entity.IdentifiableEntity;

@Entity
@Table(name = "activities")
@DiscriminatorColumn(name = "type")
public class Activity extends IdentifiableEntity {

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private BacklogItem backlogItem;

    @Column(nullable = false)
    private ZonedDateTime activityDate;

    public BacklogItem getBacklogItem() {
        return backlogItem;
    }

    public void setBacklogItem(BacklogItem backlogItem) {
        this.backlogItem = backlogItem;
    }

    public ZonedDateTime getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(ZonedDateTime activityDate) {
        this.activityDate = activityDate;
    }
}
