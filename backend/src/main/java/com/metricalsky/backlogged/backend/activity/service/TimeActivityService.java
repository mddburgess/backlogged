package com.metricalsky.backlogged.backend.activity.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metricalsky.backlogged.backend.activity.dto.TimeActivityDto;
import com.metricalsky.backlogged.backend.activity.repository.ActivityRepository;
import com.metricalsky.backlogged.backend.backlog.repository.BacklogItemRepository;

@Service
public class TimeActivityService {

    private final ActivityRepository activityRepository;
    private final BacklogItemRepository backlogItemRepository;
    private final TimeActivityMapper mapper = new TimeActivityMapper();

    public TimeActivityService(ActivityRepository activityRepository,
            BacklogItemRepository backlogItemRepository) {
        this.activityRepository = activityRepository;
        this.backlogItemRepository = backlogItemRepository;
    }

    @Transactional
    public void create(Integer backlogItemId, TimeActivityDto activityDto) {
        var backlogItem = backlogItemRepository.findById(backlogItemId)
                .orElseThrow();
        var entity = mapper.toEntity(activityDto);
        entity.setBacklogItem(backlogItem);
        activityRepository.save(entity);
    }
}
