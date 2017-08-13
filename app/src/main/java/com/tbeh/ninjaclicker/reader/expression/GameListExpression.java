package com.tbeh.ninjaclicker.reader.expression;

import com.tbeh.ninjaclicker.model.sprite.CharacterEnum;
import com.tbeh.ninjaclicker.reader.WorldObjects;
import com.tbeh.ninjaclicker.reader.command.Command;
import com.tbeh.ninjaclicker.reader.command.SpriteListCommand;

public class GameListExpression extends OperationExpression {

    public GameListExpression(CharacterExpression left, NumberExpression right) {
        super(left, right);
    }

    @Override
    public Command evaluate(WorldObjects.Operation operation) {
        return new SpriteListCommand(
                getLeft() != null ? (CharacterEnum)getLeft().evaluate() : null,
                operation,
                getRight() != null ? (WorldObjects.Count)getRight().evaluate() : null
        );
    }
}
