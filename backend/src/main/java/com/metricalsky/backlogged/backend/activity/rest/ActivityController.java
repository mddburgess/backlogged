package com.metricalsky.backlogged.backend.activity.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metricalsky.backlogged.backend.activity.dto.ActivityDto;
import com.metricalsky.backlogged.backend.activity.repository.ActivityRepository;
import com.metricalsky.backlogged.backend.activity.service.TimeActivityMapper;
import com.metricalsky.backlogged.backend.backlog.service.BacklogItemService;

@RestController
@RequestMapping("/api/backlog/{id}/activities")
public class ActivityController {

    @Autowired
    private TimeActivityMapper mapper;
    @Autowired
    private BacklogItemService backlogItemService;
    @Autowired
    private ActivityRepository activityRepository;

    @PostMapping
    public void create(@PathVariable("id") Integer backlogItemId, @RequestBody ActivityDto activityDto) {
        var backlogItem = backlogItemService.getById(backlogItemId);
        var entity = mapper.toEntity(activityDto);
        entity.setBacklogItem(backlogItem);
        activityRepository.save(entity);
    }
}
