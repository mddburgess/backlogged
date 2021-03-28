package com.metricalsky.backlogged.backend.it.api;

import java.net.URI;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;

import com.metricalsky.backlogged.backend.backlog.dto.BacklogDto;
import com.metricalsky.backlogged.backend.backlog.repository.BacklogRepository;
import com.metricalsky.backlogged.backend.library.dto.TitleDto;
import com.metricalsky.backlogged.backend.library.entity.Title;
import com.metricalsky.backlogged.backend.library.repository.TitleRepository;
import com.metricalsky.backlogged.test.extensions.AbortOnTestFailure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestInstance(PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@AbortOnTestFailure
class BacklogsApiIT {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private BacklogRepository backlogRepository;
    @Autowired
    private TitleRepository titleRepository;

    private Title title;
    private BacklogDto backlogDto;

    @BeforeAll
    void beforeAll() {
        backlogRepository.deleteAll();

        title = new Title();
        title.setName(RandomStringUtils.randomAlphabetic(10));
        titleRepository.save(title);
        assertThat(title.getId()).isNotNull();
    }

    @AfterAll
    void afterAll() {
        backlogRepository.deleteAll();
    }

    @Test
    @Order(1)
    void givenNoBacklogs_whenListBacklogs_thenExpectEmptyList() {
        var response = restTemplate.getForEntity("/api/backlogs", BacklogDto[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEmpty();
    }

    @Test
    @Order(2)
    void givenTitle_whenCreateBacklog_thenExpectCreatedBacklog() {
        var backlog = buildBacklog();

        var response = restTemplate.postForEntity("/api/backlogs", backlog, BacklogDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).satisfies(body -> {
            assertThat(body.getKey()).isNotNull();
            assertThat(body.getTitle().getKey()).isEqualTo(backlog.getTitle().getKey());
        });

        assertThat(backlogRepository.count()).isEqualTo(1);
        backlogDto = response.getBody();
    }

    @Test
    @Order(3)
    void givenBacklogTitle_whenCreateBacklog_thenExpectExistingBacklog() {
        var backlog = buildBacklog();

        var response = restTemplate.postForEntity("/api/backlogs", backlog, BacklogDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getKey()).isEqualTo(backlogDto.getKey());

        assertThat(backlogRepository.count()).isEqualTo(1);
    }

    @Test
    @Order(4)
    void givenBacklog_whenListBacklogs_thenExpectListWithBacklog() {
        var response = restTemplate.getForEntity("/api/backlogs", BacklogDto[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsExactly(backlogDto);
    }

    @Test
    @Order(5)
    void givenTitle_whenDeleteBacklog_thenExpectOkStatus() {
        var request = RequestEntity.delete(URI.create("/api/backlogs/" + backlogDto.getKey())).build();
        var response = restTemplate.exchange(request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(backlogRepository.findById(Integer.valueOf(backlogDto.getKey()))).isEmpty();
    }

    @Test
    @Order(6)
    void givenDeletedTitle_whenDeleteBacklog_thenExpectNotFoundStatus() {
        var request = RequestEntity.delete(URI.create("/api/backlogs/" + backlogDto.getKey())).build();
        var response = restTemplate.exchange(request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    private BacklogDto buildBacklog() {
        var titleData = new TitleDto();
        titleData.setKey(title.getId().toString());

        var backlogData = new BacklogDto();
        backlogData.setTitle(titleData);
        return backlogData;
    }
}
