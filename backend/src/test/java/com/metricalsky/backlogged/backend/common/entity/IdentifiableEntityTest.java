package com.metricalsky.backlogged.backend.common.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IdentifiableEntityTest {

    @Test
    void givenId_whenIsNew_thenReturnFalse() {
        var entity = new IdentifiableEntity();
        entity.setId(1);
        assertThat(entity.isNew()).isFalse();
    }

    @Test
    void givenNullId_whenIsNew_thenReturnTrue() {
        var entity = new IdentifiableEntity();
        assertThat(entity.isNew()).isTrue();
    }
}
