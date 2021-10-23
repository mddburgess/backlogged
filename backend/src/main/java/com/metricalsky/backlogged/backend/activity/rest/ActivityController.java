package com.metricalsky.backlogged.backend.activity.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metricalsky.backlogged.backend.activity.dto.ActivityDto;
import com.metricalsky.backlogged.backend.activity.service.TimeActivityService;

@RestController
@RequestMapping("/api/backlog/{id}/activities")
public class ActivityController {

    @Autowired
    private TimeActivityService timeActivityService;

    @PostMapping
    public void create(@PathVariable("id") Integer backlogItemId, @RequestBody ActivityDto activityDto) {
        timeActivityService.create(backlogItemId, activityDto);
    }
}
