package com.nikitsin.handling.information.expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.nikitsin.handling.information.expression.MathOperation.*;

public class PolishNotation {
    private static final String SPLIT_REGEX = "[ \\t]+";

    public List<MathExpression> parse(String polishNotation){
        List<MathExpression> expressions = new ArrayList<>();

        Arrays.asList(polishNotation.split(SPLIT_REGEX)).forEach( token -> {
            if (token.equals(AND_SIGN)) {
                expressions.add(res -> res.push(res.pop() & res.pop()));
            } else if (token.equals(LEFT_SHIFT_SIGN)) {
                expressions.add(res -> {
                    int right = res.pop();
                    int left = res.pop();
                    res.push(left << right);
                });
            } else if (token.equals(RIGHT_SHIFT_SIGN)) {
                expressions.add(res -> {
                    int right = res.pop();
                    int left = res.pop();
                    res.push(left >> right);
                });
            } else if (token.equals(TILDE_SIGN)) {
                expressions.add(res -> res.push(~res.pop()));
            } else if (token.equals(OR_SIGN)) {
                expressions.add(res -> res.push(res.pop() | res.pop()));
            } else if (token.equals(XOR_SIGN)) {
                expressions.add(res -> res.push(res.pop() ^ res.pop()));
            } else if (token.equals(PLUS_SIGN)) {
                expressions.add(res -> res.push(res.pop() + res.pop()));
            } else if (token.equals(MINUS_SIGN)) {
                expressions.add(res -> res.push(-res.pop() + res.pop()));
            } else if (token.equals(MULTIPLY_SIGN)) {
                expressions.add(res -> res.push(res.pop() * res.pop()));
            } else if (token.equals(DIVISION_SIGN)) {
                expressions.add(res -> res.push(1 / res.pop() * res.pop()));
            } else {
                expressions.add(res -> res.push(Integer.parseInt(token)));
            }

        });

        return expressions;
    }

}












