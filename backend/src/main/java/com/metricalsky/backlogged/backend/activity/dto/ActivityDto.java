package com.metricalsky.backlogged.backend.activity.dto;

import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Data;

import com.metricalsky.backlogged.backend.activity.entity.ActivityType;
import com.metricalsky.backlogged.backend.library.dto.TitleDto;

@Data
@Builder
public class ActivityDto {

    private String key;
    private ActivityType type;
    private OffsetDateTime date;
    private TitleDto title;
}
