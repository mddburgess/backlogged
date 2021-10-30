package com.metricalsky.backlogged.backend.backlog.event;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import com.metricalsky.backlogged.backend.backlog.entity.BacklogItem;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BacklogItemEventPublisherTest {

    @InjectMocks
    private BacklogItemEventPublisher backlogItemEventPublisher;
    @Mock
    private ApplicationEventPublisher applicationEventPublisher;
    @Captor
    private ArgumentCaptor<BacklogItemUpdatedEvent> updateEvent;

    @Test
    void givenBacklogItem_whenPublishUpdateEvent_thenCallEventPublisher() {
        var backlogItem = new BacklogItem();

        backlogItemEventPublisher.publishUpdateEvent(backlogItem);

        verify(applicationEventPublisher)
                .publishEvent(updateEvent.capture());

        var previousState = updateEvent.getValue().previousState();
        var currentState = updateEvent.getValue().currentState();

        assertThat(previousState)
                .isNotSameAs(currentState)
                .usingRecursiveComparison()
                .isEqualTo(currentState);
        assertThat(currentState)
                .isSameAs(backlogItem);
    }
}
