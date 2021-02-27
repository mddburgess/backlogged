package com.metricalsky.backlogged.backend.library.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.metricalsky.backlogged.backend.library.dto.TitleData;
import com.metricalsky.backlogged.backend.library.entity.Title;

@Mapper(componentModel = "spring")
public interface TitleMapper {

    @Mapping(source = "id", target = "key")
    TitleData fromEntity(Title entity);

    Title toEntity(TitleData data);

    void patchEntity(@MappingTarget Title entity, TitleData data);
}
