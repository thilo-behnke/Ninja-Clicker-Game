package com.tbeh.ninjaclicker.reader.expression;

import android.support.annotation.Nullable;

public abstract class ValueExpression implements Expression {

    private int value;

    @Nullable
    private ValueExpression right;

    ValueExpression(int value){
        this.value = value;
    }

    public abstract Enum evaluate();

    int getValue() {
        return value;
    }

    @Nullable
    public ValueExpression nextExpression() {
        return right;
    }
}
