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
    private Sprite ninjaRTemplate;
    private Sprite ninjaBTemplate;
    private Sprite girlTemplate;
    private Spawner ninjaRSpawner;
    private Spawner ninjaBSpawner;
    private Spawner girlSpawner;
    private Spawner hammerSpawner;
    private Spawner swordSpawner;


    public BaseSpawnManager() {
        ninjaRTemplate = NINJA_RED.makePrototype();
        ninjaRSpawner = new Spawner(ninjaRTemplate);
        ninjaBTemplate = NINJA_BLUE.makePrototype();
        ninjaBSpawner = new Spawner(ninjaBTemplate);
        girlTemplate = GIRL.makePrototype();
        girlSpawner = new Spawner(girlTemplate);
        hammerSpawner = new Spawner(HAMMER.makePrototype());
        swordSpawner = new Spawner(SWORD.makePrototype());
    }

    Spawner getNinjaRSpawner() {
        return ninjaRSpawner;
    }

    Spawner getNinjaBSpawner() {
        return ninjaBSpawner;
    }

    Spawner getGirlSpawner() {
        return girlSpawner;
    }

    private Spawner getHammerSpawner() {
        return hammerSpawner;
    }

    private Spawner getSwordSpawner() {
        return swordSpawner;
    }

    @Override
    public Sprite getCharacterPrototype(CharacterEnum characterEnum) {
        switch (characterEnum) {
            case NINJA_RED:
                return ninjaRTemplate;
            case NINJA_BLUE:
                return ninjaBTemplate;
            case GIRL:
                return girlTemplate;
            default:
                return null;
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
