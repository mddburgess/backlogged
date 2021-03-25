package com.metricalsky.backlogged.backend.activity.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metricalsky.backlogged.backend.activity.dto.ActivityData;
import com.metricalsky.backlogged.backend.activity.entity.Activity;
import com.metricalsky.backlogged.backend.activity.repository.ActivityRepository;
import com.metricalsky.backlogged.backend.library.entity.Title;

import static java.util.stream.Collectors.toList;
import static org.springframework.data.domain.Sort.Order.desc;
import static org.springframework.data.domain.Sort.by;

@Service
public class ActivityService {

    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ActivityRepository activityRepository;

    public List<ActivityData> listActivities() {
        return activityRepository.findAll(by(desc("activityDate")))
                .stream()
                .map(activityMapper::fromEntity)
                .collect(toList());
    }

    public void createActivity(String type, Title title) {
        var activity = new Activity();
        activity.setActivityType(type);
        activity.setActivityDate(OffsetDateTime.now());
        activity.setTitle(title);
        activityRepository.save(activity);
    }
}
