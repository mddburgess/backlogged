package com.metricalsky.backlogged.backend.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.metricalsky.backlogged.backend.library.dto.TitleData;
import com.metricalsky.backlogged.backend.library.repository.TitleRepository;

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

    public TitleData retrieveTitle(Integer key) {
        return repository.findById(key)
                .map(mapper::fromEntity)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    public TitleData createTitle(TitleData title) {
        var entity = mapper.toEntity(title);
        repository.save(entity);
        return mapper.fromEntity(entity);
    }

    public TitleData updateTitle(Integer key, TitleData title) {
        var entity = repository.findById(key)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
        mapper.patchEntity(entity, title);
        repository.save(entity);
        return mapper.fromEntity(entity);
    }

    public void deleteTitle(Integer key) {
        repository.findById(key)
                .ifPresent(repository::delete);
    }
}
