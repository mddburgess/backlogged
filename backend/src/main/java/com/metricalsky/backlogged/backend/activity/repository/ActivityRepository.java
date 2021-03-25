package com.metricalsky.backlogged.backend.activity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metricalsky.backlogged.backend.activity.entity.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

}
