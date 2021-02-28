package com.metricalsky.backlogged.backend.backlog.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metricalsky.backlogged.backend.backlog.dto.BacklogData;
import com.metricalsky.backlogged.backend.backlog.service.BacklogService;

@RestController
@RequestMapping("/api/backlogs")
public class BacklogController {

    @Autowired
    private BacklogService backlogService;

    @GetMapping
    public List<BacklogData> list() {
        return backlogService.listBacklogs();
    }

    @PostMapping
    public ResponseEntity<BacklogData> create(@RequestBody BacklogData backlog) {
        var b = backlogService.findBacklogByTitleKey(backlog.getTitle().getKey());
        if (b.isPresent()) {
            return ResponseEntity.ok(b.get());
        } else {
            var newBacklog = backlogService.createBacklog(backlog);
            return ResponseEntity.created(URI.create("/")).body(newBacklog);
        }
    }

    @DeleteMapping("/{key}")
    public void delete(@PathVariable Integer key) {
        backlogService.deleteBacklog(key);
    }
}
