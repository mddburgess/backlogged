package com.metricalsky.backlogged.backend.backlog.service;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.metricalsky.backlogged.backend.activity.entity.TimeActivity;
import com.metricalsky.backlogged.backend.backlog.dto.BacklogItemDto;
import com.metricalsky.backlogged.backend.backlog.entity.BacklogItem;
import com.metricalsky.backlogged.backend.backlog.entity.BacklogItemStatus;
import com.metricalsky.backlogged.backend.backlog.entity.BacklogItemType;

import static org.assertj.core.api.Assertions.assertThat;

class BacklogItemMapperTest {

    private final BacklogItemMapper mapper = new BacklogItemMapper();

    @Test
    void givenEmptyDto_whenToEntity_thenReturnEmptyEntity() {
        assertThat(mapper.toEntity(new BacklogItemDto()))
                .hasAllNullFieldsOrProperties();
    }

    @Test
    void givenDto_whenToEntity_thenReturnEquivalentEntity() {
        var dto = new BacklogItemDto();
        dto.setId(1);
        dto.setName("Name");
        dto.setType(BacklogItemType.VIDEO_GAME);
        dto.setStatus(BacklogItemStatus.NEW);

        var entity = mapper.toEntity(dto);

        assertThat(entity)
                .usingRecursiveComparison()
                .ignoringFields("activities")
                .isEqualTo(dto);
    }

    @Test
    void givenEmptyEntity_whenToDto_thenReturnEmptyDto() {
        assertThat(mapper.toDto(new BacklogItem()))
                .hasAllNullFieldsOrProperties();
    }

    @Test
    void givenEntity_whenToDto_thenReturnEquivalentDto() {
        var entity = new BacklogItem();
        entity.setId(1);
        entity.setName("Name");
        entity.setType(BacklogItemType.VIDEO_GAME);
        entity.setStatus(BacklogItemStatus.NEW);

        var activity = new TimeActivity();
        activity.setDuration(Duration.ofHours(1));
        entity.setActivities(List.of(activity));

        var dto = mapper.toDto(entity);

        assertThat(dto)
                .usingRecursiveComparison()
                .ignoringFields("activityTime")
                .isEqualTo(entity);
        assertThat(dto)
                .hasFieldOrPropertyWithValue("activityTime", Duration.ofHours(1));
    }

    @Test
    void givenDto_whenPatchEntity_thenEntityIsPatched() {
        var entity = new BacklogItem();
        entity.setId(1);
        entity.setName("Name");
        entity.setType(BacklogItemType.VIDEO_GAME);
        entity.setStatus(BacklogItemStatus.NEW);

        var dto = new BacklogItemDto();
        dto.setId(2);
        dto.setName("DTO Name");
        dto.setType(BacklogItemType.MOVIE);
        dto.setStatus(BacklogItemStatus.DONE);

        mapper.patchEntity(entity, dto);

        assertThat(entity)
                .hasFieldOrPropertyWithValue("id", 1)
                .usingRecursiveComparison()
                .ignoringFields("id", "activities")
                .isEqualTo(dto);
    }
}
