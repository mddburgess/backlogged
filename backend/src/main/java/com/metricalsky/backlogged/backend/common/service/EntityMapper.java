package com.metricalsky.backlogged.backend.common.service;

public interface EntityMapper<E, D> {

    E toEntity(D dto);
    D toDto(E entity);
}
