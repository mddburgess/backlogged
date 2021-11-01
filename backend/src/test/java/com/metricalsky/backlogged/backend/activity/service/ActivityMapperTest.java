package com.metricalsky.backlogged.backend.activity.service;

import org.junit.jupiter.api.Test;

import com.metricalsky.backlogged.backend.activity.dto.StatusActivityDto;
import com.metricalsky.backlogged.backend.activity.dto.TimeActivityDto;
import com.metricalsky.backlogged.backend.activity.entity.Activity;
import com.metricalsky.backlogged.backend.activity.entity.StatusActivity;
import com.metricalsky.backlogged.backend.activity.entity.TimeActivity;
import com.metricalsky.backlogged.backend.common.exception.NotImplementedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ActivityMapperTest {

    private final ActivityMapper mapper = new ActivityMapper();

    @Test
    void givenStatusActivity_whenToDto_thenReturnStatusActivityDto() {
        var entity = new StatusActivity();

        assertThat(mapper.toDto(entity))
                .isInstanceOf(StatusActivityDto.class);
    }

    @Test
    void givenTimeActivity_whenToDto_thenReturnTimeActivityDto() {
        var entity = new TimeActivity();

        assertThat(mapper.toDto(entity))
                .isInstanceOf(TimeActivityDto.class);
    }

    @Test
    void givenUnmapped_whenToDto_thenThrowNotImplementedException() {
        var entity = new Activity();

        assertThatExceptionOfType(NotImplementedException.class)
                .isThrownBy(() -> mapper.toDto(entity));
    }
}
