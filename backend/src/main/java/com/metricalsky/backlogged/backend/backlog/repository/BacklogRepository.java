package com.metricalsky.backlogged.backend.backlog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metricalsky.backlogged.backend.backlog.entity.Backlog;

@Repository
public interface BacklogRepository extends JpaRepository<Backlog, Integer> {

    Optional<Backlog> findByTitleId(Integer titleId);
}
