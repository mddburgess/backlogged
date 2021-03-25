package com.metricalsky.backlogged.backend.activity.service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.metricalsky.backlogged.backend.activity.dto.ActivityData;
import com.metricalsky.backlogged.backend.library.dto.TitleData;
import com.metricalsky.backlogged.backend.library.entity.Title;

@Service
public class ActivityService {

    private List<ActivityData> activities = new ArrayList<>();

    public List<ActivityData> listActivities() {
        return activities;
    }

    public void createActivity(String type, Title title) {
        var titleData = new TitleData();
        titleData.setKey(title.getId().toString());
        titleData.setName(title.getName());

        var activityData = new ActivityData(type, ZonedDateTime.now(), titleData);
        activities.add(activityData);
    }
}
