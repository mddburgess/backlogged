package com.metricalsky.backlogged.backend.backlog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.metricalsky.backlogged.backend.activity.service.ActivityService;
import com.metricalsky.backlogged.backend.backlog.dto.BacklogData;
import com.metricalsky.backlogged.backend.backlog.entity.Backlog;
import com.metricalsky.backlogged.backend.backlog.repository.BacklogRepository;
import com.metricalsky.backlogged.backend.library.repository.TitleRepository;

import static com.metricalsky.backlogged.backend.activity.entity.ActivityType.ADD_TO_BACKLOG;
import static com.metricalsky.backlogged.backend.activity.entity.ActivityType.REMOVE_FROM_BACKLOG;
import static java.util.stream.Collectors.toList;

@Service
public class BacklogService {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private BacklogMapper backlogMapper;
    @Autowired
    private BacklogRepository backlogRepository;
    @Autowired
    private TitleRepository titleRepository;

    public List<BacklogData> listBacklogs() {
        return backlogRepository.findAll()
                .stream()
                .map(backlogMapper::fromEntity)
                .collect(toList());
    }

    public Optional<BacklogData> findBacklogByTitleKey(String titleKey) {
        return backlogRepository.findByTitleId(Integer.valueOf(titleKey))
                .map(backlogMapper::fromEntity);
    }

    @Transactional
    public BacklogData createBacklog(BacklogData backlog) {
        var title = titleRepository.findById(Integer.valueOf(backlog.getTitle().getKey()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        var newBacklog = Backlog.builder().title(title).build();
        backlogRepository.save(newBacklog);
        activityService.createActivity(ADD_TO_BACKLOG, title);
        return backlogMapper.fromEntity(newBacklog);
    }

    @Transactional
    public void deleteBacklog(Integer key) {
        var backlog = backlogRepository.findById(key)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        backlogRepository.delete(backlog);
        activityService.createActivity(REMOVE_FROM_BACKLOG, backlog.getTitle());
    }
}
