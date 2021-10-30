package com.metricalsky.backlogged.backend.backlog.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metricalsky.backlogged.backend.backlog.dto.BacklogItemDto;
import com.metricalsky.backlogged.backend.backlog.event.BacklogItemEventPublisher;
import com.metricalsky.backlogged.backend.backlog.repository.BacklogItemRepository;

@Service
public class BacklogItemService {

    private final BacklogItemEventPublisher eventPublisher;
    private final BacklogItemRepository repository;
    private final BacklogItemMapper mapper = new BacklogItemMapper();

    public BacklogItemService(BacklogItemEventPublisher eventPublisher,
            BacklogItemRepository repository) {
        this.eventPublisher = eventPublisher;
        this.repository = repository;
    }

    public List<BacklogItemDto> list() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
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
