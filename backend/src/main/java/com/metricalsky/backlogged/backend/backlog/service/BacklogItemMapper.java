package com.metricalsky.backlogged.backend.backlog.service;

import org.springframework.stereotype.Service;

import com.metricalsky.backlogged.backend.backlog.dto.BacklogItemDto;
import com.metricalsky.backlogged.backend.backlog.entity.BacklogItem;
import com.metricalsky.backlogged.backend.common.service.EntityMapper;

@Service
public class BacklogItemMapper implements EntityMapper<BacklogItem, BacklogItemDto> {

    @Override
    public BacklogItem toEntity(BacklogItemDto dto) {
        var entity = new BacklogItem();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    @Override
    public BacklogItemDto toDto(BacklogItem entity) {
        var dto = new BacklogItemDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
