package com.metricalsky.backlogged.backend.backlog.service;

import java.time.Duration;

import com.metricalsky.backlogged.backend.activity.entity.TimeActivity;
import com.metricalsky.backlogged.backend.activity.service.ActivityMapper;
import com.metricalsky.backlogged.backend.backlog.dto.BacklogItemDto;
import com.metricalsky.backlogged.backend.backlog.entity.BacklogItem;
import com.metricalsky.backlogged.backend.common.service.EntityMapper;

public class BacklogItemMapper implements EntityMapper<BacklogItem, BacklogItemDto> {

    private final ActivityMapper activityMapper = new ActivityMapper();

    @Override
    public BacklogItem toEntity(BacklogItemDto dto) {
        var entity = new BacklogItem();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setType(dto.getType());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    @Override
    public BacklogItemDto toDto(BacklogItem entity) {
        var dto = new BacklogItemDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setType(entity.getType());
        dto.setStatus(entity.getStatus());
        if (entity.getActivities() != null) {
            dto.setActivityTime(entity.getActivities().stream()
                    .filter(TimeActivity.class::isInstance)
                    .map(TimeActivity.class::cast)
                    .map(TimeActivity::getDuration)
                    .reduce(Duration::plus)
                    .orElse(null));
        }
        return dto;
    }

    public BacklogItemDto toDetailedDto(BacklogItem entity) {
        var dto = new BacklogItemDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setType(entity.getType());
        dto.setStatus(entity.getStatus());
        if (entity.getActivities() != null) {
            dto.setActivities(entity.getActivities()
                    .stream()
                    .map(activityMapper::toDto)
                    .toList());
        }
        return dto;
    }

    public void patchEntity(BacklogItem entity, BacklogItemDto dto) {
        entity.setName(dto.getName());
        entity.setType(dto.getType());
        entity.setStatus(dto.getStatus());
    }
}
