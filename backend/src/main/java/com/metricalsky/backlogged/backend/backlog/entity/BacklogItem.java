package com.metricalsky.backlogged.backend.backlog.entity;

import java.time.Duration;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.metricalsky.backlogged.backend.activity.entity.Activity;
import com.metricalsky.backlogged.backend.common.converters.DurationInMinutesConverter;
import com.metricalsky.backlogged.backend.common.entity.IdentifiableEntity;

@Entity
@Table(name = "backlog_items")
@Getter
@Setter
public class BacklogItem extends IdentifiableEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BacklogItemType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BacklogItemStatus status;

    @Enumerated(EnumType.STRING)
    private BacklogItemResolution resolution;

    @Convert(converter = DurationInMinutesConverter.class)
    private Duration duration;

    @OneToMany(mappedBy = "backlogItem")
    private List<Activity> activities;
}
