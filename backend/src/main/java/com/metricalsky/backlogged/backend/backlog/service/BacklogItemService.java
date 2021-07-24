package com.metricalsky.backlogged.backend.backlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metricalsky.backlogged.backend.backlog.entity.BacklogItem;
import com.metricalsky.backlogged.backend.backlog.repository.BacklogItemRepository;

@Service
public class BacklogItemService {

    @Autowired
    private BacklogItemRepository repository;

    public List<BacklogItem> list() {
        return repository.findAll();
    }

    public BacklogItem create(BacklogItem backlogItem) {
        return repository.save(backlogItem);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
