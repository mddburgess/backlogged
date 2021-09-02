package com.metricalsky.backlogged.backend.activity.service;

import java.time.ZonedDateTime;

import org.springframework.stereotype.Service;

import com.metricalsky.backlogged.backend.activity.dto.ActivityDto;
import com.metricalsky.backlogged.backend.activity.entity.TimeActivity;
import com.metricalsky.backlogged.backend.common.service.EntityMapper;

@Service
public class TimeActivityMapper implements EntityMapper<TimeActivity, ActivityDto> {

    @Override
    public TimeActivity toEntity(ActivityDto dto) {
        var entity = new TimeActivity();
        entity.setActivityDate(ZonedDateTime.now());
        entity.setDuration(dto.getDuration());
        return entity;
    }

    @Override
    public ActivityDto toDto(TimeActivity entity) {
        var dto = new ActivityDto();
        dto.setDuration(entity.getDuration());
        return dto;
    }
}
