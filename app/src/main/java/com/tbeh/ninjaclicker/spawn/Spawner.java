package com.tbeh.ninjaclicker.spawn;

import com.tbeh.ninjaclicker.model.sprite.Sprite;

public class Spawner {

    private Sprite spawnerTemplate;

    public Spawner(Sprite sprite){
        spawnerTemplate = sprite;
    }

    public Sprite getSpriteCopy(){
        return spawnerTemplate.makeCopy(spawnerTemplate);
    }

}
