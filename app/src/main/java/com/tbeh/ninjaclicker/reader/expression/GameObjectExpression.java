package com.tbeh.ninjaclicker.reader.expression;

import com.tbeh.ninjaclicker.reader.WorldObjects;

public class GameObjectExpression extends ValueExpression {

    public GameObjectExpression(int value) {
        super(value);
    }

    @Override
    public Enum evaluate() {
        return WorldObjects.GameObject.values()[getValue()];
    }
}
