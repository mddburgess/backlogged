package com.metricalsky.quality.pmd.jpa;

import net.sourceforge.pmd.testframework.SimpleAggregatorTst;

public class AvoidImplicitTableNameTest extends SimpleAggregatorTst {

    @Override
    protected void setUp() {
        addRule("rules/java/jpa.xml", "AvoidImplicitTableName");
    }
}
