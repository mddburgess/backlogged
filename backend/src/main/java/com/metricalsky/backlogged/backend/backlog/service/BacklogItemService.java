package com.metricalsky.backlogged.backend.backlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metricalsky.backlogged.backend.activity.service.StatusActivityService;
import com.metricalsky.backlogged.backend.backlog.entity.BacklogItem;
import com.metricalsky.backlogged.backend.backlog.repository.BacklogItemRepository;

@Service
public class BacklogItemService {

    @Autowired
    private BacklogItemRepository repository;
    @Autowired
    private StatusActivityService statusActivityService;

    public List<BacklogItem> list() {
        return repository.findAll();
    }

    public BacklogItem getById(Integer id) {
        return repository.findById(id).orElseThrow();
    }

    @Transactional
    public BacklogItem create(BacklogItem backlogItem) {
        var savedBacklogItem = repository.save(backlogItem);
        statusActivityService.create(savedBacklogItem, null);
        return savedBacklogItem;
    }

    @Transactional
    public BacklogItem update(Integer id, BacklogItem backlogItem) {
        var existingBacklogItem = repository.findById(id);
        var fromStatus = existingBacklogItem.map(BacklogItem::getStatus).orElse(null);
        backlogItem.setId(id);
        var savedBacklogItem = repository.save(backlogItem);
        statusActivityService.create(savedBacklogItem, fromStatus);
        return savedBacklogItem;
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
