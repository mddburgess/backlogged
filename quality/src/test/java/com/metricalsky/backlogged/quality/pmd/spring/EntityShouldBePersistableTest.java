package com.metricalsky.backlogged.quality.pmd.spring;

import net.sourceforge.pmd.testframework.SimpleAggregatorTst;

public class EntityShouldBePersistableTest extends SimpleAggregatorTst {

    @Override
    protected void setUp() {
        addRule("rules/java/spring.xml", "EntityShouldBePersistable");
    }
}
