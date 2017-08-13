package com.tbeh.ninjaclicker.spawn;

import com.tbeh.ninjaclicker.model.level.LevelParameter;
import com.tbeh.ninjaclicker.model.level.LevelSetting;
import com.tbeh.ninjaclicker.model.sprite.CharacterEnum;
import com.tbeh.ninjaclicker.model.sprite.Sprite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomSpawnManager extends BaseSpawnManager {
    private static Random random = new Random();
    //TODO: Work out functionality for difficulty
    private String difficulty;


    private int spawnNumber;

    public RandomSpawnManager() {
        super();
    }

    public void setUpSpawnManager(HashMap<LevelParameter.Type, LevelSetting> spawnParameters) {
        this.difficulty = spawnParameters.get(LevelParameter.Type.DIFFICULTY).getAdditionalParameter1();
        this.spawnNumber = Integer.parseInt(spawnParameters.get(LevelParameter.Type.RANDOM).getAdditionalParameter1());
    }

    public ArrayList<Sprite> spawnMinions() {
        ArrayList<Sprite> spriteList = new ArrayList<>();
        for (int i = 0; i < spawnNumber; i++) {
            int randomNumber = random.nextInt(100);

            if (randomNumber <= CharacterEnum.NINJA_RED.getRareness()
                    && randomNumber > CharacterEnum.NINJA_BLUE.getRareness()) {
                spriteList.add(getNinjaRSpawner().getSpriteCopy());
            }
            else if (randomNumber <= CharacterEnum.NINJA_RED.getRareness()
                    && randomNumber > CharacterEnum.GIRL.getRareness()) {
                spriteList.add(getNinjaBSpawner().getSpriteCopy());
            }
            else {
                spriteList.add(getGirlSpawner().getSpriteCopy());
            }
        }
        return spriteList;
    }

}
