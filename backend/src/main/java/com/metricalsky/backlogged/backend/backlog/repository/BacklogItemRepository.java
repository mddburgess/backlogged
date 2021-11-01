package com.metricalsky.backlogged.backend.backlog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metricalsky.backlogged.backend.backlog.entity.BacklogItem;

@Repository
@Transactional(readOnly = true)
public interface BacklogItemRepository extends JpaRepository<BacklogItem, Integer> {

    @EntityGraph(attributePaths = "activities")
    Optional<BacklogItem> findDetailedById(Integer id);
}
