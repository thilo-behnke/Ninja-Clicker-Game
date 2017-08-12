package com.tbeh.ninjaclicker.reader.expression;

public abstract class ValueExpression implements Expression {

    private int value;

    ValueExpression(int value){
        this.value = value;
    }

    public abstract Enum evaluate();

    int getValue() {
        return value;
    }
}
