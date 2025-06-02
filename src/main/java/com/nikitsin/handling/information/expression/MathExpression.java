package com.nikitsin.handling.information.expression;

@FunctionalInterface
public interface MathExpression {
    void interpretation(EvaluationContext evaluationContext);
}
