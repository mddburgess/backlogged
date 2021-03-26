package com.metricalsky.backlogged.backend.activity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.metricalsky.backlogged.backend.activity.dto.ActivityData;
import com.metricalsky.backlogged.backend.activity.entity.Activity;
import com.metricalsky.backlogged.backend.activity.entity.ActivityType;
import com.metricalsky.backlogged.backend.activity.repository.ActivityRepository;
import com.metricalsky.backlogged.backend.library.entity.Title;

import static java.util.stream.Collectors.toList;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
public class ActivityService {

    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ActivityRepository activityRepository;

    public List<ActivityData> listActivities() {
        return activityRepository.findAll(Sort.by(DESC, "activityDate"))
                .stream()
                .map(activityMapper::fromEntity)
                .collect(toList());
    }

    public void createActivity(ActivityType type, Title title) {
        activityRepository.save(Activity.builder()
                .activityType(type)
                .title(title)
                .build());
    }
}
