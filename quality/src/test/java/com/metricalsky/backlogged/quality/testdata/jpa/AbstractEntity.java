package com.metricalsky.backlogged.quality.testdata.jpa;

import javax.persistence.Entity;

import org.springframework.data.domain.Persistable;

@Entity
public abstract class AbstractEntity implements Persistable<Integer> {

}
