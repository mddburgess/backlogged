package com.metricalsky.backlogged.test.extensions;

import java.lang.reflect.Method;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;
import org.junit.jupiter.api.extension.TestWatcher;

public class AbortOnTestFailureExtension implements TestWatcher, ExecutionCondition {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        getStore(context).put(context.getRequiredTestClass(), context.getRequiredTestMethod());
    }

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
        if (context.getTestMethod().isPresent()) {
            var failedTestMethod = getStore(context).get(context.getRequiredTestClass(), Method.class);
            if (failedTestMethod != null) {
                return ConditionEvaluationResult.disabled(formatFailureMessage(failedTestMethod));
            }
        }
        return ConditionEvaluationResult.enabled(null);
    }

    private static String formatFailureMessage(Method failedTestMethod) {
        return String.format("Skipping test due to earlier failure on %s", failedTestMethod);
    }

    private static Store getStore(ExtensionContext context) {
        return context.getRoot().getStore(Namespace.create(context.getClass()));
    }
}
