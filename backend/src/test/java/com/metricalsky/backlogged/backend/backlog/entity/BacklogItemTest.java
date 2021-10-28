package com.metricalsky.backlogged.backend.backlog.entity;

import javax.persistence.PersistenceException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DataJpaTest
class BacklogItemTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void givenEmptyEntity_whenPersist_thenPersistenceExceptionIsThrown() {
        assertThatExceptionOfType(PersistenceException.class)
                .isThrownBy(() -> entityManager.persistFlushFind(new BacklogItem()));
    }

    @Test
    void givenMinimalEntity_whenPersist_thenEntityIsPersisted() {
        var backlogItem = new BacklogItem();
        backlogItem.setType(BacklogItemType.VIDEO_GAME);
        backlogItem.setStatus(BacklogItemStatus.NEW);

        backlogItem = entityManager.persistFlushFind(backlogItem);
        assertThat(backlogItem.getId()).isPositive();
    }
}
