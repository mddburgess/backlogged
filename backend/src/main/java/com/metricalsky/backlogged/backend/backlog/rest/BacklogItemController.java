package com.metricalsky.backlogged.backend.backlog.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metricalsky.backlogged.backend.backlog.dto.BacklogItemDto;
import com.metricalsky.backlogged.backend.backlog.service.BacklogItemMapper;
import com.metricalsky.backlogged.backend.backlog.service.BacklogItemService;

@RestController
@RequestMapping("/api/backlog")
public class BacklogItemController {

    @Autowired
    private BacklogItemService service;
    @Autowired
    private BacklogItemMapper mapper;

    @GetMapping
    public List<BacklogItemDto> list() {
        return service.list()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public BacklogItemDto create(@RequestBody BacklogItemDto backlogItemDto) {
        var entity = mapper.toEntity(backlogItemDto);
        entity = service.create(entity);
        return mapper.toDto(entity);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}
