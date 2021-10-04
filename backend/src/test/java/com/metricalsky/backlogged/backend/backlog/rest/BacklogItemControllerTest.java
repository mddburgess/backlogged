package com.metricalsky.backlogged.backend.backlog.rest;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.metricalsky.backlogged.backend.backlog.dto.BacklogItemDto;
import com.metricalsky.backlogged.backend.backlog.entity.BacklogItemStatus;
import com.metricalsky.backlogged.backend.backlog.entity.BacklogItemType;
import com.metricalsky.backlogged.backend.backlog.service.BacklogItemService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BacklogItemController.class)
public class BacklogItemControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private BacklogItemService service;

    @Test
    void givenNoBacklogItems_whenGetBacklog_thenReturnEmptyList() throws Exception {
        mvc.perform(get("/api/backlog"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void givenBacklogItem_whenGetBacklog_thenReturnListOfBacklogItem() throws Exception {
        var dto = new BacklogItemDto();
        dto.setId(1);
        dto.setName("Name");
        dto.setType(BacklogItemType.VIDEO_GAME);
        dto.setStatus(BacklogItemStatus.NEW);
        dto.setActivityTime(Duration.ofHours(1));

        when(service.list())
                .thenReturn(List.of(dto));

        mvc.perform(get("/api/backlog"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].id").value(dto.getId()))
                .andExpect(jsonPath("$[0].name").value(dto.getName()))
                .andExpect(jsonPath("$[0].type").value(dto.getType().name()))
                .andExpect(jsonPath("$[0].status").value(dto.getStatus().name()))
                .andExpect(jsonPath("$[0].activityTime").value(dto.getActivityTime().toString()));
    }
}
