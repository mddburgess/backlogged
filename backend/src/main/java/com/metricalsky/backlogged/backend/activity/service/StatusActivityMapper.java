package com.metricalsky.backlogged.backend.activity.service;

import com.metricalsky.backlogged.backend.activity.dto.StatusActivityDto;
import com.metricalsky.backlogged.backend.activity.entity.StatusActivity;

public class StatusActivityMapper {

    public StatusActivityDto toDto(StatusActivity entity) {
        var dto = new StatusActivityDto();
        dto.setId(entity.getId());
        dto.setActivityDate(entity.getActivityDate());
        dto.setFromStatus(entity.getFromStatus());
        dto.setToStatus(entity.getToStatus());
        return dto;
    }
}
