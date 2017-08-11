package com.tbeh.ninjaclicker.spawn;

import com.tbeh.ninjaclicker.model.sprite.CharacterEnum;
import com.tbeh.ninjaclicker.model.sprite.Sprite;

import java.util.Random;

import static com.tbeh.ninjaclicker.model.sprite.CharacterEnum.NINJA_BLUE;
import static com.tbeh.ninjaclicker.model.sprite.CharacterEnum.NINJA_RED;
import static com.tbeh.ninjaclicker.model.sprite.CharacterEnum.GIRL;
import static com.tbeh.ninjaclicker.model.sprite.PowerUpEnum.HAMMER;
import static com.tbeh.ninjaclicker.model.sprite.PowerUpEnum.SWORD;

public abstract class BaseSpawnManager implements ISpawnManager {
    private static Random random = new Random();
    private Sprite marioTemplate;
    private Sprite luigiTemplate;
    private Sprite peachTemplate;
    private Spawner marioSpawner;
    private Spawner luigiSpawner;
    private Spawner peachSpawner;
    private Spawner hammerSpawner;
    private Spawner swordSpawner;


    public BaseSpawnManager() {
        marioTemplate = NINJA_RED.makePrototype();
        marioSpawner = new Spawner(marioTemplate);
        luigiTemplate = NINJA_BLUE.makePrototype();
        luigiSpawner = new Spawner(luigiTemplate);
        peachTemplate = GIRL.makePrototype();
        peachSpawner = new Spawner(peachTemplate);
        hammerSpawner = new Spawner(HAMMER.makePrototype());
        swordSpawner = new Spawner(SWORD.makePrototype());
    }

    Spawner getMarioSpawner() {
        return marioSpawner;
    }

    Spawner getLuigiSpawner() {
        return luigiSpawner;
    }

    Spawner getPeachSpawner() {
        return peachSpawner;
    }

    private Spawner getHammerSpawner() {
        return hammerSpawner;
    }

    private Spawner getSwordSpawner() {
        return swordSpawner;
    }

    public Sprite getCharacterPrototype(CharacterEnum characterEnum) {
        if (characterEnum.equals(NINJA_RED)) {
            return marioTemplate;
        } else if (characterEnum.equals(NINJA_BLUE)) {
            return luigiTemplate;
        } else if (characterEnum.equals(GIRL)) {
            return peachTemplate;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Sprite spawnPowerUp() {
        int randomNumber = random.nextInt(10);
        if (randomNumber <= 5) {
            return getHammerSpawner().getSpriteCopy();
        } else if (randomNumber > 5 && randomNumber <= 10) {
            return getSwordSpawner().getSpriteCopy();
        }
        return null;
    }
}
