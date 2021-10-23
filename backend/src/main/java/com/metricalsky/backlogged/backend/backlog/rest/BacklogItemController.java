package com.metricalsky.backlogged.backend.backlog.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metricalsky.backlogged.backend.backlog.dto.BacklogItemDto;
import com.metricalsky.backlogged.backend.backlog.service.BacklogItemService;

@RestController
@RequestMapping("/api/backlog")
public class BacklogItemController {

    @Autowired
    private BacklogItemService service;

    @GetMapping
    public List<BacklogItemDto> list() {
        return service.list();
    }

    @PostMapping
    public BacklogItemDto create(@RequestBody BacklogItemDto backlogItemDto) {
        return service.create(backlogItemDto);
    }

    @PutMapping(path = "/{id}")
    public BacklogItemDto update(@PathVariable("id") Integer id, @RequestBody BacklogItemDto backlogItemDto) {
        return service.update(id, backlogItemDto);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}
