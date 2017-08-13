package com.tbeh.ninjaclicker.reader.command;

import com.tbeh.ninjaclicker.GameTimer;
import com.tbeh.ninjaclicker.main.World;
import com.tbeh.ninjaclicker.reader.WorldObjects;

public class GameObjectCommand implements Command {

    private WorldObjects.GameObject gameObject;
    private WorldObjects.Operation operation;
    private WorldObjects.Count count;

    public GameObjectCommand(WorldObjects.GameObject gameObject, WorldObjects.Operation operation, WorldObjects.Count count) {
        this.gameObject = gameObject;
        this.operation = operation;
        this.count = count;
    }

    @Override
    public void execute(World world) {
        switch (gameObject){
            case GAME_TIMER:
                world.setGameTimer(new GameTimer());
                if(operation.equals(WorldObjects.Operation.SET)) world.getGameTimer().startTimer(count.ordinal() * 1000);
        }
    }
}
