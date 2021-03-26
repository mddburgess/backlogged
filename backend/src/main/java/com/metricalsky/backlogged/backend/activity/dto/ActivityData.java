package com.metricalsky.backlogged.backend.activity.dto;

import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.metricalsky.backlogged.backend.activity.entity.ActivityType;
import com.metricalsky.backlogged.backend.library.dto.TitleData;

@Data
@AllArgsConstructor
public class ActivityData {

    private String key;
    private ActivityType type;
    private OffsetDateTime date;
    private TitleData title;
}
