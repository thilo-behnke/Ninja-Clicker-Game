package com.tbeh.ninjaclicker.main;

import com.tbeh.ninjaclicker.GameTimer;
import com.tbeh.ninjaclicker.model.sprite.Sprite;
import com.tbeh.ninjaclicker.spawn.ISpawnManager;

import java.util.List;

public class World {
    private static GameTimer gameTimer;
    /**
     * A list that holds all sprites currently on screen.
     */
    private static List<Sprite> spriteList;
    /**
     * Manages the spawning of sprites.
     */
    private static ISpawnManager spawnManager;

    private static GameEngine.WinCondition winCondition;

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

    public static GameTimer getGameTimer() {
        return gameTimer;
    }

    public static void setGameTimer(GameTimer gameTimer) {
        World.gameTimer = gameTimer;
    }

    public static GameEngine.WinCondition getWinCondition() {
        return winCondition;
    }

    public static void setWinCondition(GameEngine.WinCondition winCondition) {
        World.winCondition = winCondition;
    }
}
