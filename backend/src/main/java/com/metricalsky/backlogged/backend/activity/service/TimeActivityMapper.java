package com.metricalsky.backlogged.backend.activity.service;

import java.time.ZonedDateTime;

import com.metricalsky.backlogged.backend.activity.dto.TimeActivityDto;
import com.metricalsky.backlogged.backend.activity.entity.TimeActivity;
import com.metricalsky.backlogged.backend.common.service.EntityMapper;

public class TimeActivityMapper implements EntityMapper<TimeActivity, TimeActivityDto> {

    @Override
    public TimeActivity toEntity(TimeActivityDto dto) {
        var entity = new TimeActivity();
        entity.setActivityDate(ZonedDateTime.now());
        entity.setDuration(dto.getDuration());
        return entity;
    }

    @Override
    public TimeActivityDto toDto(TimeActivity entity) {
        var dto = new TimeActivityDto();
        dto.setId(entity.getId());
        dto.setActivityDate(entity.getActivityDate());
        dto.setDuration(entity.getDuration());
        return dto;
    }
}
