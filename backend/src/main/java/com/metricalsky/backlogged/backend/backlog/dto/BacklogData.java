package com.metricalsky.backlogged.backend.backlog.dto;

import lombok.Data;

import com.metricalsky.backlogged.backend.library.dto.TitleData;

@Data
public class BacklogData {

    private String key;
    private TitleData title;
}
