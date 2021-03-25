package com.metricalsky.backlogged.backend.activity.dto;

import lombok.Value;

import com.metricalsky.backlogged.backend.library.dto.TitleData;

@Value
public class ActivityData {

    String type;
    String date;
    TitleData title;
}
