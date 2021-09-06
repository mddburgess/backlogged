package com.metricalsky.backlogged.backend.backlog.rest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;

import com.metricalsky.backlogged.backend.backlog.dto.BacklogItemDto;
import com.metricalsky.backlogged.backend.backlog.entity.BacklogItem;
import com.metricalsky.backlogged.backend.backlog.entity.BacklogItemStatus;
import com.metricalsky.backlogged.backend.backlog.entity.BacklogItemType;
import com.metricalsky.backlogged.backend.backlog.repository.BacklogItemRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BacklogItemControllerTest {

    @Autowired
    private TestRestTemplate rest;
    @Autowired
    private BacklogItemRepository backlogItemRepository;

    private BacklogItem backlogItem;

    @BeforeEach
    void beforeEach() {
        backlogItem = new BacklogItem();
        backlogItem.setName("name");
        backlogItem.setType(BacklogItemType.VIDEO_GAME);
        backlogItem.setStatus(BacklogItemStatus.NEW);
        backlogItemRepository.save(backlogItem);
    }

    @Test
    void givenBacklogItem_whenPut_thenUpdateBacklogItem() {
        var dto = new BacklogItemDto();
        dto.setName("new name");
        dto.setType(BacklogItemType.MOVIE);
        dto.setStatus(BacklogItemStatus.ACTIVE);

        var request = RequestEntity.put("/api/backlog/{0}", backlogItem.getId()).body(dto);
        var response = rest.exchange(request, BacklogItemDto.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody())
                .hasFieldOrPropertyWithValue("id", backlogItem.getId())
                .hasFieldOrPropertyWithValue("name", "new name")
                .hasFieldOrPropertyWithValue("type", BacklogItemType.MOVIE)
                .hasFieldOrPropertyWithValue("status", BacklogItemStatus.ACTIVE);
    }
}
