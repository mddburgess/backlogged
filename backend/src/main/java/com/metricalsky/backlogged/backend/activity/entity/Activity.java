package com.metricalsky.backlogged.backend.activity.entity;

import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.metricalsky.backlogged.backend.library.entity.Title;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Enumerated(STRING)
    @Column(name = "activity_type")
    private ActivityType type;

    @Builder.Default
    @Column(name = "activity_date")
    private OffsetDateTime date = OffsetDateTime.now();

    @ManyToOne
    @JoinColumn(name = "title_id")
    private Title title;
}
