package com.metricalsky.backlogged.backend.library.service;

import com.metricalsky.backlogged.backend.library.dto.TitleData;
import com.metricalsky.backlogged.backend.library.entity.Title;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TitleMapper {

    @Mapping(source = "id", target = "key")
    TitleData fromEntity(Title entity);

    Title toEntity(TitleData data);

    void patchEntity(@MappingTarget Title entity, TitleData data);
}
