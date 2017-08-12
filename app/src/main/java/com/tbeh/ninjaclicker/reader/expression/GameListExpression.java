package com.tbeh.ninjaclicker.reader.expression;

import com.tbeh.ninjaclicker.model.sprite.CharacterEnum;
import com.tbeh.ninjaclicker.reader.ReadTypes;
import com.tbeh.ninjaclicker.reader.command.Command;
import com.tbeh.ninjaclicker.reader.command.SpriteListCommand;

public class GameListExpression extends OperationExpression {

    public GameListExpression(CharacterExpression left, NumberExpression right) {
        super(left, right);
    }

    @Override
    public Command evaluate(ReadTypes.Operation operation) {
        return new SpriteListCommand((CharacterEnum)getLeft().evaluate(), operation, (ReadTypes.Count)getRight().evaluate());
    }
}
