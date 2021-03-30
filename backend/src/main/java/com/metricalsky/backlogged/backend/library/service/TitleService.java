package com.metricalsky.backlogged.backend.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.metricalsky.backlogged.backend.activity.service.ActivityService;
import com.metricalsky.backlogged.backend.library.dto.TitleDto;
import com.metricalsky.backlogged.backend.library.repository.TitleRepository;

import static com.metricalsky.backlogged.backend.activity.entity.ActivityType.ADD_TO_LIBRARY;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class TitleService {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private TitleMapper titleMapper;
    @Autowired
    private TitleRepository titleRepository;

    public List<TitleDto> listTitles() {
        return titleRepository.findAll()
                .stream()
                .map(titleMapper::fromEntity)
                .collect(toList());
    }

    public TitleDto retrieveTitle(Integer key) {
        return titleRepository.findById(key)
                .map(titleMapper::fromEntity)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    @Transactional
    public TitleDto createTitle(TitleDto title) {
        var entity = titleMapper.toEntity(title);
        titleRepository.save(entity);
        activityService.createActivity(ADD_TO_LIBRARY, entity);
        return titleMapper.fromEntity(entity);
    }

    public TitleDto updateTitle(Integer key, TitleDto title) {
        var entity = titleRepository.findById(key)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
        titleMapper.patchEntity(entity, title);
        titleRepository.save(entity);
        return titleMapper.fromEntity(entity);
    }

    public void deleteTitle(Integer key) {
        titleRepository.findById(key)
                .ifPresent(titleRepository::delete);
    }
}
