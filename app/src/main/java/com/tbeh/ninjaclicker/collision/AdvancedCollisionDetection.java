package com.tbeh.ninjaclicker.collision;

import android.content.Context;
import android.graphics.Point;

import com.tbeh.ninjaclicker.main.GameEngine;
import com.tbeh.ninjaclicker.activity.BaseActivity;
import com.tbeh.ninjaclicker.main.World;
import com.tbeh.ninjaclicker.model.Message;
import com.tbeh.ninjaclicker.model.sprite.PowerUp;
import com.tbeh.ninjaclicker.model.sprite.PowerUpEnum;
import com.tbeh.ninjaclicker.model.sprite.Sprite;

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
        synchronized (World.getSpriteList()) {
            boolean hit = false;
            Sprite hitSprite = null;
            for (Sprite sprite : World.getSpriteList()) {
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
