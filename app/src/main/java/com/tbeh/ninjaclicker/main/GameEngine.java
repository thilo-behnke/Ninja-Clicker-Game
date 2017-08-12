package com.tbeh.ninjaclicker.main;

import android.content.Context;
import android.graphics.Point;

import com.tbeh.ninjaclicker.GameTimer;
import com.tbeh.ninjaclicker.animation.DyingAnimation;
import com.tbeh.ninjaclicker.control.ScoreManager;
import com.tbeh.ninjaclicker.activity.BaseGameActivity;
import com.tbeh.ninjaclicker.collision.CollisionDetector;
import com.tbeh.ninjaclicker.collision.NormalCollisionDetection;
import com.tbeh.ninjaclicker.control.ScreenManager;
import com.tbeh.ninjaclicker.model.level.LevelParameter;
import com.tbeh.ninjaclicker.model.level.LevelSetting;
import com.tbeh.ninjaclicker.model.sprite.CharacterEnum;
import com.tbeh.ninjaclicker.model.sprite.PowerUp;
import com.tbeh.ninjaclicker.model.sprite.PowerUpEnum;
import com.tbeh.ninjaclicker.model.sprite.Sprite;
import com.tbeh.ninjaclicker.model.sprite.components.ai.FallFlatComp;
import com.tbeh.ninjaclicker.model.sprite.components.ai.FlyAwayComp;
import com.tbeh.ninjaclicker.spawn.RandomSpawnManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static android.os.SystemClock.sleep;
import static com.tbeh.ninjaclicker.main.GameEngine.RoundStatus.ROUND_LOST;
import static com.tbeh.ninjaclicker.main.GameEngine.RoundStatus.ROUND_RUNNING;
import static com.tbeh.ninjaclicker.main.GameEngine.RoundStatus.ROUND_WON;

public class GameEngine implements Runnable {

    /**
     * Detects collions on Screen.
     */
    private static CollisionDetector collisionDetector;
    /**
     * The currently active powerup.
     */
    private static PowerUpEnum activePowerUp;
    /**
     * Keeps the scoreManager of the current round.
     */
    private static ScoreManager scoreManager;

    private static ScreenManager screenManager;
    public static GameTimer gameTimer;
    private static GameTimer powerUpTimer;
    private static GameTimer activePowerUpTimer;
    private static Sprite powerUp;
    private static Random random;
    private static boolean isRunning;
    private static int level;
    private static List<Point> collisionList;

    private RoundStatus roundStatus;

    enum RoundStatus {
        ROUND_RUNNING, ROUND_LOST, ROUND_WON;
    }

    private HashMap<LevelParameter.Type, LevelSetting> settings;

    public GameEngine(Context context) {

        settings = new HashMap<>();
        settings.put(LevelParameter.Type.DIFFICULTY, new LevelSetting(LevelParameter.DIFFICULTY_EASY));
        settings.put(LevelParameter.Type.RANDOM, new LevelSetting(LevelParameter.SPRITE_COUNT, ((BaseGameActivity) context).getIntent().getStringExtra("SpriteNumber")));

        level = 1;
        isRunning = true;
        screenManager = ScreenManager.getInstance(context);
        random = new Random();
        scoreManager = ScoreManager.getInstance();
        World.setSpriteList(Collections.synchronizedList(new ArrayList<>()));
        collisionDetector = CollisionDetector.getInstance(new NormalCollisionDetection(context));
        activePowerUp = PowerUpEnum.NONE;
        World.setSpawnManager(new RandomSpawnManager());
        collisionList = Collections.synchronizedList(new ArrayList<Point>());

        ((BaseGameActivity) context).registerObserver(scoreManager);
    }

    @Override
    public void run() {
        init();
        gameLoop();
    }

    private void init() {
        gameTimer = new GameTimer();
        powerUpTimer = new GameTimer();
        activePowerUp = PowerUpEnum.NONE;
    }

    private void startInitialRound() {
        World.getSpawnManager().setUpSpawnManager(settings);
        World.getSpriteList().clear();
        World.getSpriteList().addAll(World.getSpawnManager().spawnMinions());
        gameTimer.startTimer(10000);
        roundStatus = ROUND_RUNNING;
    }

    private void startNewRound() {
        gameTimer.cancelTimer();
        nextLevel();
        World.getSpriteList().clear();
        World.getSpriteList().addAll(World.getSpawnManager().spawnMinions());
        gameTimer.startTimer(10000);
        roundStatus = ROUND_RUNNING;
    }

