package com.example.janth.ninjaclicker.model.sprite.components.physics;

import android.graphics.Bitmap;

import com.example.janth.ninjaclicker.main.GameEngine;
import com.example.janth.ninjaclicker.model.sprite.Sprite;
import com.example.janth.ninjaclicker.model.sprite.components.ComponentsHolder;
import com.example.janth.ninjaclicker.model.sprite.components.IComponent;
import com.example.janth.ninjaclicker.model.sprite.components.ai.AiComp;
import com.example.janth.ninjaclicker.model.sprite.components.ai.MoveComp;

public class PhysicsComp implements IComponent {

    private int x;
    private int y;
    private int velocity;
    private ComponentsHolder holder;

    public PhysicsComp(){}

    public PhysicsComp(int velocity) {
        x = 0;
        y = 0;
        this.velocity = velocity;
    }

    private PhysicsComp(PhysicsComp component, ComponentsHolder holder) {
        this.holder = holder;
        x = component.X();
        y = component.Y();
        velocity = component.getVelocity();
    }

    public PhysicsComp makeCopy(ComponentsHolder holder) {
        return new PhysicsComp(this, holder);
    }

    @Override
    public void update(Sprite sprite) {
    }

    @Override
    public void initialize(Sprite sprite) {
        randomizePosition(sprite.comp().shared().getBitmap());
    }

    private void randomizePosition(Bitmap bitmap) {
        x = GameEngine.getScreenManager().getRandomPositionXWithinMap(bitmap);
        y = GameEngine.getScreenManager().getRandomPositionYWithinMap(bitmap);
    }

    public AiComp.RoutineState move(AiComp aiComp) {
        if (aiComp instanceof MoveComp){
            int destX = ((MoveComp)aiComp).getDestX();
            int destY = ((MoveComp)aiComp).getDestY();
            Bitmap bitmap = holder.shared().getBitmap();
            if (!isPointWithinSpriteBoundaries(destX, destY, bitmap)) {
                if (destY > y + bitmap.getHeight()) {
                    y += velocity;
                } else if (destY < y) {
                    y -= velocity;
                }
                if (destX > x + bitmap.getWidth()) {
                    x += velocity;
                } else if (destX < x) {
                    x -= velocity;
                }
            } else {
                return AiComp.RoutineState.Success;
            }
        }
        return aiComp.getState();
    }

    private boolean isPointWithinSpriteBoundaries(int pointX, int pointY, Bitmap bitmap) {
        return (pointX >= X()
                && pointX <= X() + bitmap.getWidth()
                && pointY >= Y()
                && pointY <= Y() + bitmap.getHeight());
    }

    public int X() {
        return x;
    }

    public int Y() {
        return y;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public String toString() {
        return getClass().getSimpleName()
                + ", " + "PosX: " + X()
                + ", " + "PosY: " + Y()
                + ", " + "Velocity: " + getVelocity();
    }
}
