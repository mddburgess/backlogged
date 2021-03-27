package com.metricalsky.backlogged.backend.activity.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import com.metricalsky.backlogged.backend.activity.dto.ActivityData;
import com.metricalsky.backlogged.backend.activity.repository.ActivityRepository;
import com.metricalsky.backlogged.backend.library.dto.TitleData;

import static com.metricalsky.backlogged.backend.activity.entity.ActivityType.ADD_TO_BACKLOG;
import static com.metricalsky.backlogged.backend.activity.test.ActivityFactory.createActivity;
import static com.metricalsky.backlogged.backend.library.test.TitleFactory.createTitle;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActivityServiceTest {

    @InjectMocks
    private ActivityService activityService;

    @Mock
    private ActivityRepository activityRepository;

    @Test
    void listIsEmptyWhenRepositoryIsEmpty() {
        when(activityRepository.findAll(any(Sort.class)))
                .thenReturn(emptyList());

        var actual = activityService.listActivities();

        assertThat(actual).isEmpty();
    }

    @Test
    void listMapsEntitiesToDTOs() {
        var title = createTitle(1, "name");
        var activity = createActivity(2, ADD_TO_BACKLOG, OffsetDateTime.MAX, title);
        when(activityRepository.findAll(any(Sort.class)))
                .thenReturn(List.of(activity));

        var actualList = activityService.listActivities();

        var expectedActivity = ActivityData.builder()
                .key("2")
                .type(ADD_TO_BACKLOG)
                .date(OffsetDateTime.MAX)
                .title(createTitleData())
                .build();
        assertThat(actualList).containsExactly(expectedActivity);
    }

    private static TitleData createTitleData() {
        var titleData = new TitleData();
        titleData.setKey("1");
        titleData.setName("name");
        return titleData;
    }
}
