package com.tbeh.ninjaclicker.reader.expression;

import com.tbeh.ninjaclicker.reader.WorldObjects;
import com.tbeh.ninjaclicker.reader.command.Command;
import com.tbeh.ninjaclicker.reader.command.GameObjectCommand;

public class TimerExpression extends OperationExpression {

    public TimerExpression(GameObjectExpression left, NumberExpression right) {
        super(left, right);
    }

    @Override
    public Command evaluate(WorldObjects.Operation operation) {
        return new GameObjectCommand((WorldObjects.GameObject) getLeft().evaluate(), operation, (WorldObjects.Count)getRight().evaluate());
    }
}
