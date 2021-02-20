package com.metricalsky.backlogged.backend.backlog.repository;

import com.metricalsky.backlogged.backend.backlog.entity.Backlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends JpaRepository<Backlog, Integer> {

}
