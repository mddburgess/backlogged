package com.metricalsky.backlogged.backend.backlog.event;

import com.metricalsky.backlogged.backend.backlog.entity.BacklogItem;

public record BacklogItemUpdatedEvent(
        BacklogItem previousState,
        BacklogItem currentState
) {

}
