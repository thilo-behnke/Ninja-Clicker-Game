package com.tbeh.ninjaclicker.reader.command;

import com.tbeh.ninjaclicker.main.GameEngine;
import com.tbeh.ninjaclicker.main.World;
import com.tbeh.ninjaclicker.model.sprite.CharacterEnum;
import com.tbeh.ninjaclicker.model.sprite.Sprite;
import com.tbeh.ninjaclicker.reader.ReadTypes;
import com.tbeh.ninjaclicker.spawn.BaseSpawnManager;
import com.tbeh.ninjaclicker.spawn.ISpawnManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        List<Sprite> spriteList = world.getSpriteList();
        Sprite sprite =
                characterEnum != null ? world.getSpawnManager().getCharacterPrototype(characterEnum)
                : null;
        switch (operation) {
            case ADD:
                IntStream.range(1, count.getIndex()).forEach(x -> spriteList.add(sprite));
                break;
            case REMOVE:
                IntStream.range(1, count.getIndex()).forEach(x -> spriteList.remove(sprite));
                break;
            case REMOVE_ALL:
                spriteList.removeIf(y -> y.getType().equals(sprite.getType()));
                break;
            case CLEAR:
                world.getSpriteList().clear();
        }
    }
}
