package com.tbeh.ninjaclicker.reader.command;

import com.tbeh.ninjaclicker.GameTimer;
import com.tbeh.ninjaclicker.main.World;
import com.tbeh.ninjaclicker.reader.ReadTypes;

public class GameObjectCommand implements Command {

    private ReadTypes.GameObject gameObject;
    private ReadTypes.Operation operation;
    private ReadTypes.Count count;

    public GameObjectCommand(ReadTypes.GameObject gameObject, ReadTypes.Operation operation, ReadTypes.Count count) {
        this.gameObject = gameObject;
        this.operation = operation;
        this.count = count;
    }

    @Override
    public void execute(World world) {
        switch (gameObject){
            case GAME_TIMER:
                world.setGameTimer(new GameTimer());
                if(operation.equals(ReadTypes.Operation.SET)) world.getGameTimer().startTimer(count.ordinal() * 1000);
        }
    }
}
