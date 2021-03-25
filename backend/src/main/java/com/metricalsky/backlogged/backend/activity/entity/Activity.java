package com.metricalsky.backlogged.backend.activity.entity;

import java.time.OffsetDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

import com.metricalsky.backlogged.backend.library.entity.Title;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private String activityType;

    private OffsetDateTime activityDate;

    @ManyToOne
    @JoinColumn(name = "title_id")
    private Title title;
}
