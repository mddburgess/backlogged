package com.metricalsky.backlogged.backend.library.repository;

import java.util.UUID;

import com.metricalsky.backlogged.backend.library.entity.Title;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TitleRepositoryTest {

    private static final UUID NIL_UUID = new UUID(0, 0);

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private TitleRepository repository;

    @Test
    void givenExists_whenFindByToken_thenReturnTitle() {
        var title = new Title();
        title.setToken(UUID.randomUUID());
        title.setName(RandomStringUtils.randomAlphabetic(10));
        title = entityManager.persistFlushFind(title);

        assertThat(repository.findByToken(title.getToken()))
                .hasValue(title);
    }

    @Test
    void givenNotExists_whenFindByToken_thenReturnEmpty() {
        var title = new Title();
        title.setToken(UUID.randomUUID());
        title.setName(RandomStringUtils.randomAlphabetic(10));
        entityManager.persistFlushFind(title);

        assertThat(repository.findByToken(NIL_UUID))
                .isEmpty();
    }
}
