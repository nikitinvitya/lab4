package com.nikitsin.handling.information.entity.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum ExpressionOperatorImpl {
    PLUS(1, "+"), MINUS(2, "-"), MULTIPLY(3, "*");

    private final int rate;
    private final String value;
    public int getRate() {
        return rate;
    }

    ExpressionOperatorImpl(int rate, String value) {
        this.rate = rate;
        this.value = value;
    }

    public static Optional<ExpressionOperatorImpl> getExpressionOperator(String value) {
        return Arrays.stream(values())
                .filter(op -> op.value.equals(value))
                .findFirst();
    }


}
