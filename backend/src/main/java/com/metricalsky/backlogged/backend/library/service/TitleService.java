package com.metricalsky.backlogged.backend.library.service;

import java.util.List;
import java.util.UUID;

import com.metricalsky.backlogged.backend.library.dto.TitleData;
import com.metricalsky.backlogged.backend.library.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class TitleService {

    @Autowired
    private TitleMapper mapper;
    @Autowired
    private TitleRepository repository;

    public List<TitleData> listTitles() {
        return repository.findAll()
                .stream()
                .map(mapper::fromEntity)
                .collect(toList());
    }

    public TitleData retrieveTitle(UUID token) {
        return repository.findByToken(token)
                .map(mapper::fromEntity)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    public TitleData createTitle(TitleData title) {
        var entity = mapper.toEntity(title);
        repository.save(entity);
        return mapper.fromEntity(entity);
    }

    public TitleData updateTitle(UUID token, TitleData title) {
        var entity = repository.findByToken(token)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
        mapper.patchEntity(entity, title);
        repository.save(entity);
        return mapper.fromEntity(entity);
    }

    public void deleteTitle(UUID token) {
        repository.findByToken(token)
                .ifPresent(repository::delete);
    }
}
