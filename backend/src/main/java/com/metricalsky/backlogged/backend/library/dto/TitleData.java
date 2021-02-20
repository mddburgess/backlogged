package com.metricalsky.backlogged.backend.library.dto;

import java.util.List;

import lombok.Data;

@Data
public class TitleData {

    private String key;
    private String name;
    private List<CopyData> copies;
}
