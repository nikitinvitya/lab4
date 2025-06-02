package com.nikitsin.handling.information.expression;

import java.util.ArrayDeque;

public class EvaluationContext {
    private final ArrayDeque<Integer> stack = new ArrayDeque<>();

    public void push(Integer value) {
        stack.push(value);
    }

    public Integer pop() {
        return stack.pop();
    }
}
