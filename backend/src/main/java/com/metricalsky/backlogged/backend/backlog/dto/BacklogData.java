package com.metricalsky.backlogged.backend.backlog.dto;

import com.metricalsky.backlogged.backend.library.dto.TitleData;
import lombok.Data;

@Data
public class BacklogData {

    private String key;
    private TitleData title;
}
