package com.metricalsky.backlogged.backend.activity.dto;

import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.metricalsky.backlogged.backend.library.dto.TitleData;

@Data
@AllArgsConstructor
public class ActivityData {

    private String key;
    private String type;
    private OffsetDateTime date;
    private TitleData title;
}
