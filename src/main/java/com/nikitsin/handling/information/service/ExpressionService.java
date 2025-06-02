package com.nikitsin.handling.information.service;

import com.nikitsin.handling.information.entity.impl.ExpressionOperatorImpl;
import com.nikitsin.handling.information.expression.ExpressionEvaluator;
import com.nikitsin.handling.information.expression.MathExpression;
import com.nikitsin.handling.information.expression.PolishNotation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionService {
    private static final String NUMBER_EXPRESSION_REGEX = "\\p{Punct}*\\d[\\p{Punct}\\d]+";
    private static final Logger logger = LogManager.getLogger(ExpressionService.class);

    private List<String> getAllExpressions(String text) {
        List<String> expressions = new ArrayList<>();
        Pattern p = Pattern.compile(NUMBER_EXPRESSION_REGEX);
        Matcher matcher = p.matcher(text);
        while (matcher.find()) {
            expressions.add(matcher.group());
        }
        return expressions;
    }

    public String replaceExpressionsByNumber(String text) {
        List<String> mustReplace = getAllExpressions(text);
        List<String> willReplace = convertToNumbers(mustReplace);

        String result = text;
        for (int i = 0; i < mustReplace.size(); i++) {
            result = result.replace(mustReplace.get(i), willReplace.get(i));
        }

        logger.info("All expressions in the text were replaced by numbers");
        return result;
    }

    private List<String> convertToNumbers(List<String> input) {
        List<String> result = new ArrayList<>();
        PolishNotationService polishNotationParser = new PolishNotationService();
        PolishNotation interpreter = new PolishNotation();

        for (String value : input) {
            List<MathExpression> mathExpressions = interpreter.parse(polishNotationParser.calculatePolishNotation(value));
            ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();
            int res = expressionEvaluator.evaluate(mathExpressions);
            result.add(String.valueOf(res));
        }

        return result;
    }

    public static class PolishNotationService {
        public String calculatePolishNotation(String expression) {
            List<String> list = new ArrayList<>();

            for (int i = 0; i < expression.length(); i++) {
                if (i + 1 < expression.length()) {
                    if (expression.charAt(i) == '<' && expression.charAt(i + 1) == '<') {
                        list.add("<<");
                        i++;
                        continue;
                    }
                    if (expression.charAt(i) == '>' && expression.charAt(i + 1) == '>') {
                        list.add(">>");
                        i++;
                        continue;
                    }
                }

                char current = expression.charAt(i);
                if (Character.isDigit(current)) {
                    StringBuilder number = new StringBuilder();
                    while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                        number.append(expression.charAt(i));
                        i++;
                    }
                    i--;
                    list.add(number.toString());
                } else if (current == '-') {
                    StringBuilder number = new StringBuilder("-");
                    i++;
                    while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                        number.append(expression.charAt(i));
                        i++;
                    }
                    i--;
                    list.add(number.toString());
                } else {
                    list.add(String.valueOf(current));
                }
            }

            return convert(list);
        }

        private String convert(List<String> expression) {
            Deque<String> result = new ArrayDeque<>();
            Deque<String> forWait = new ArrayDeque<>();

            for (String token : expression) {
                if (token.isEmpty()) continue;

                if (isNumber(token)) {
                    result.push(token);
                } else {
                    if (forWait.isEmpty()) {
                        forWait.push(token);
                    } else {
                        String lastWait = forWait.pop();

                        if (token.equals("(")) {
                            forWait.push(lastWait);
                            forWait.push(token);
                        } else if (token.equals(")")) {
                            if (!lastWait.equals("(")) {
                                result.push(lastWait);
                            }

                            while (!forWait.isEmpty()) {
                                String lastPop = forWait.pop();
                                if (lastPop.equals("(")) break;
                                result.push(lastPop);
                            }
                        } else if (lastWait.equals("(")) {
                            forWait.push(lastWait);
                            forWait.push(token);
                        } else if (lastWait.equals(")")) {
                            forWait.push(lastWait);
                        } else {
                            int currentRate = ExpressionOperatorImpl.getExpressionOperator(token)
                                    .orElseThrow(() -> new IllegalArgumentException("Unknown operator: " + token))
                                    .getRate();
                            int lastRate = ExpressionOperatorImpl.getExpressionOperator(lastWait)
                                    .orElseThrow(() -> new IllegalArgumentException("Unknown operator: " + lastWait))
                                    .getRate();

                            if (currentRate > lastRate) {
                                forWait.push(lastWait);
                                forWait.push(token);
                            } else {
                                result.push(lastWait);
                                while (!forWait.isEmpty()) {
                                    String lastPop = forWait.pop();
                                    if (lastPop.equals("(")) {
                                        forWait.push(lastPop);
                                        break;
                                    }
                                    int popRate = ExpressionOperatorImpl.getExpressionOperator(lastPop)
                                            .orElseThrow(() -> new IllegalArgumentException("Unknown operator: " + lastPop))
                                            .getRate();
                                    if (popRate >= currentRate) {
                                        result.push(lastPop);
                                    } else {
                                        forWait.push(lastPop);
                                        break;
                                    }
                                }
                                forWait.push(token);
                            }
                        }
                    }
                }
            }

            while (!forWait.isEmpty()) {
                String add = forWait.pop();
                if (!add.equals("(") && !add.equals(")")) {
                    result.push(add);
                }
            }

            return reverseNotation(result);
        }

        private boolean isNumber(String token) {
            return ExpressionOperatorImpl.getExpressionOperator(token).isEmpty()
                    && !token.equals(">") && !token.equals("<");
        }

        private String reverseNotation(Deque<String> deque) {
            StringBuilder builder = new StringBuilder();
            for (String token : deque) {
                builder.append(token).append(" ");
            }
            if (builder.length() > 0) {
                builder.setLength(builder.length() - 1); // удаляем последний пробел
            }
            return builder.reverse().toString();
        }
    }
}
