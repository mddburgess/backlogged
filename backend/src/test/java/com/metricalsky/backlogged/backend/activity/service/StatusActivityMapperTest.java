package com.metricalsky.backlogged.backend.activity.service;

import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

import com.metricalsky.backlogged.backend.activity.entity.StatusActivity;
import com.metricalsky.backlogged.backend.backlog.entity.BacklogItemStatus;

import static org.assertj.core.api.Assertions.assertThat;

class StatusActivityMapperTest {

    private final StatusActivityMapper mapper = new StatusActivityMapper();

    @Test
    void givenEmptyEntity_whenToDto_thenReturnEmptyDto() {
        assertThat(mapper.toDto(new StatusActivity()))
                .hasAllNullFieldsOrProperties();
    }

    @Test
    void givenEntity_whenToDto_thenReturnEquivalentDto() {
        var entity = new StatusActivity();
        entity.setId(1);
        entity.setActivityDate(ZonedDateTime.now());
        entity.setFromStatus(BacklogItemStatus.NEW);
        entity.setToStatus(BacklogItemStatus.ACTIVE);

        assertThat(mapper.toDto(entity))
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }
}
