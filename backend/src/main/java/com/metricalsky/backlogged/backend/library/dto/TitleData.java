package com.metricalsky.backlogged.backend.library.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class TitleData {

    private UUID token;
    private String name;
    private List<CopyData> copies;
}
