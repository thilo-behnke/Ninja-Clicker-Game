package com.tbeh.ninjaclicker.reader.expression;

import android.support.annotation.Nullable;

import com.tbeh.ninjaclicker.reader.WorldObjects;
import com.tbeh.ninjaclicker.reader.command.Command;

public abstract class OperationExpression implements Expression {

    @Nullable
    private ValueExpression left;
    @Nullable
    private ValueExpression right;

    public OperationExpression(ValueExpression left, ValueExpression right) {
        this.left = left;
        this.right = right;
    }

    public abstract Command evaluate(WorldObjects.Operation operation);

    public ValueExpression getLeft() {
        return left;
    }

    public ValueExpression getRight() {
        return right;
    }
}
