package com.metricalsky.backlogged.backend.library.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.metricalsky.backlogged.backend.library.model.Copy;
import com.metricalsky.backlogged.backend.library.model.Title;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/titles")
public class TitleController {

    private final List<Title> titles = new ArrayList<>();

    public TitleController() {
        var macOS = new Copy();
        macOS.setPlatform("macOS");
        macOS.setService("Steam");
        var nsw = new Copy();
        nsw.setPlatform("Nintendo Switch");
        nsw.setService("Physical");
        var title = new Title();
        title.setToken(UUID.randomUUID().toString());
        title.setTitle("Sid Meier's Civilization VI");
        title.setCopies(List.of(macOS, nsw));
        titles.add(title);

        var title2 = new Title();
        title2.setToken(UUID.randomUUID().toString());
        title2.setTitle("Ring Fit Adventure");
        title2.setCopies(List.of(nsw));
        titles.add(title2);
    }

    @GetMapping
    public List<Title> list() {
        return titles;
    }

    @PostMapping
    public Title create(@RequestBody Title title) {
        title.setToken(UUID.randomUUID().toString());
        titles.add(title);
        return title;
    }

    @PutMapping("/{token}")
    public void update(@PathVariable String token, @RequestBody Title title) {
        titles.replaceAll(t -> token.equals(t.getToken()) ? title : t);
    }

    @DeleteMapping("/{token}")
    public void delete(@PathVariable String token) {
        titles.removeIf(t -> token.equals(t.getToken()));
    }
}
