package com.example.janth.ninjaclicker.animation;

public class DyingAnimation implements IAnimation {

    private int rotation;
    private int rotationVelocity;

    public DyingAnimation(int rotationVelocity) {
        this.rotationVelocity = rotationVelocity;
    }

    @Override
    public void update() {
        rotation += rotationVelocity;
    }

    @Override
    public int getEffect() {
        return rotation;
    }
}
