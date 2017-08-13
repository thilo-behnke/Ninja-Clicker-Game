package com.tbeh.ninjaclicker.reader.expression;

import com.tbeh.ninjaclicker.reader.ReadTypes;

public class GameObjectExpression extends ValueExpression {

    public GameObjectExpression(int value) {
        super(value);
    }

    @Override
    public Enum evaluate() {
        return ReadTypes.GameObject.values()[getValue()];
    }
}
