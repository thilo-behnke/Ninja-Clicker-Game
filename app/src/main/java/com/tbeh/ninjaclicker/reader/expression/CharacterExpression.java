package com.tbeh.ninjaclicker.reader.expression;

import com.tbeh.ninjaclicker.model.sprite.CharacterEnum;

public class CharacterExpression extends ValueExpression {

    public CharacterExpression(int value) {
        super(value);
    }

    @Override
    public Enum evaluate() {
        return CharacterEnum.values()[getValue()];
    }

    @Override
    int getValue() {
        return super.getValue();
    }
}
