package com.metricalsky.backlogged.backend.library.test;

import com.metricalsky.backlogged.backend.library.entity.Title;

public final class TitleFactory {

    private TitleFactory() {

    }

    public static Title createTitle() {
        var title = new Title();
        title.setName("name");
        return title;
    }
}
