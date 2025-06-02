package com.nikitsin.handling.information.expression;

import java.util.List;

public class ExpressionEvaluator {
    private final EvaluationContext context = new EvaluationContext();

    public int evaluate(List<MathExpression> expressions) {
        expressions.forEach(expression -> expression.interpretation(context));
        return context.pop();
    }
}
