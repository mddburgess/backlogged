package com.metricalsky.backlogged.backend.activity.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.metricalsky.backlogged.backend.activity.dto.ActivityData;
import com.metricalsky.backlogged.backend.activity.entity.Activity;
import com.metricalsky.backlogged.backend.library.service.TitleMapper;

@Mapper(uses = TitleMapper.class)
public interface ActivityMapper {

    @Mapping(source = "id", target = "key")
    @Mapping(source = "activityType", target = "type")
    @Mapping(source = "activityDate", target = "date")
    ActivityData fromEntity(Activity entity);
}
