package com.example.janth.ninjaclicker.collision;

import android.content.Context;
import android.graphics.Point;

import com.example.janth.ninjaclicker.main.GameEngine;
import com.example.janth.ninjaclicker.activity.BaseActivity;
import com.example.janth.ninjaclicker.model.Message;
import com.example.janth.ninjaclicker.model.sprite.PowerUp;
import com.example.janth.ninjaclicker.model.sprite.PowerUpEnum;
import com.example.janth.ninjaclicker.model.sprite.Sprite;

abstract class AdvancedCollisionDetection implements CollisionDetection {

    private PowerUp powerUp;
    private Context context;

    AdvancedCollisionDetection(Context context) {
        this.context = context;
    }

    public void detectCollision(int touchX, int touchY) {
        powerUp = GameEngine.getPowerUp();
        handleCollisionSprites(touchX, touchY);
        handleCollisionPowerUp(touchX, touchY);
    }

    private void handleCollisionSprites(int touchX, int touchY) {
        synchronized (GameEngine.getSpriteList()) {
            boolean hit = false;
            Sprite hitSprite = null;
            for (Sprite sprite : GameEngine.getSpriteList()) {
                if (isPointWithinSpriteBoundaries(touchX, touchY, sprite) && sprite.comp().general().isAlive()) {
                    hit = true;
                    hitSprite = sprite;
                    GameEngine.getCollisionList().add(new Point(touchX, touchY));
                    sprite.comp().general().setDying();
                }
            }
            if (!hit) ((BaseActivity) context).notifyObservers(Message.Event.MISS);
            else ((BaseActivity) context).notifyObservers(hitSprite.getType());
        }
    }

    private void handleCollisionPowerUp(int touchX, int touchY) {
        if (powerUp != null && GameEngine.getPowerUp() != null) {
            if (isPointWithinSpriteBoundaries(touchX, touchY, powerUp)) {
                if (powerUp.getType().equals(PowerUpEnum.HAMMER)) {
                    GameEngine.getCollisionDetector().changeStrategy(new HammerCollisionDetection(context));
                } else if (powerUp.getType().equals(PowerUpEnum.SWORD)) {
                    GameEngine.getCollisionDetector().changeStrategy(new SwordCollisionDetection(context));
                }
                GameEngine.setActivePowerUpType((PowerUpEnum) powerUp.getType());
                powerUp.comp().general().setDead();
            }
        }
    }

    abstract boolean isPointWithinSpriteBoundaries(int pointX, int pointY, Sprite sprite);

}
