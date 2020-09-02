package com.tsystems.javaschool.tasks.calculator;

public class Token {
    public static boolean error = false;

    private Type type;
    private double value;
    private char operator;
    private int precedence;

    public Token() {
        type = Type.UNKNOWN;
    }

    public Token(String contents) {
        switch (contents) {
            case "+":
            case "-":
                type = Type.OPERATOR;
                operator = contents.charAt(0);
                precedence = 1;
                break;
            case "*":
            case "/":
                type = Type.OPERATOR;
                operator = contents.charAt(0);
                precedence = 2;
                break;
            case "(":
                type = Type.LEFT_PARENTHESIS;
                break;
            case ")":
                type = Type.RIGHT_PARENTHESIS;
                break;
            default:
                type = Type.NUMBER;
                try {
                    value = Double.parseDouble(contents);
                } catch (Exception ex) {
                    type = Type.UNKNOWN;
                }
        }
    }

    public Token(double x) {
        type = Type.NUMBER;
        value = x;
    }

    Type getType() {
        return type;
    }

    double getValue() {
        return value;
    }

    int getPrecedence() {
        return precedence;
    }

    Token operate(double a, double b) {
        double result = 0;
        switch (operator) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                if (b != 0) {
                    result = a / b;
                } else {
                    error = true;
                }
                break;
        }
        return new Token(result);
    }

}

enum Type {
    UNKNOWN,
    NUMBER,
    OPERATOR,
    LEFT_PARENTHESIS,
    RIGHT_PARENTHESIS,
}
