package com.metricalsky.backlogged.backend.library.dto;

import java.util.List;

import lombok.Data;

@Data
public class TitleDto {

    private String key;
    private String name;
    private List<CopyDto> copies;
}
