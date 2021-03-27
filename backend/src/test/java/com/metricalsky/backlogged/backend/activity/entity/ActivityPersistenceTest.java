package com.metricalsky.backlogged.backend.activity.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static com.metricalsky.backlogged.backend.activity.test.ActivityFactory.createActivity;
import static com.metricalsky.backlogged.backend.library.test.TitleFactory.createTitle;
import static org.assertj.core.api.Assertions.assertThatNoException;

@DataJpaTest
class ActivityPersistenceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void activityPersistsToDatabase() {
        var title = entityManager.persist(createTitle());
        var activity = createActivity(title);

        assertThatNoException()
                .isThrownBy(() -> entityManager.persist(activity));
    }
}
