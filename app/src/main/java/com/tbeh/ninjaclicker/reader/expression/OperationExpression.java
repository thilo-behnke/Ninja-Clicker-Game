package com.tbeh.ninjaclicker.reader.expression;

import com.tbeh.ninjaclicker.reader.ReadTypes;
import com.tbeh.ninjaclicker.reader.command.Command;

public abstract class OperationExpression implements Expression {

    private CharacterExpression left;
    private NumberExpression right;

    public OperationExpression(CharacterExpression left, NumberExpression right) {
        this.left = left;
        this.right = right;
    }

    public abstract Command evaluate(ReadTypes.Operation operation);

    public CharacterExpression getLeft() {
        return left;
    }

    public NumberExpression getRight() {
        return right;
    }
}
