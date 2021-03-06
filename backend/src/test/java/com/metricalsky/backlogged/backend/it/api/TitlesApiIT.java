package com.metricalsky.backlogged.backend.it.api;

import java.net.URI;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
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

import com.metricalsky.backlogged.backend.activity.repository.ActivityRepository;
import com.metricalsky.backlogged.backend.library.dto.CopyDto;
import com.metricalsky.backlogged.backend.library.dto.TitleDto;
import com.metricalsky.backlogged.backend.library.repository.TitleRepository;
import com.metricalsky.backlogged.test.extensions.AbortOnTestFailure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestInstance(PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@AbortOnTestFailure
class TitlesApiIT {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private TitleRepository titleRepository;

    private TitleDto title;
    private TitleDto updatedTitle;

    @BeforeAll
    void beforeAll() {
        activityRepository.deleteAll();
        titleRepository.deleteAll();
    }

    @Test
    @Order(1)
    void givenNoTitles_whenListTitles_thenExpectEmptyList() {
        var response = restTemplate.getForEntity("/api/titles", TitleDto[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEmpty();
    }

    @Test
    @Order(2)
    void givenTitle_whenCreateTitles_thenExpectTitle() {
        var newTitle = buildTitle();

        var response = restTemplate.postForEntity("/api/titles", newTitle, TitleDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).satisfies(body -> {
            assertThat(body.getKey()).isNotNull();
            assertThat(body.getName()).isEqualTo(newTitle.getName());
            assertThat(body.getCopies()).zipSatisfy(newTitle.getCopies(), (bodyCopy, titleCopy) -> {
                assertThat(bodyCopy.getPlatform()).isEqualTo(titleCopy.getPlatform());
                assertThat(bodyCopy.getService()).isEqualTo(titleCopy.getService());
            });
        });

        title = response.getBody();
    }

    @Test
    @Order(3)
    void givenTitle_whenListTitles_thenExpectListWithTitle() {
        var response = restTemplate.getForEntity("/api/titles", TitleDto[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsExactly(title);
    }

    @Test
    @Order(4)
    void givenTitle_whenRetrieveTitle_thenExpectTitle() {
        var response = restTemplate.getForEntity("/api/titles/{0}", TitleDto.class, title.getKey());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(title);
    }

    @Test
    @Order(5)
    void givenTitle_whenUpdateTitle_thenExpectUpdatedTitle() {
        updatedTitle = buildTitle();

        var request = RequestEntity.put(URI.create("/api/titles/" + title.getKey())).body(updatedTitle);
        var response = restTemplate.exchange(request, TitleDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        updatedTitle = response.getBody();
    }

    @Test
    @Order(6)
    void givenUpdatedTitle_whenRetrieveTitle_thenExpectUpdatedTitle() {
        var response = restTemplate.getForEntity("/api/titles/{0}", TitleDto.class, title.getKey());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(updatedTitle);
    }

    @Test
    @Order(7)
    @Disabled
    void givenTitle_whenDeleteTitle_thenExpectOkStatus() {
        var request = RequestEntity.delete(URI.create("/api/titles/" + title.getKey())).build();
        var response = restTemplate.exchange(request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @Order(8)
    @Disabled
    void givenDeletedTitle_whenRetrieveTitle_thenExpectNotFoundStatus() {
        var response = restTemplate.getForEntity("/api/titles/{0}", TitleDto.class, title.getKey());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    private TitleDto buildTitle() {
        var copy = new CopyDto();
        copy.setPlatform(RandomStringUtils.randomAlphabetic(10));
        copy.setService(RandomStringUtils.randomAlphabetic(10));
        var title = new TitleDto();
        title.setName(RandomStringUtils.randomAlphabetic(10));
        title.setCopies(List.of(copy));
        return title;
    }
}
