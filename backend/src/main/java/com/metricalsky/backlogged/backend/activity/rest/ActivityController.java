package com.metricalsky.backlogged.backend.activity.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metricalsky.backlogged.backend.activity.dto.ActivityData;
import com.metricalsky.backlogged.backend.library.dto.TitleData;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    @GetMapping
    public List<ActivityData> list() {
        var title = new TitleData();
        title.setKey("42");
        title.setName("The Legend of Zelda: Breath of the Wild");

        return List.of(
                new ActivityData("REMOVE_FROM_BACKLOG", "2021-03-20T18:00:00Z", title),
                new ActivityData("ADD_TO_BACKLOG", "2021-03-19T18:00:00Z", title),
                new ActivityData("ADD_TO_LIBRARY", "2021-03-18T18:00:00Z", title)
        );
    }
}
