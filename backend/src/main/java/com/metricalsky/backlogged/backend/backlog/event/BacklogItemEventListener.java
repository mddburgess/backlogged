package com.metricalsky.backlogged.backend.backlog.event;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.metricalsky.backlogged.backend.activity.entity.StatusActivity;
import com.metricalsky.backlogged.backend.activity.repository.ActivityRepository;

@Component
public class BacklogItemEventListener {

    @Autowired
    private ActivityRepository activityRepository;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onUpdate(BacklogItemUpdatedEvent event) {
        if (event.previousState().getStatus() == event.currentState().getStatus()) {
            return;
        }

        var statusActivity = new StatusActivity();
        statusActivity.setBacklogItem(event.currentState());
        statusActivity.setActivityDate(ZonedDateTime.now());
        statusActivity.setFromStatus(event.previousState().getStatus());
        statusActivity.setToStatus(event.currentState().getStatus());

        activityRepository.save(statusActivity);
    }
}
