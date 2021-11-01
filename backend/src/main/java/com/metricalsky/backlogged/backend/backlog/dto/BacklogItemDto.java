package com.metricalsky.backlogged.backend.backlog.dto;

import java.time.Duration;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.metricalsky.backlogged.backend.activity.dto.ActivityDto;
import com.metricalsky.backlogged.backend.backlog.entity.BacklogItemStatus;
import com.metricalsky.backlogged.backend.backlog.entity.BacklogItemType;

@Getter
@Setter
public class BacklogItemDto {

    private Integer id;
    private String name;
    private BacklogItemType type;
    private BacklogItemStatus status;
    private Duration activityTime;
    private List<ActivityDto> activities;
}
