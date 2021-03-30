package com.metricalsky.backlogged.backend.activity.service;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.metricalsky.backlogged.backend.activity.dto.ActivityDto;
import com.metricalsky.backlogged.backend.activity.entity.Activity;
import com.metricalsky.backlogged.backend.activity.entity.ActivityType;
import com.metricalsky.backlogged.backend.activity.repository.ActivityRepository;
import com.metricalsky.backlogged.backend.library.entity.Title;

import static java.util.stream.Collectors.toList;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
public class ActivityService {

    private final ActivityMapper activityMapper;
    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository) {
        this.activityMapper = Mappers.getMapper(ActivityMapper.class);
        this.activityRepository = activityRepository;
    }

    public List<ActivityDto> listActivities() {
        return activityRepository.findAll(Sort.by(DESC, "date"))
                .stream()
                .map(activityMapper::fromEntity)
                .collect(toList());
    }

    public void createActivity(ActivityType type, Title title) {
        activityRepository.save(Activity.builder()
                .type(type)
                .title(title)
                .build());
    }
}
