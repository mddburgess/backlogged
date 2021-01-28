package com.metricalsky.backlogged.backend.library.service;

import com.metricalsky.backlogged.backend.library.dto.TitleData;
import com.metricalsky.backlogged.backend.library.entity.Title;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TitleMapper {

    TitleData fromEntity(Title entity);

    Title toEntity(TitleData data);

    @Mapping(target = "token", ignore = true)
    void patchEntity(@MappingTarget Title entity, TitleData data);
}
