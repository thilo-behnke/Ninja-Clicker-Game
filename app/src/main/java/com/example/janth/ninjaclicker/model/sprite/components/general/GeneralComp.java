package com.example.janth.ninjaclicker.model.sprite.components.general;

import android.graphics.Bitmap;

import com.example.janth.ninjaclicker.activity.BaseActivity;
import com.example.janth.ninjaclicker.animation.IAnimation;
import com.example.janth.ninjaclicker.animation.SimpleAnimation;
import com.example.janth.ninjaclicker.model.sprite.Sprite;
import com.example.janth.ninjaclicker.model.sprite.components.ComponentsHolder;
import com.example.janth.ninjaclicker.model.sprite.components.IComponent;

public class GeneralComp implements IComponent {

    public enum Direction {
        LEFT, RIGHT;
    }

    public enum State {
        ALIVE, DYING, DEAD;
    }

    private State state;
    private ComponentsHolder holder;
    private Direction direction;

    private IAnimation animation;

    public GeneralComp() {
        state = State.ALIVE;
        animation = new SimpleAnimation();
        direction = Direction.RIGHT;
    }

    private GeneralComp(ComponentsHolder holder) {
        this();
        setHolder(holder);
    }

    public GeneralComp makeCopy(ComponentsHolder holder) {
        return new GeneralComp(holder);
    }

    public void initialize(Sprite sprite) {
    }

    public void update(Sprite sprite) {
        animation.update();
    }

    public Bitmap getBitmap(Sprite sprite) {
        return BaseActivity.getResourceManager().getSpriteFile(sprite.getType());
    }

    private void setHolder(ComponentsHolder holder) {
        this.holder = holder;
    }

    public boolean isAlive() {
        return state == State.ALIVE;
    }

    public boolean isDying() {
        return state == State.DYING;
    }

    public boolean isDead() {
        return state == State.DEAD;
    }

    public void setDying() {
        this.state = State.DYING;
    }

    public void setDead() {
        this.state = State.DEAD;
    }

    public Direction direction() { return direction; }

    public void setDirection(Direction direction) { this.direction = direction; }

    public IAnimation getAnimation() {
        return animation;
    }

    public void setAnimation(IAnimation animation) {
        this.animation = animation;
    }

    public String toString() {
        return getClass().getSimpleName()
                + ", " + "isAlive: " + isAlive();
    }
}
