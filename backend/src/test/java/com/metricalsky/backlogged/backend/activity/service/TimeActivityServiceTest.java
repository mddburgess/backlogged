package com.metricalsky.backlogged.backend.activity.service;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.metricalsky.backlogged.backend.activity.dto.TimeActivityDto;
import com.metricalsky.backlogged.backend.activity.entity.TimeActivity;
import com.metricalsky.backlogged.backend.activity.repository.ActivityRepository;
import com.metricalsky.backlogged.backend.backlog.entity.BacklogItem;
import com.metricalsky.backlogged.backend.backlog.repository.BacklogItemRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TimeActivityServiceTest {

    @InjectMocks
    private TimeActivityService timeActivityService;

    @Mock
    private ActivityRepository activityRepository;
    @Mock
    private BacklogItemRepository backlogItemRepository;

    @Test
    void givenActivityDto_whenCreate_thenSaveTimeActivity() {
        var backlogItem = new BacklogItem();
        var activityDto = new TimeActivityDto();

        when(backlogItemRepository.findById(1))
                .thenReturn(Optional.of(backlogItem));

        timeActivityService.create(1, activityDto);

        verify(activityRepository)
                .save(any(TimeActivity.class));
    }
}
