package com.metricalsky.backlogged.backend.library.repository;

import java.util.Optional;
import java.util.UUID;

import com.metricalsky.backlogged.backend.library.entity.Title;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleRepository extends JpaRepository<Title, Integer> {

    Optional<Title> findByToken(UUID token);
}
