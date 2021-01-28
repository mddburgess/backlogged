package com.metricalsky.backlogged.backend.library.rest;

import java.util.List;
import java.util.UUID;

import com.metricalsky.backlogged.backend.library.dto.TitleData;
import com.metricalsky.backlogged.backend.library.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/titles")
public class TitleController {

    @Autowired
    private TitleService titleService;

    @GetMapping
    public List<TitleData> list() {
        return titleService.listTitles();
    }

    @PostMapping
    public TitleData create(@RequestBody TitleData title) {
        return titleService.createTitle(title);
    }

    @GetMapping("/{token}")
    public TitleData retrieve(@PathVariable UUID token) {
        return titleService.retrieveTitle(token);
    }

    @PutMapping("/{token}")
    public TitleData update(@PathVariable UUID token, @RequestBody TitleData title) {
        return titleService.updateTitle(token, title);
    }

    @DeleteMapping("/{token}")
    public void delete(@PathVariable UUID token) {
        titleService.deleteTitle(token);
    }
}
