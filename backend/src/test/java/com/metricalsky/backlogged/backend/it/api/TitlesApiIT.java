package com.metricalsky.backlogged.backend.it.api;

import java.net.URI;
import java.util.List;

import com.metricalsky.backlogged.backend.library.entity.Copy;
import com.metricalsky.backlogged.backend.library.entity.Title;
import com.metricalsky.backlogged.backend.library.repository.TitleRepository;
import com.metricalsky.backlogged.test.extensions.AbortOnTestFailure;
import org.apache.commons.lang3.RandomStringUtils;
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
    private TitleRepository titleRepository;

    private Title title;
    private Title updatedTitle;

    @BeforeAll
    void beforeAll() {
        titleRepository.deleteAll();
    }

    @Test
    @Order(1)
    void givenNoTitles_whenListTitles_thenExpectEmptyList() {
        var response = restTemplate.getForEntity("/api/titles", Title[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEmpty();
    }

    @Test
    @Order(2)
    void givenTitle_whenCreateTitles_thenExpectTitle() {
        var newTitle = buildTitle();

        var response = restTemplate.postForEntity("/api/titles", newTitle, Title.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).satisfies(body -> {
            assertThat(body.getId()).isNotNull();
            assertThat(body.getToken()).isNotNull();
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
        var response = restTemplate.getForEntity("/api/titles", Title[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsExactly(title);
    }

    @Test
    @Order(4)
    void givenTitle_whenRetrieveTitle_thenExpectTitle() {
        var response = restTemplate.getForEntity("/api/titles/{0}", Title.class, title.getToken());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(title);
    }

    @Test
    @Order(5)
    void givenTitle_whenUpdateTitle_thenExpectUpdatedTitle() {
        updatedTitle = buildTitle();
        updatedTitle.setId(title.getId());
        updatedTitle.setToken(title.getToken());
        updatedTitle.setCopies(title.getCopies());

        var request = RequestEntity.put(URI.create("/api/titles/" + title.getToken())).body(updatedTitle);
        var response = restTemplate.exchange(request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @Order(6)
    void givenUpdatedTitle_whenRetrieveTitle_thenExpectUpdatedTitle() {
        var response = restTemplate.getForEntity("/api/titles/{0}", Title.class, title.getToken());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(updatedTitle);
    }

    @Test
    @Order(7)
    void givenTitle_whenDeleteTitle_thenExpectOkStatus() {
        var request = RequestEntity.delete(URI.create("/api/titles/" + title.getToken())).build();
        var response = restTemplate.exchange(request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @Order(8)
    void givenDeletedTitle_whenRetrieveTitle_thenExpectNotFoundStatus() {
        var response = restTemplate.getForEntity("/api/titles/{0}", Title.class, title.getToken());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    private Title buildTitle() {
        var copy = new Copy();
        copy.setPlatform(RandomStringUtils.randomAlphabetic(10));
        copy.setService(RandomStringUtils.randomAlphabetic(10));
        var title = new Title();
        title.setName(RandomStringUtils.randomAlphabetic(10));
        title.setCopies(List.of(copy));
        return title;
    }
}
