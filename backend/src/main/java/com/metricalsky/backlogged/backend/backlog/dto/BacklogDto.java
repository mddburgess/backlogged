package com.metricalsky.backlogged.backend.backlog.dto;

import lombok.Data;

import com.metricalsky.backlogged.backend.library.dto.TitleDto;

@Data
public class BacklogDto {

    private String key;
    private TitleDto title;
}
