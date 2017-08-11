package com.tbeh.ninjaclicker.control;

import com.tbeh.ninjaclicker.main.GameEngine;
import com.tbeh.ninjaclicker.model.CustomObserver;
import com.tbeh.ninjaclicker.model.sprite.CharacterEnum;
import com.tbeh.ninjaclicker.model.sprite.Sprite;
import com.tbeh.ninjaclicker.spawn.BaseSpawnManager;

import java.util.ArrayList;

public class ScoreManager implements CustomObserver {

    private static ScoreManager instance;

    private int score;
    private ArrayList<Integer> roundsWon;

    private ScoreManager() {
        roundsWon = new ArrayList<>();
    }

    public static ScoreManager getInstance() {
        if (instance == null) {
            instance = new ScoreManager();
        }
        return instance;
    }

    public void updateScoreSprite(Sprite sprite) {
        score += sprite.comp().shared().getScore();
    }

    public void updateScoreLevel(boolean succeeded, int level) {
        if (succeeded) {
            score += 2000;
            roundsWon.add(level);
        } else {
            score -= 1000;
        }
    }

    @Override
    public void onNotify(Enum enumMessage) {
        if(enumMessage.getDeclaringClass().equals(CharacterEnum.class)){
            updateScoreSprite(((BaseSpawnManager) GameEngine.getSpawnManager()).getCharacterPrototype((CharacterEnum)enumMessage));
        }
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "ScoreManager{" +
                "score=" + score +
                ", roundsWon=" + roundsWon +
                '}';
    }

}
