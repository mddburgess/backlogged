package com.metricalsky.backlogged.backend.activity.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.metricalsky.backlogged.backend.backlog.entity.BacklogItemStatus;

@Entity
@DiscriminatorValue("STATUS")
public class StatusActivity extends Activity {

    @Enumerated(EnumType.STRING)
    private BacklogItemStatus fromStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BacklogItemStatus toStatus;

    public BacklogItemStatus getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(BacklogItemStatus fromStatus) {
        this.fromStatus = fromStatus;
    }

    public BacklogItemStatus getToStatus() {
        return toStatus;
    }

    public void setToStatus(BacklogItemStatus toStatus) {
        this.toStatus = toStatus;
    }
}