    private void gameLoop() {
        final int FRAMES_PER_SECOND = 25;
        final int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
        long sleepTime;
        long nextGameTick = System.currentTimeMillis();

        startInitialRound();

        while (isRunning) {
            nextGameTick += SKIP_TICKS;
            sleepTime = nextGameTick - System.currentTimeMillis();
            if (sleepTime >= 0) {
                sleep(sleepTime);
            }
            checkWinCondition();
            update();
        }
    }

    public static void update() {
        updateSprites();
        spawnRandomPowerUp();
        updatePowerUpTimer();
    }

    private static void updateSprites() {
        List<Sprite> toRemove = new ArrayList<>();
        for (Sprite sprite : World.getSpriteList()) {
            if (sprite.comp().general().isDying()) {
                sprite.comp().general().setDead();
                sprite.comp().general().setAnimation(new DyingAnimation(20));
                if(activePowerUp.equals(PowerUpEnum.HAMMER)){
                    sprite.comp().replaceAiComponent(new FallFlatComp(sprite.comp(), 50));
                } else if (activePowerUp.equals(PowerUpEnum.SWORD)){
                    sprite.comp().replaceAiComponent(new FlyAwayComp(sprite.comp(), 200));
                } else {
                    sprite.comp().replaceAiComponent(new FlyAwayComp(sprite.comp(), 50));
                }
                sprite.comp().ai().initialize(sprite);
            } else if (sprite.comp().general().isDead()
                    && screenManager.isOutsideOfScreen(sprite)) {
                toRemove.add(sprite);
            } else {
                sprite.update();
            }
        }
        synchronized (World.getSpriteList()) {
            World.getSpriteList().removeAll(toRemove);
        }
    }

    private static void spawnRandomPowerUp() {
        if (powerUp == null) {
            int randomNumber = random.nextInt(100);
            if (randomNumber >= 95) {
                powerUp = World.getSpawnManager().spawnPowerUp();
                if (powerUp.getType().equals(PowerUpEnum.HAMMER)) {
                    powerUpTimer.startTimer(5000);
                } else if (powerUp.getType().equals(PowerUpEnum.SWORD)) {
                    powerUpTimer.startTimer(2000);
                }
            }
        } else {
            if (powerUpTimer.isFinished() || powerUp.comp().general().isDead()) {
                powerUp = null;
            }
        }
    }

    private static void updatePowerUpTimer() {
        PowerUpEnum activePowerUp = GameEngine.getActivePowerUp();
        if (activePowerUp.equals(PowerUpEnum.HAMMER)) {
            if (activePowerUpTimer == null) {
                activePowerUpTimer = new GameTimer();
                activePowerUpTimer.startTimer(20000);
            } else {
                if (activePowerUpTimer.isFinished()) {
                    GameEngine.setActivePowerUpType(PowerUpEnum.NONE);
                    activePowerUpTimer = null;
                }
            }
        } else if (activePowerUp.equals(PowerUpEnum.SWORD)) {
            if (activePowerUpTimer == null) {
                activePowerUpTimer = new GameTimer();
                activePowerUpTimer.startTimer(10000);
            } else {
                if (activePowerUpTimer.isFinished()) {
                    GameEngine.setActivePowerUpType(PowerUpEnum.NONE);
                    activePowerUpTimer = null;
                }
            }
        }
    }

    private void checkWinCondition() {
        switch (roundStatus) {
            case ROUND_RUNNING:
                if (gameTimer.isFinished()) {
                    roundStatus = ROUND_LOST;
                }
                if (World.getSpriteList().isEmpty() || World.getSpriteList().stream().allMatch(x -> x.getType().equals(CharacterEnum.GIRL))) {
//                if (GameEngine.getSpriteList().isEmpty() || onlyContains(CharacterEnum.PEACH)) {
                    roundStatus = ROUND_WON;
                }
                break;
            case ROUND_LOST:
                GameEngine.getScoreManager().updateScoreLevel(false, level);
                startNewRound();
                break;
            case ROUND_WON:
                GameEngine.getScoreManager().updateScoreLevel(true, level);
                startNewRound();
                break;
        }
    }

    public static boolean isRunning() {
        return isRunning;
    }

    public static void setRunning(boolean running) {
        isRunning = running;
    }

    public static PowerUp getPowerUp() {
        return (PowerUp) powerUp;
    }

    public static CollisionDetector getCollisionDetector() {
        return collisionDetector;
    }

    public static PowerUpEnum getActivePowerUp() {
        return activePowerUp;
    }

    public static void setActivePowerUpType(PowerUpEnum powerUpType) {
        activePowerUp = powerUpType;
    }

    public static ScoreManager getScoreManager() {
        return scoreManager;
    }

    public static ScreenManager getScreenManager() {
        return screenManager;
    }

    public static int getLevel() {
        return level;
    }

    private void nextLevel() {
        level++;
    }

    public static List<Point> getCollisionList() {
        return collisionList;
    }
}
