package com.metricalsky.backlogged.backend.backlog.rest;

import java.time.Duration;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.metricalsky.backlogged.backend.backlog.dto.BacklogItemDto;
import com.metricalsky.backlogged.backend.backlog.entity.BacklogItemStatus;
import com.metricalsky.backlogged.backend.backlog.entity.BacklogItemType;
import com.metricalsky.backlogged.backend.backlog.service.BacklogItemService;
import com.metricalsky.backlogged.backend.common.exception.ResourceNotFoundException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BacklogItemController.class)
class BacklogItemControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

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

    @Test
    void givenBacklogItem_whenPostBacklog_thenReturnCreatedBacklogItem() throws Exception {
        var dto = new BacklogItemDto();

        when(service.create(any(BacklogItemDto.class)))
                .thenReturn(dto);

        var request = post("/api/backlog")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto));

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap());
    }

    @Test
    void givenExists_whenGetBacklogItem_thenReturnBacklogItem() throws Exception {
        var dto = new BacklogItemDto();
        dto.setId(1);
        dto.setName("Name");
        dto.setType(BacklogItemType.VIDEO_GAME);
        dto.setStatus(BacklogItemStatus.NEW);

        when(service.retrieve(1))
                .thenReturn(dto);

        mvc.perform(get("/api/backlog/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(dto.getId()))
                .andExpect(jsonPath("$.name").value(dto.getName()))
                .andExpect(jsonPath("$.type").value(dto.getType().name()))
                .andExpect(jsonPath("$.status").value(dto.getStatus().name()));
    }

    @Test
    void givenNotExists_whenGetBacklogItem_thenReturnNotFound() throws Exception {
        when(service.retrieve(1))
                .thenThrow(ResourceNotFoundException.class);

        mvc.perform(get("/api/backlog/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenBacklogItem_whenPutBacklogItem_thenReturnUpdatedBacklogItem() throws Exception {
        var dto = new BacklogItemDto();

        when(service.update(eq(1), any(BacklogItemDto.class)))
                .thenReturn(dto);

        var request = put("/api/backlog/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto));

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap());
    }

    @Test
    void givenNotExists_whenPutBacklogItem_thenReturnNotFound() throws Exception {
        when(service.update(eq(1), any(BacklogItemDto.class)))
                .thenThrow(ResourceNotFoundException.class);

        var request = put("/api/backlog/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}");

        mvc.perform(request)
                .andExpect(status().isNotFound());
    }

    @Test
    void givenExists_whenDeleteBacklogItem_thenCallService() throws Exception {
        mvc.perform(delete("/api/backlog/1"))
                .andExpect(status().isOk());

        verify(service)
                .delete(1);
    }

    @Test
    void givenNotExists_whenDeleteBacklogItem_thenReturnNotFound() throws Exception {
        doThrow(ResourceNotFoundException.class)
                .when(service).delete(1);

        mvc.perform(delete("/api/backlog/1"))
                .andExpect(status().isNotFound());
    }
}
