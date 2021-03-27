package com.metricalsky.backlogged.backend.activity.test;

import java.time.OffsetDateTime;

import com.metricalsky.backlogged.backend.activity.entity.Activity;
import com.metricalsky.backlogged.backend.activity.entity.ActivityType;
import com.metricalsky.backlogged.backend.library.entity.Title;

import static com.metricalsky.backlogged.backend.activity.entity.ActivityType.ADD_TO_LIBRARY;

public final class ActivityFactory {

    private ActivityFactory() {

    }

    public static Activity createActivity(Title title) {
        return createActivity(null, ADD_TO_LIBRARY, OffsetDateTime.now(), title);
    }

    public static Activity createActivity(Integer id, ActivityType type, OffsetDateTime date, Title title) {
        return Activity.builder()
                .id(id)
                .type(type)
                .date(date)
                .title(title)
                .build();
    }
}
