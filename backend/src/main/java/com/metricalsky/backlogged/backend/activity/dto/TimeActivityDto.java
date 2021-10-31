package com.metricalsky.backlogged.backend.activity.dto;

import java.time.Duration;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonTypeName("TIME")
public class TimeActivityDto extends ActivityDto {

    private Duration duration;
}
