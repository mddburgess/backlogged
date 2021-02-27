package com.metricalsky.backlogged.backend.backlog.service;

import java.net.URI;
import java.util.List;

import com.metricalsky.backlogged.backend.backlog.dto.BacklogData;
import com.metricalsky.backlogged.backend.backlog.entity.Backlog;
import com.metricalsky.backlogged.backend.backlog.repository.BacklogRepository;
import com.metricalsky.backlogged.backend.library.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static java.util.stream.Collectors.toList;

@Service
public class BacklogService {

    @Autowired
    private BacklogMapper mapper;
    @Autowired
    private BacklogRepository repository;
    @Autowired
    private TitleRepository titleRepository;

    public List<BacklogData> listBacklogs() {
        return repository.findAll()
                .stream()
                .map(mapper::fromEntity)
                .collect(toList());
    }

    public ResponseEntity<BacklogData> createBacklog(BacklogData backlog) {
        var title = titleRepository.findById(Integer.valueOf(backlog.getTitle().getKey()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        var existingBacklog = repository.findByTitleId(title.getId());
        if (existingBacklog.isPresent()) {
            return ResponseEntity.ok(mapper.fromEntity(existingBacklog.get()));
        }

        var newBacklog = Backlog.builder().title(title).build();
        repository.save(newBacklog);
        return ResponseEntity.created(URI.create("/"))
                .body(mapper.fromEntity(newBacklog));
    }

    public void deleteBacklog(Integer key) {
        var backlog = repository.findById(key)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        repository.delete(backlog);
    }
}
