package com.metricalsky.testdata.pmd.jpa;

import javax.persistence.Entity;

import org.springframework.data.domain.Persistable;

@Entity
public abstract class AbstractEntity implements Persistable<Integer> {

}
