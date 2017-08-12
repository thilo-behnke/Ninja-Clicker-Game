package com.tbeh.ninjaclicker.reader.command;

import com.tbeh.ninjaclicker.main.GameEngine;
import com.tbeh.ninjaclicker.main.World;
import com.tbeh.ninjaclicker.model.sprite.CharacterEnum;
import com.tbeh.ninjaclicker.model.sprite.Sprite;
import com.tbeh.ninjaclicker.reader.ReadTypes;

import java.util.ArrayList;
import java.util.List;

import static com.tbeh.ninjaclicker.reader.ReadTypes.Operation.ADD;

public class SpriteListCommand implements Command {

    private CharacterEnum characterEnum;
    private ReadTypes.Operation operation;
    private ReadTypes.Count count;

    public SpriteListCommand(CharacterEnum characterEnum, ReadTypes.Operation operation, ReadTypes.Count count) {
        this.characterEnum = characterEnum;
        this.count = count;
        this.operation = operation;
    }

    @Override
    public void execute(World world) {
        List<Sprite> spriteList = new ArrayList<>();
        if (operation.equals(ADD)) {
            for (int i = 0; i < count.ordinal(); i++) {
                spriteList.add(world.getSpawnManager().getCharacterPrototype(characterEnum));
            }
        }
        world.getSpriteList().addAll(spriteList);
    }
}
