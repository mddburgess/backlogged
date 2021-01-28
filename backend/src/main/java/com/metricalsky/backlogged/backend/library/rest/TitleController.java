package com.metricalsky.backlogged.backend.library.rest;

import java.util.List;
import java.util.UUID;

import com.metricalsky.backlogged.backend.library.dto.TitleData;
import com.metricalsky.backlogged.backend.library.repository.TitleRepository;
import com.metricalsky.backlogged.backend.library.service.TitleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/titles")
public class TitleController {

    @Autowired
    private TitleRepository repository;
    @Autowired
    private TitleMapper mapper;

    @GetMapping
    public List<TitleData> list() {
        return mapper.fromEntities(repository.findAll());
    }

    @PostMapping
    public TitleData create(@RequestBody TitleData titleData) {
        var title = mapper.toEntity(titleData);
        title.setToken(UUID.randomUUID());
        title = repository.save(title);
        return mapper.fromEntity(title);
    }

    @GetMapping("/{token}")
    public TitleData retrieve(@PathVariable UUID token) {
        var title = repository.findByToken(token)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return mapper.fromEntity(title);
    }

    @PutMapping("/{token}")
    public TitleData update(@PathVariable UUID token, @RequestBody TitleData titleData) {
        var title = repository.findByToken(token)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapper.patchEntity(title, titleData);
        title = repository.save(title);
        return mapper.fromEntity(title);
    }

    @DeleteMapping("/{token}")
    public void delete(@PathVariable UUID token) {
        repository.findByToken(token).ifPresent(repository::delete);
    }
}
