package com.example.janth.ninjaclicker.spawn;

import com.example.janth.ninjaclicker.model.level.LevelParameter;
import com.example.janth.ninjaclicker.model.level.LevelSetting;
import com.example.janth.ninjaclicker.model.sprite.Sprite;

import java.util.ArrayList;
import java.util.HashMap;

public interface ISpawnManager {

    void setUpSpawnManager(HashMap<LevelParameter.Type, LevelSetting> spriteMap);
    ArrayList<Sprite> spawnMinions();
    Sprite spawnPowerUp();

}
