package com.metricalsky.backlogged.backend.activity.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.metricalsky.backlogged.backend.activity.dto.ActivityDto;
import com.metricalsky.backlogged.backend.activity.entity.Activity;
import com.metricalsky.backlogged.backend.library.service.TitleMapper;

@Mapper(uses = TitleMapper.class)
public interface ActivityMapper {

    @Mapping(target = "key", source = "id")
    ActivityDto fromEntity(Activity entity);
}
