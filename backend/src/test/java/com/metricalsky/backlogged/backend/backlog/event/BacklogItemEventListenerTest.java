package com.metricalsky.backlogged.backend.backlog.event;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.metricalsky.backlogged.backend.activity.entity.StatusActivity;
import com.metricalsky.backlogged.backend.activity.repository.ActivityRepository;
import com.metricalsky.backlogged.backend.backlog.entity.BacklogItem;
import com.metricalsky.backlogged.backend.backlog.entity.BacklogItemStatus;
import com.metricalsky.backlogged.backend.test.Answers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BacklogItemEventListenerTest {

    @InjectMocks
    private BacklogItemEventListener backlogItemEventListener;
    @Mock
    private ActivityRepository activityRepository;
    @Captor
    private ArgumentCaptor<StatusActivity> statusActivity;

    @Test
    void givenStatusChanged_whenOnUpdate_thenCreateStatusActivity() {
        var updateEvent = new BacklogItemUpdatedEvent(new BacklogItem(), new BacklogItem());
        updateEvent.previousState().setStatus(BacklogItemStatus.NEW);
        updateEvent.currentState().setStatus(BacklogItemStatus.ACTIVE);

        when(activityRepository.save(any(StatusActivity.class)))
                .thenAnswer(Answers.setEntityId());

        backlogItemEventListener.onUpdate(updateEvent);

        verify(activityRepository)
                .save(statusActivity.capture());

        assertThat(statusActivity.getValue())
                .hasNoNullFieldsOrProperties()
                .hasFieldOrPropertyWithValue("backlogItem", updateEvent.currentState())
                .hasFieldOrPropertyWithValue("fromStatus", BacklogItemStatus.NEW)
                .hasFieldOrPropertyWithValue("toStatus", BacklogItemStatus.ACTIVE);
    }

    @Test
    void givenStatusDidNotChange_whenOnUpdate_thenDoNothing() {
        var updateEvent = new BacklogItemUpdatedEvent(new BacklogItem(), new BacklogItem());
        updateEvent.previousState().setStatus(BacklogItemStatus.NEW);
        updateEvent.currentState().setStatus(BacklogItemStatus.NEW);

        backlogItemEventListener.onUpdate(updateEvent);

        verifyNoInteractions(activityRepository);
    }
}
