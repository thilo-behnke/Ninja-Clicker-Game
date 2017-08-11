package com.tbeh.ninjaclicker.spawn;

import com.tbeh.ninjaclicker.model.level.LevelParameter;
import com.tbeh.ninjaclicker.model.level.LevelSetting;
import com.tbeh.ninjaclicker.model.sprite.Sprite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomSpawnManager extends BaseSpawnManager {
    private static Random random = new Random();
    //TODO: Work out functionality for difficulty
    private String difficulty;

    private final int SPAWN_MARIO = 0;
    private final int SPAWN_LUIGI = 5;
    private final int SPAWN_PEACH = 9;

    private int spawnNumber;

    public RandomSpawnManager(){
        super();
    }

    public void setUpSpawnManager(HashMap<LevelParameter.Type, LevelSetting> spawnParameters){
        this.difficulty = spawnParameters.get(LevelParameter.Type.DIFFICULTY).getAdditionalParameter1();
        this.spawnNumber = Integer.parseInt(spawnParameters.get(LevelParameter.Type.RANDOM).getAdditionalParameter1());
    }

    public ArrayList<Sprite> spawnMinions(){
        ArrayList<Sprite> spriteList = new ArrayList<>();
        for(int i = 0; i < spawnNumber; i++){
            int randomNumber = random.nextInt(10);
            if(randomNumber>=SPAWN_MARIO && randomNumber<SPAWN_LUIGI){
                spriteList.add(getMarioSpawner().getSpriteCopy());
            }
            else if(randomNumber>=SPAWN_LUIGI && randomNumber<SPAWN_PEACH){
                spriteList.add(getLuigiSpawner().getSpriteCopy());
            }
            else if(randomNumber>=SPAWN_PEACH){
                spriteList.add(getPeachSpawner().getSpriteCopy());
            }
        }
        return spriteList;
    }

}
