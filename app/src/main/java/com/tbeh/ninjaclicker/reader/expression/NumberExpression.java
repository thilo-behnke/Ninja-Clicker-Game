package com.tbeh.ninjaclicker.reader.expression;

import com.tbeh.ninjaclicker.reader.WorldObjects;

public class NumberExpression extends ValueExpression {

    public NumberExpression(int value) {
        super(value);
    }

    @Override
    public Enum evaluate() {
        return WorldObjects.Count.values()[getValue()];
    }
}
