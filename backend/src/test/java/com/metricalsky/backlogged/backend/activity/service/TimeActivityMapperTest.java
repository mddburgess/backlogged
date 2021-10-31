package com.metricalsky.backlogged.backend.activity.service;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import com.metricalsky.backlogged.backend.activity.dto.TimeActivityDto;
import com.metricalsky.backlogged.backend.activity.entity.TimeActivity;

import static org.assertj.core.api.Assertions.assertThat;

class TimeActivityMapperTest {

    private final TimeActivityMapper mapper = new TimeActivityMapper();

    @Test
    void givenEmptyDto_whenToEntity_thenReturnEmptyEntity() {
        assertThat(mapper.toEntity(new TimeActivityDto()))
                .hasAllNullFieldsOrPropertiesExcept("activityDate");
    }

    @Test
    void givenDto_whenToEntity_thenReturnEquivalentEntity() {
        var dto = new TimeActivityDto();
        dto.setDuration(Duration.ofHours(1));

        var entity = mapper.toEntity(dto);

        assertThat(entity)
                .usingRecursiveComparison()
                .ignoringFields("id", "backlogItem", "activityDate")
                .isEqualTo(dto);
        assertThat(entity.getActivityDate())
                .isNotNull();
    }

    @Test
    void givenEmptyEntity_whenToDto_thenReturnEmptyDto() {
        assertThat(mapper.toDto(new TimeActivity()))
                .hasAllNullFieldsOrProperties();
    }

    @Test
    void givenEntity_whenToDto_thenReturnEquivalentDto() {
        var entity = new TimeActivity();
        entity.setDuration(Duration.ofHours(1));

        var dto = mapper.toDto(entity);

        assertThat(dto)
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }
}
