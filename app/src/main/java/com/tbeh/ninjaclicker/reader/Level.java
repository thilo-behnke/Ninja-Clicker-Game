package com.tbeh.ninjaclicker.reader;


import com.tbeh.ninjaclicker.GameTimer;
import com.tbeh.ninjaclicker.model.sprite.Sprite;

import java.util.List;

public class Level {

    private GameTimer gameTimer;
    private List<Sprite> spriteList;
    private WorldObjects.Goal goal;

    public Level(GameTimer gameTimer, List<Sprite> spriteList, WorldObjects.Goal goal) {
        this.gameTimer = gameTimer;
        this.spriteList = spriteList;
        this.goal = goal;
    }

    public GameTimer getGameTimer() {
        return gameTimer;
    }

    public List<Sprite> getSpriteList() {
        return spriteList;
    }

    public WorldObjects.Goal getGoal() {
        return goal;
    }

    @Override
    public String toString() {
        return "Level{" +
                "gameTimer=" + gameTimer +
                ", spriteList=" + spriteList +
                ", goal=" + goal +
                '}';
    }
}
