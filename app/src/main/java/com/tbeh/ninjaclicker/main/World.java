package com.tbeh.ninjaclicker.main;

import com.tbeh.ninjaclicker.model.sprite.Sprite;
import com.tbeh.ninjaclicker.spawn.ISpawnManager;

import java.util.List;

public class World {
    /**
     * A list that holds all sprites currently on screen.
     */
    private static List<Sprite> spriteList;
    /**
     * Manages the spawning of sprites.
     */
    private static ISpawnManager spawnManager;

    public static List<Sprite> getSpriteList() {
        return spriteList;
    }

    public static void setSpriteList(List<Sprite> spriteList) {
        World.spriteList = spriteList;
    }

    public static ISpawnManager getSpawnManager() {
        return spawnManager;
    }

    public static void setSpawnManager(ISpawnManager spawnManager) {
        World.spawnManager = spawnManager;
    }
}
