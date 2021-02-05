package com.metricalsky.backlogged.backend.backlog.service;

import com.metricalsky.backlogged.backend.backlog.dto.BacklogData;
import com.metricalsky.backlogged.backend.backlog.entity.Backlog;
import com.metricalsky.backlogged.backend.library.service.TitleMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = TitleMapper.class)
public interface BacklogMapper {

    @Mapping(source = "id", target = "key")
    BacklogData fromEntity(Backlog entity);

    @Mapping(source = "title.key", target = "title.id")
    Backlog toEntity(BacklogData data);
}
