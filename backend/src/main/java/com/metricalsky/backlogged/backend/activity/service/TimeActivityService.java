package com.metricalsky.backlogged.backend.activity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metricalsky.backlogged.backend.activity.dto.ActivityDto;
import com.metricalsky.backlogged.backend.activity.repository.ActivityRepository;
import com.metricalsky.backlogged.backend.backlog.repository.BacklogItemRepository;

@Service
public class TimeActivityService {

    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private BacklogItemRepository backlogItemRepository;

    private final TimeActivityMapper mapper = new TimeActivityMapper();

    @Transactional
    public void create(Integer backlogItemId, ActivityDto activityDto) {
        var backlogItem = backlogItemRepository.findById(backlogItemId)
                .orElseThrow();
        var entity = mapper.toEntity(activityDto);
        entity.setBacklogItem(backlogItem);
        activityRepository.save(entity);
    }
}
