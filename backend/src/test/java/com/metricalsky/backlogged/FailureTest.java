package com.metricalsky.backlogged;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class FailureTest {

    @Test
    void failTest() {
        fail("Fail to test CI workflow");
    }
}
