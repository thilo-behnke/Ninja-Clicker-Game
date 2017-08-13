package com.tbeh.ninjaclicker.reader.command;

import com.tbeh.ninjaclicker.main.World;
import com.tbeh.ninjaclicker.model.sprite.CharacterEnum;
import com.tbeh.ninjaclicker.reader.WorldObjects;

import java.util.List;

public class GoalCommand implements Command {

    private WorldObjects.GameObject gameObject;
    private List<CharacterEnum> characters;
    private WorldObjects.Goal goal;

    public GoalCommand(WorldObjects.GameObject gameObject, List<CharacterEnum> characters, WorldObjects.Goal goal) {
        this.gameObject = gameObject;
        this.characters = characters;
        this.goal = goal;
    }

    @Override
    public void execute(World world) {
        switch (goal){
            case KILL_CERTAIN:

        }
    }
}
