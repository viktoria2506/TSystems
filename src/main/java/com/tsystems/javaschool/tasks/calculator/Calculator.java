package com.tsystems.javaschool.tasks.calculator;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Stack;

public class Calculator {

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */

    public String evaluate(String statement) {
        Stack<Token> value = new Stack<>();
        Stack<Token> operator = new Stack<>();

        if (statement == null) {
            return null;
        }
        String[] parts = statement.split("(?=[^\\d.])|(?<![\\d.])");
        Token[] tokens = new Token[parts.length];
        for (int i = 0; i < parts.length; i++) {
            tokens[i] = new Token(parts[i]);
        }
        for (Token nextToken : tokens) {
            if (nextToken.getType() == Type.NUMBER) {
                value.push(nextToken);
            } else if (nextToken.getType() == Type.OPERATOR) {
                if (operator.isEmpty() || nextToken.getPrecedence() > operator.peek().getPrecedence()) {
                    operator.push(nextToken);
                } else {
                    calculate(operator, value);
                    operator.push(nextToken);
                }
            } else if (nextToken.getType() == Type.LEFT_PARENTHESIS) {
                operator.push(nextToken);
            } else if (nextToken.getType() == Type.RIGHT_PARENTHESIS) {
                calculate(operator, value);
                if (!operator.isEmpty() && operator.peek().getType() == Type.LEFT_PARENTHESIS) {
                    operator.pop();
                } else {
                    Token.error = true;
                }
            }

        }
        calculate(operator, value);
        if (!Token.error) {
            Token result = value.peek();
            value.pop();
            if (!operator.isEmpty() || !value.isEmpty()) {
                return null;
            } else {
                NumberFormat numberFormatter = NumberFormat.getNumberInstance(Locale.US);
                numberFormatter.setMaximumFractionDigits(4);
                numberFormatter.setMinimumFractionDigits(0);

                System.out.println(numberFormatter.format(result.getValue()));
                return numberFormatter.format(result.getValue());
            }
        } else return null;

    }

    private void calculate(Stack<Token> operator, Stack<Token> value) {
        while (!operator.isEmpty() && operator.peek().getType() == Type.OPERATOR) {
            Token toProcess = operator.peek();
            operator.pop();
            Token A, B;
            if (value.isEmpty()) {
                Token.error = true;
                return;
            } else {
                B = value.peek();
                value.pop();
            }
            if (value.isEmpty()) {
                Token.error = true;
                return;
            } else {
                A = value.peek();
                value.pop();
            }
            Token result = toProcess.operate(A.getValue(), B.getValue());
            value.push(result);
        }

    }
}
