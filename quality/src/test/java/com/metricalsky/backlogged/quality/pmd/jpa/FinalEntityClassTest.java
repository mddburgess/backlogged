package com.metricalsky.backlogged.quality.pmd.jpa;

import net.sourceforge.pmd.testframework.SimpleAggregatorTst;

public class FinalEntityClassTest extends SimpleAggregatorTst {

    @Override
    protected void setUp() {
        addRule("rules/java/jpa.xml", "FinalEntityClass");
    }
}
