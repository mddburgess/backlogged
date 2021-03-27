package com.metricalsky.backlogged.backend.it.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import com.metricalsky.backlogged.backend.activity.dto.ActivityData;
import com.metricalsky.backlogged.backend.activity.repository.ActivityRepository;
import com.metricalsky.backlogged.backend.library.repository.TitleRepository;

import static com.metricalsky.backlogged.backend.activity.test.ActivityFactory.createActivity;
import static com.metricalsky.backlogged.backend.library.test.TitleFactory.createTitle;
import static java.time.temporal.ChronoUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class ActivitiesApiIT {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private TitleRepository titleRepository;

    @Test
    void givenNoActivities_whenList_thenListIsEmpty() {
        activityRepository.deleteAll();

        var response = restTemplate.getForEntity("/api/activities", ActivityData[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEmpty();
    }

    @Test
    void givenNewActivity_whenList_thenListStartsWithActivity() {
        var title = titleRepository.save(createTitle());
        var activity = activityRepository.save(createActivity(title));

        var response = restTemplate.getForEntity("/api/activities", ActivityData[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
        assertThat(response.getBody()[0]).satisfies(activityData -> {
            assertThat(activityData.getKey()).isEqualTo(activity.getId().toString());
            assertThat(activityData.getType()).isEqualTo(activity.getType());
            assertThat(activityData.getDate()).isCloseToUtcNow(within(1, SECONDS));
            assertThat(activityData.getTitle()).satisfies(titleData -> {
                assertThat(titleData.getKey()).isEqualTo(title.getId().toString());
                assertThat(titleData.getName()).isEqualTo(title.getName());
            });
        });
    }
}
