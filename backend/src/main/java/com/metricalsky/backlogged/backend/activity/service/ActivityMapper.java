package com.metricalsky.backlogged.backend.activity.service;

import com.metricalsky.backlogged.backend.activity.dto.ActivityDto;
import com.metricalsky.backlogged.backend.activity.entity.Activity;
import com.metricalsky.backlogged.backend.activity.entity.StatusActivity;
import com.metricalsky.backlogged.backend.activity.entity.TimeActivity;
import com.metricalsky.backlogged.backend.common.exception.NotImplementedException;

public class ActivityMapper {

    private final StatusActivityMapper statusActivityMapper = new StatusActivityMapper();
    private final TimeActivityMapper timeActivityMapper = new TimeActivityMapper();

    public ActivityDto toDto(Activity entity) {
        if (entity instanceof StatusActivity statusActivity) {
            return statusActivityMapper.toDto(statusActivity);
        }
        if (entity instanceof TimeActivity timeActivity) {
            return timeActivityMapper.toDto(timeActivity);
        }

        throw new NotImplementedException("Data mapping for {0} is not implemented.",
                entity.getClass());
    }
}
