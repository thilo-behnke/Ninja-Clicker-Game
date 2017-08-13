package com.tbeh.ninjaclicker.reader.expression;

import com.tbeh.ninjaclicker.reader.ReadTypes;
import com.tbeh.ninjaclicker.reader.command.Command;
import com.tbeh.ninjaclicker.reader.command.GameObjectCommand;

public class TimerExpression extends OperationExpression {

    public TimerExpression(GameObjectExpression left, NumberExpression right) {
        super(left, right);
    }

    @Override
    public Command evaluate(ReadTypes.Operation operation) {
        return new GameObjectCommand((ReadTypes.GameObject) getLeft().evaluate(), operation, (ReadTypes.Count)getRight().evaluate());
    }
}
