package com.tbeh.ninjaclicker.reader.expression;

import android.support.annotation.Nullable;

import com.tbeh.ninjaclicker.reader.WorldObjects;
import com.tbeh.ninjaclicker.reader.command.Command;
import com.tbeh.ninjaclicker.reader.command.GoalCommand;

public class GoalExpression extends OperationExpression {

    public GoalExpression(@Nullable ValueExpression left, @Nullable ValueExpression right) {
        super(left, right);
    }

    @Override
    public Command evaluate(WorldObjects.Operation operation) {
        if(WorldObjects.Goal.values()[getRight().getValue()].getAttributeType() != null){
            if(WorldObjects.Goal.values()[getRight().getValue()].getAttributeType().equals(WorldObjects.ListAttributes.class)){

            }
        }
        return null;
//        return new GoalCommand(
//                getLeft() != null ? (WorldObjects.GameObject) getLeft().evaluate() : null,
//                operation,
//                getRight() != null ? (WorldObjects.Goal) getRight().evaluate() : null);
    }
}
