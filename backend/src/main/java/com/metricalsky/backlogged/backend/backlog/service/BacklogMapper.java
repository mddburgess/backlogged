package com.metricalsky.backlogged.backend.backlog.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.metricalsky.backlogged.backend.backlog.dto.BacklogData;
import com.metricalsky.backlogged.backend.backlog.entity.Backlog;
import com.metricalsky.backlogged.backend.library.service.TitleMapper;

@Mapper(componentModel = "spring", uses = TitleMapper.class)
public interface BacklogMapper {

    @Mapping(source = "id", target = "key")
    BacklogData fromEntity(Backlog entity);
}
