package com.metricalsky.backlogged.backend.library.service;

import java.util.List;

import com.metricalsky.backlogged.backend.library.dto.TitleData;
import com.metricalsky.backlogged.backend.library.entity.Title;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TitleMapper {

    List<TitleData> fromEntities(List<Title> entities);

    TitleData fromEntity(Title entity);

    Title toEntity(TitleData data);

    @Mapping(target = "token", ignore = true)
    void patchEntity(@MappingTarget Title entity, TitleData data);
}
