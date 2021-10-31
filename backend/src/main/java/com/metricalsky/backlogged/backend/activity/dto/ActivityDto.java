package com.metricalsky.backlogged.backend.activity.dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ActivityDto {

    private Integer id;
    private ZonedDateTime activityDate;
}
