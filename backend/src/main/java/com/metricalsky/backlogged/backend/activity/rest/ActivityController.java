package com.metricalsky.backlogged.backend.activity.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metricalsky.backlogged.backend.activity.dto.ActivityData;
import com.metricalsky.backlogged.backend.activity.service.ActivityService;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping
    public List<ActivityData> list() {
        return activityService.listActivities();
    }
}
