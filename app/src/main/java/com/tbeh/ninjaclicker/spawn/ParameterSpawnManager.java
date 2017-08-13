package com.tbeh.ninjaclicker.spawn;

import com.tbeh.ninjaclicker.model.level.LevelParameter;
import com.tbeh.ninjaclicker.model.level.LevelSetting;
import com.tbeh.ninjaclicker.model.sprite.Sprite;

import java.util.ArrayList;
import java.util.HashMap;

public class ParameterSpawnManager extends BaseSpawnManager {

    public ArrayList<Sprite> spriteList;

    public ParameterSpawnManager(){
        super();
    }

    public void setUpSpawnManager (HashMap<LevelParameter.Type, LevelSetting> spriteMap){

        spriteList = new ArrayList<>();

        for(int i = 0; i < Integer.parseInt(spriteMap.get(LevelParameter.CHARACTER_MARIO).getAdditionalParameter1()); i++){
            spriteList.add(getNinjaRSpawner().getSpriteCopy());
        }


        for(int i = 0; i < spriteMap.size(); i++){
            switch (spriteMap.get(i).getLevelParameter()){
                case CHARACTER_MARIO:
                    spriteList.add(getNinjaRSpawner().getSpriteCopy());
                    break;
                case CHARACTER_LUIGI:
                    spriteList.add(getNinjaBSpawner().getSpriteCopy());
                    break;
                case CHARACTER_PEACH:
                    spriteList.add(getGirlSpawner().getSpriteCopy());
                    break;
            }
        }
    }

    public ArrayList<Sprite> spawnMinions(){
        return spriteList;
    }

}
