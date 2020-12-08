package com.metricalsky.backlogged.backend;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class FailureTest {

    @Test
    void failingTest() {
        fail("Failing test to check CI workflow.");
    }
}
