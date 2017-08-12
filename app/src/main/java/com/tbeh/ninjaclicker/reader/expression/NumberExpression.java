package com.tbeh.ninjaclicker.reader.expression;

import com.tbeh.ninjaclicker.reader.ReadTypes;

public class NumberExpression extends ValueExpression {

    public NumberExpression(int value) {
        super(value);
    }

    @Override
    public Enum evaluate() {
        return ReadTypes.Count.values()[getValue()];
    }
}
