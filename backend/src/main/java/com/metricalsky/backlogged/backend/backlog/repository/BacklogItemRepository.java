package com.metricalsky.backlogged.backend.backlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metricalsky.backlogged.backend.backlog.entity.BacklogItem;

@Repository
public interface BacklogItemRepository extends JpaRepository<BacklogItem, Integer> {

}
