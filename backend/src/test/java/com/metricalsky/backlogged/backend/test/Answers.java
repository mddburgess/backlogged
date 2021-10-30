package com.metricalsky.backlogged.backend.test;

import org.mockito.stubbing.Answer;

import com.metricalsky.backlogged.backend.common.entity.IdentifiableEntity;

public final class Answers {

    private Answers() {

    }

    public static Answer<IdentifiableEntity> setEntityId() {
        return setEntityId(1);
    }

    public static Answer<IdentifiableEntity> setEntityId(Integer id) {
        return invocation -> {
            var entity = invocation.getArgument(0, IdentifiableEntity.class);
            entity.setId(id);
            return entity;
        };
    }
}
