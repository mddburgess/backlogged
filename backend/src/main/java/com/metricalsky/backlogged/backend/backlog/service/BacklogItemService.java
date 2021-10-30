package com.metricalsky.backlogged.backend.backlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metricalsky.backlogged.backend.backlog.dto.BacklogItemDto;
import com.metricalsky.backlogged.backend.backlog.event.BacklogItemEventPublisher;
import com.metricalsky.backlogged.backend.backlog.repository.BacklogItemRepository;

import static java.util.stream.Collectors.toList;

@Service
public class BacklogItemService {

    @Autowired
    private BacklogItemEventPublisher eventPublisher;
    @Autowired
    private BacklogItemRepository repository;

    private final BacklogItemMapper mapper;

    public BacklogItemService() {
        mapper = new BacklogItemMapper();
    }

    public List<BacklogItemDto> list() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(toList());
    }

    @Transactional
    public BacklogItemDto create(BacklogItemDto dto) {
        var backlogItem = mapper.toEntity(dto);
        repository.save(backlogItem);
        return mapper.toDto(backlogItem);
    }

    @Transactional
    public BacklogItemDto update(Integer id, BacklogItemDto dto) {
        var backlogItem = repository.findById(id).orElseThrow();
        eventPublisher.publishUpdateEvent(backlogItem);
        mapper.patchEntity(backlogItem, dto);
        return mapper.toDto(backlogItem);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
