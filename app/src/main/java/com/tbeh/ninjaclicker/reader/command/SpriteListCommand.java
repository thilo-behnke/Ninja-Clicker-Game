package com.tbeh.ninjaclicker.reader.command;

import com.tbeh.ninjaclicker.main.World;
import com.tbeh.ninjaclicker.model.sprite.CharacterEnum;
import com.tbeh.ninjaclicker.model.sprite.Sprite;
import com.tbeh.ninjaclicker.reader.WorldObjects;

import java.util.List;
import java.util.stream.IntStream;

public class SpriteListCommand implements Command {

    private CharacterEnum characterEnum;
    private WorldObjects.Operation operation;
    private WorldObjects.Count count;

    public SpriteListCommand(CharacterEnum characterEnum, WorldObjects.Operation operation, WorldObjects.Count count) {
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
