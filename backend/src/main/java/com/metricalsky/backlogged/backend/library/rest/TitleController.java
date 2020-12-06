package com.metricalsky.backlogged.backend.library.rest;

import java.util.List;
import java.util.UUID;

import com.metricalsky.backlogged.backend.library.entity.Title;
import com.metricalsky.backlogged.backend.library.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/titles")
public class TitleController {

    @Autowired
    private TitleRepository repository;

    @GetMapping
    public List<Title> list() {
        return repository.findAll();
    }

    @PostMapping
    public Title create(@RequestBody Title title) {
        title.setToken(UUID.randomUUID());
        title.linkCopies();
        return repository.save(title);
    }

    @GetMapping("/{token}")
    public Title retrieve(@PathVariable UUID token) {
        return repository.findByToken(token)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{token}")
    public void update(@PathVariable UUID token, @RequestBody Title title) {
        title.linkCopies();
        repository.save(title);
    }

    @DeleteMapping("/{token}")
    public void delete(@PathVariable UUID token) {
        repository.findByToken(token).ifPresent(repository::delete);
    }
}
