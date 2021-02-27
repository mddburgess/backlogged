package com.metricalsky.backlogged.backend.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metricalsky.backlogged.backend.library.entity.Title;

@Repository
public interface TitleRepository extends JpaRepository<Title, Integer> {

}
