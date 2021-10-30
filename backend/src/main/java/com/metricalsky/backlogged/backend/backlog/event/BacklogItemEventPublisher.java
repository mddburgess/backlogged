package com.metricalsky.backlogged.backend.backlog.event;

import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.metricalsky.backlogged.backend.backlog.entity.BacklogItem;

@Component
public class BacklogItemEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public BacklogItemEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishUpdateEvent(BacklogItem backlogItem) {
        var previousState = new BacklogItem();
        BeanUtils.copyProperties(backlogItem, previousState);
        eventPublisher.publishEvent(new BacklogItemUpdatedEvent(previousState, backlogItem));
    }
}
