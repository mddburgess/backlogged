package com.metricalsky.backlogged.backend.library.rest;

import java.util.List;

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

    @GetMapping("/{key}")
    public TitleData retrieve(@PathVariable Integer key) {
        return titleService.retrieveTitle(key);
    }

    @PutMapping("/{key}")
    public TitleData update(@PathVariable Integer key, @RequestBody TitleData title) {
        return titleService.updateTitle(key, title);
    }

    @DeleteMapping("/{key}")
    public void delete(@PathVariable Integer key) {
        titleService.deleteTitle(key);
    }
}
