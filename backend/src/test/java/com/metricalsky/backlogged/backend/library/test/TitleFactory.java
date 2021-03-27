package com.metricalsky.backlogged.backend.library.test;

import com.metricalsky.backlogged.backend.library.entity.Title;

public final class TitleFactory {

    private TitleFactory() {

    }

    public static Title createTitle() {
        return createTitle(null, "name");
    }

    public static Title createTitle(Integer id, String name) {
        var title = new Title();
        title.setId(id);
        title.setName(name);
        return title;
    }
}
