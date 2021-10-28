package com.metricalsky.backlogged.backend.activity.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import com.metricalsky.backlogged.backend.activity.entity.StatusActivity;
import com.metricalsky.backlogged.backend.activity.repository.ActivityRepository;
import com.metricalsky.backlogged.backend.backlog.entity.BacklogItem;
import com.metricalsky.backlogged.backend.backlog.entity.BacklogItemStatus;
import com.metricalsky.backlogged.backend.common.entity.IdentifiableEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatusActivityServiceTest {

    @InjectMocks
    private StatusActivityService service;
    @Mock
    private ActivityRepository repository;

    @Test
    void givenBacklogItemAndStatus_whenCreate_thenReturnCreatedActivity() {
        var backlogItem = new BacklogItem();
        backlogItem.setStatus(BacklogItemStatus.DONE);
        var fromStatus = BacklogItemStatus.NEW;

        when(repository.save(any(StatusActivity.class)))
                .thenAnswer(setEntityId(1));

        assertThat(service.create(backlogItem, fromStatus))
                .hasNoNullFieldsOrProperties()
                .hasFieldOrPropertyWithValue("id", 1)
                .hasFieldOrPropertyWithValue("backlogItem", backlogItem)
                .hasFieldOrPropertyWithValue("fromStatus", BacklogItemStatus.NEW)
                .hasFieldOrPropertyWithValue("toStatus", BacklogItemStatus.DONE);
    }

    private static Answer<IdentifiableEntity> setEntityId(Integer id) {
        return invocation -> {
            var entity = invocation.getArgument(0, IdentifiableEntity.class);
            entity.setId(id);
            return entity;
        };
    }
}
