package com.metricalsky.backlogged.backend.backlog.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.metricalsky.backlogged.backend.backlog.dto.BacklogItemDto;
import com.metricalsky.backlogged.backend.backlog.entity.BacklogItem;
import com.metricalsky.backlogged.backend.backlog.entity.BacklogItemStatus;
import com.metricalsky.backlogged.backend.backlog.entity.BacklogItemType;
import com.metricalsky.backlogged.backend.backlog.event.BacklogItemEventPublisher;
import com.metricalsky.backlogged.backend.backlog.repository.BacklogItemRepository;
import com.metricalsky.backlogged.backend.common.exception.ResourceNotFoundException;
import com.metricalsky.backlogged.backend.test.Answers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BacklogItemServiceTest {

    @InjectMocks
    private BacklogItemService service;

    @Mock
    private BacklogItemEventPublisher eventPublisher;
    @Mock
    private BacklogItemRepository repository;

    @Test
    void givenNoBacklogItems_whenList_thenReturnEmptyList() {
        when(repository.findAll())
                .thenReturn(List.of());

        assertThat(service.list())
                .isEmpty();
    }

    @Test
    void givenBacklogItem_whenList_thenReturnItemInList() {
        var backlogItem = new BacklogItem();
        backlogItem.setId(1);
        backlogItem.setName("Name");
        backlogItem.setType(BacklogItemType.VIDEO_GAME);
        backlogItem.setStatus(BacklogItemStatus.NEW);

        when(repository.findAll())
                .thenReturn(List.of(backlogItem));

        assertThat(service.list())
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("activityTime")
                .isEqualTo(List.of(backlogItem));
    }

    @Test
    void givenDto_whenCreate_thenReturnCreatedItem() {
        var dto = new BacklogItemDto();
        dto.setName("Name");
        dto.setType(BacklogItemType.VIDEO_GAME);
        dto.setStatus(BacklogItemStatus.NEW);

        when(repository.save(any(BacklogItem.class)))
                .thenAnswer(Answers.setEntityId(1));

        assertThat(service.create(dto))
                .hasFieldOrPropertyWithValue("id", 1)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(dto);
    }

    @Test
    void givenExists_whenRetrieve_thenReturnBacklogItem() {
        var entity = new BacklogItem();
        entity.setId(1);
        entity.setName("Name");
        entity.setType(BacklogItemType.VIDEO_GAME);
        entity.setStatus(BacklogItemStatus.NEW);

        when(repository.findDetailedById(1))
                .thenReturn(Optional.of(entity));

        assertThat(service.retrieve(1))
                .usingRecursiveComparison()
                .ignoringFields("activityTime")
                .isEqualTo(entity);
    }

    @Test
    void givenNotExists_whenRetrieve_thenThrowResourceNotFoundException() {
        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> service.retrieve(1));

        verify(repository)
                .findDetailedById(1);
    }

    @Test
    void givenExists_whenUpdate_thenReturnUpdatedItem() {
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

        when(repository.findById(1))
                .thenReturn(Optional.of(entity));

        assertThat(service.update(1, dto))
                .hasFieldOrPropertyWithValue("id", 1)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(dto);

        verify(eventPublisher)
                .publishUpdateEvent(entity);
    }

    @Test
    void givenNotExists_whenUpdate_thenThrowResourceNotFoundException() {
        var dto = new BacklogItemDto();

        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> service.update(1, dto));

        verifyNoInteractions(eventPublisher);
    }

    @Test
    void givenExists_whenDelete_thenDeleteFromRepository() {
        var entity = new BacklogItem();

        when(repository.findById(1))
                .thenReturn(Optional.of(entity));

        service.delete(1);

        verify(repository)
                .delete(entity);
    }

    @Test
    void givenNotExists_whenDelete_thenThrowResourceNotFoundException() {
        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> service.delete(1));

        verify(repository)
                .findById(1);
        verifyNoMoreInteractions(repository);
    }
}
