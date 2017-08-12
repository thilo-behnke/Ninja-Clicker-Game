package com.tbeh.ninjaclicker.spawn;

import com.tbeh.ninjaclicker.model.level.LevelParameter;
import com.tbeh.ninjaclicker.model.level.LevelSetting;
import com.tbeh.ninjaclicker.model.sprite.CharacterEnum;
import com.tbeh.ninjaclicker.model.sprite.Sprite;

import java.util.ArrayList;
import java.util.HashMap;

public interface ISpawnManager {

    void setUpSpawnManager(HashMap<LevelParameter.Type, LevelSetting> spriteMap);
    ArrayList<Sprite> spawnMinions();
    Sprite spawnPowerUp();
    Sprite getCharacterPrototype(CharacterEnum characterEnum);

}
