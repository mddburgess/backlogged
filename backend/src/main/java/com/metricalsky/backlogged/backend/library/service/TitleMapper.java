package com.metricalsky.backlogged.backend.library.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.metricalsky.backlogged.backend.library.dto.TitleDto;
import com.metricalsky.backlogged.backend.library.entity.Title;

@Mapper(componentModel = "spring")
public interface TitleMapper {

    @Mapping(source = "id", target = "key")
    TitleDto fromEntity(Title entity);

    Title toEntity(TitleDto data);

    void patchEntity(@MappingTarget Title entity, TitleDto data);
}
