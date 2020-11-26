package com.metricalsky.backlogged.backend.library.model;

import java.util.List;

import lombok.Data;

@Data
public class Title {

    private String token;
    private String title;
    private List<Copy> copies;
}
