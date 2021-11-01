package com.metricalsky.backlogged.backend.activity.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;

import com.metricalsky.backlogged.backend.backlog.entity.BacklogItemStatus;

@Getter
@Setter
@JsonTypeName("STATUS")
public class StatusActivityDto extends ActivityDto {

    private BacklogItemStatus fromStatus;
    private BacklogItemStatus toStatus;
}
