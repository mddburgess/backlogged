package com.metricalsky.backlogged.backend.activity.dto;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.metricalsky.backlogged.backend.library.dto.TitleData;

@Data
@AllArgsConstructor
public class ActivityData {

    private String type;
    private ZonedDateTime date;
    private TitleData title;
}
