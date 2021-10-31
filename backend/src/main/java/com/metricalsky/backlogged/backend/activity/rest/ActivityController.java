package com.metricalsky.backlogged.backend.activity.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metricalsky.backlogged.backend.activity.dto.TimeActivityDto;
import com.metricalsky.backlogged.backend.activity.service.TimeActivityService;

@RestController
@RequestMapping("/api/backlog/{id}/activities")
public class ActivityController {

    private final TimeActivityService timeActivityService;

    public ActivityController(TimeActivityService timeActivityService) {
        this.timeActivityService = timeActivityService;
    }

    @PostMapping
    public void create(
            @PathVariable("id") Integer backlogItemId,
            @RequestBody TimeActivityDto activityDto
    ) {
        timeActivityService.create(backlogItemId, activityDto);
    }
}
