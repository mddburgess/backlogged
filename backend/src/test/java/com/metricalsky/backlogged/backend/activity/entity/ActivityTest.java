package com.metricalsky.backlogged.backend.activity.entity;

import java.time.ZonedDateTime;
import javax.persistence.PersistenceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.metricalsky.backlogged.backend.backlog.entity.BacklogItem;
import com.metricalsky.backlogged.backend.backlog.entity.BacklogItemStatus;
import com.metricalsky.backlogged.backend.backlog.entity.BacklogItemType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DataJpaTest
class ActivityTest {

    @Autowired
    private TestEntityManager entityManager;

    private BacklogItem backlogItem;

    @BeforeEach
    void beforeEach() {
        backlogItem = new BacklogItem();
        backlogItem.setType(BacklogItemType.VIDEO_GAME);
        backlogItem.setStatus(BacklogItemStatus.NEW);
        entityManager.persist(backlogItem);
    }

    @Test
    void givenEmptyEntity_whenPersist_thenPersistenceExceptionIsThrown() {
        var activity = new Activity();

        assertThatExceptionOfType(PersistenceException.class)
                .isThrownBy(() -> entityManager.persistFlushFind(activity));
    }

    @Test
    void givenMinimalEntity_whenPersist_thenEntityIsPersisted() {
        var activity = new Activity();
        activity.setBacklogItem(backlogItem);
        activity.setActivityDate(ZonedDateTime.now());

        activity = entityManager.persistFlushFind(activity);
        assertThat(activity.getId()).isPositive();
    }
}
