package com.metricalsky.backlogged.backend.activity.test;

import com.metricalsky.backlogged.backend.activity.entity.Activity;
import com.metricalsky.backlogged.backend.activity.entity.ActivityType;
import com.metricalsky.backlogged.backend.library.entity.Title;

public final class ActivityFactory {

    private ActivityFactory() {

    }

    public static Activity createActivity(Title title) {
        var activity = new Activity();
        activity.setActivityType(ActivityType.ADD_TO_LIBRARY);
        activity.setTitle(title);
        return activity;
    }
}
