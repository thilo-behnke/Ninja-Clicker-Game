package com.tbeh.ninjaclicker.view;

import android.content.Context;

import com.tbeh.ninjaclicker.FileParser;
import com.tbeh.ninjaclicker.main.GameEngine;
import com.tbeh.ninjaclicker.LevelLoader;

public class LevelGameView extends BaseGameView {

    private static int roundStatus;
    private final int ROUND_RUNNING = 0;
    private final int ROUND_LOST = 1;
    private final int ROUND_WON = 2;

    private LevelLoader levelLoader;

    public LevelGameView(Context context, GameEngine gameEngine) {
        super(context, gameEngine);
    }

    @Override
    public void init() {
        super.init();
        levelLoader = new LevelLoader(new FileParser(getContext(), 1));
    }

    public void checkWinCondition() {
        switch (roundStatus) {
            case ROUND_RUNNING:
                if (getGameTimer().isFinished()) {
                    roundStatus = ROUND_LOST;
                }
//                if (GameEngine.getSpriteList().isEmpty() || GameEngine.getSpriteList().onlyContains(CharacterEnum.PEACH)) {
//                    roundStatus = ROUND_WON;
//                }
                break;
            case ROUND_LOST:
                GameEngine.getScoreManager().updateScoreLevel(false, GameEngine.getLevel());
                reloadRound();
                break;
            case ROUND_WON:
                GameEngine.getScoreManager().updateScoreLevel(true, GameEngine.getLevel());
                startNewRound();
                break;
        }
    }

    public void startInitialRound() {
        levelLoader.loadLevelData();
        GameEngine.getSpawnManager().setUpSpawnManager(levelLoader.getSpriteMap());
        GameEngine.getSpriteList().addAll(GameEngine.getSpawnManager().spawnMinions());
        getGameTimer().startTimer(10000);
        roundStatus = ROUND_RUNNING;
    }

    public void reloadRound(){
        getGameTimer().cancelTimer();
        GameEngine.getSpriteList().addAll(GameEngine.getSpawnManager().spawnMinions());
        getGameTimer().startTimer(10000);
        roundStatus = ROUND_RUNNING;
    }

    public void startNewRound() {
        getGameTimer().cancelTimer();
        levelLoader.loadLevelData();
        GameEngine.getSpawnManager().setUpSpawnManager(levelLoader.getSpriteMap());
        GameEngine.getSpriteList().addAll(GameEngine.getSpawnManager().spawnMinions());
        getGameTimer().startTimer(10000);
        roundStatus = ROUND_RUNNING;
    }
}
