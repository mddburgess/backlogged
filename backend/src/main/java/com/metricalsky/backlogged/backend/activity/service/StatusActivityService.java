package com.metricalsky.backlogged.backend.activity.service;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metricalsky.backlogged.backend.activity.entity.StatusActivity;
import com.metricalsky.backlogged.backend.activity.repository.ActivityRepository;
import com.metricalsky.backlogged.backend.backlog.entity.BacklogItem;
import com.metricalsky.backlogged.backend.backlog.entity.BacklogItemStatus;

@Service
public class StatusActivityService {

    @Autowired
    private ActivityRepository repository;

    public StatusActivity create(BacklogItem backlogItem, BacklogItemStatus fromStatus) {
        var statusActivity = new StatusActivity();
        statusActivity.setBacklogItem(backlogItem);
        statusActivity.setActivityDate(ZonedDateTime.now());
        statusActivity.setFromStatus(fromStatus);
        statusActivity.setToStatus(backlogItem.getStatus());
        return repository.save(statusActivity);
    }
}
