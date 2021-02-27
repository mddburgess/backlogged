package com.metricalsky.backlogged.backend.library.entity;

import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.metricalsky.backlogged.backend.library.repository.TitleRepository;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TitlePersistenceTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private TitleRepository repository;

    @Test
    void givenTitleWithCopy_whenCreateTitle_thenCopyIsPersisted() {
        var title = repository.saveAndFlush(createTitle());

        assertThat(title.getCopies())
                .isNotEmpty()
                .allSatisfy(c -> {
                    assertThat(c.getId()).isNotNull();
                    assertThat(entityManager.find(Copy.class, c.getId())).isEqualTo(c);
                });
    }

    @Test
    void givenTitleWithCopy_whenDeleteTitle_thenCopyIsDeleted() {
        var title = entityManager.persistFlushFind(createTitle());
        assertThat(title.getCopies()).extracting(Copy::getId).doesNotContainNull();

        repository.delete(title);

        assertThat(entityManager.find(Title.class, title.getId())).isNull();
        assertThat(title.getCopies())
                .isNotEmpty()
                .allSatisfy(c -> assertThat(entityManager.find(Copy.class, c.getId())).isNull());
    }

    @Test
    void givenNewCopy_whenUpdateTitle_thenNewCopyIsPersisted() {
        var title = entityManager.persistFlushFind(createTitle());
        assertThat(title.getCopies()).extracting(Copy::getId).doesNotContainNull();

        title.getCopies().add(createCopy());
        repository.saveAndFlush(title);

        assertThat(title.getCopies())
                .isNotEmpty()
                .allSatisfy(c -> {
                    assertThat(c.getId()).isNotNull();
                    assertThat(entityManager.find(Copy.class, c.getId())).isEqualTo(c);
                });
    }

    @Test
    void givenModifiedCopy_whenUpdateTitle_thenCopyIsUpdated() {
        var title = entityManager.persistFlushFind(createTitle());
        assertThat(title.getCopies()).extracting(Copy::getId).doesNotContainNull();

        var oldCopyPlatforms = title.getCopies().stream().collect(toMap(Copy::getId, Copy::getPlatform));
        title.getCopies().forEach(copy -> copy.setPlatform(RandomStringUtils.randomAlphabetic(10)));

        repository.saveAndFlush(title);

        assertThat(title.getCopies())
                .isNotEmpty()
                .allSatisfy(c -> {
                    var copy = entityManager.find(Copy.class, c.getId());
                    assertThat(copy).isEqualTo(c);
                    assertThat(copy.getPlatform()).isNotEqualTo(oldCopyPlatforms.get(copy.getId()));
                });
    }

    @Test
    void givenRemovedCopy_whenUpdateTitle_thenOnlyCopyIsDeleted() {
        var title = entityManager.persistFlushFind(createTitle());
        assertThat(title.getCopies()).extracting(Copy::getId).doesNotContainNull();

        var copyIds = title.getCopies().stream().map(Copy::getId).collect(toList());
        title.getCopies().clear();

        repository.saveAndFlush(title);

        assertThat(entityManager.find(Title.class, title.getId())).isEqualTo(title);
        assertThat(copyIds)
                .isNotEmpty()
                .allSatisfy(id -> assertThat(entityManager.find(Copy.class, id)).isNull());
    }

    private static Title createTitle() {
        var title = new Title();
        title.setName(RandomStringUtils.randomAlphabetic(10));
        title.setCopies(Lists.newArrayList(createCopy()));
        return title;
    }

    private static Copy createCopy() {
        var copy = new Copy();
        copy.setPlatform(RandomStringUtils.randomAlphabetic(10));
        copy.setService(RandomStringUtils.randomAlphabetic(10));
        return copy;
    }
}
