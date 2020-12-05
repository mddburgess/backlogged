package com.metricalsky.backlogged.backend.library.repository;

import java.util.List;
import java.util.UUID;

import com.metricalsky.backlogged.backend.library.entity.Copy;
import com.metricalsky.backlogged.backend.library.entity.Title;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TitleRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private TitleRepository repository;

    @Test
    void givenTitle_whenSave_thenTitleIsSaved() {
        var copy = new Copy();
        copy.setPlatform(RandomStringUtils.randomAlphabetic(10));
        copy.setService(RandomStringUtils.randomAlphabetic(10));

        var title = new Title();
        title.setToken(UUID.randomUUID());
        title.setName(RandomStringUtils.randomAlphabetic(10));
        title.setCopies(List.of(copy));
        title.linkCopies();

        repository.save(title);

        assertThat(entityManager.find(Title.class, title.getId()))
                .isNotNull();
        assertThat(entityManager.find(Copy.class, copy.getId()))
                .isNotNull();
    }
}
