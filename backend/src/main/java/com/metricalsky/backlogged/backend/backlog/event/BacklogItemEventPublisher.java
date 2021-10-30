package com.metricalsky.backlogged.backend.backlog.event;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.metricalsky.backlogged.backend.backlog.entity.BacklogItem;

@Component
public class BacklogItemEventPublisher {

    @Autowired
    private ApplicationEventPublisher publisher;

    public void publishUpdateEvent(BacklogItem backlogItem) {
        var previousState = new BacklogItem();
        BeanUtils.copyProperties(backlogItem, previousState);
        publisher.publishEvent(new BacklogItemUpdatedEvent(previousState, backlogItem));
    }
}
