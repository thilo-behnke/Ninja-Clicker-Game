package com.example.janth.ninjaclicker.collision;

import android.content.Context;

import com.example.janth.ninjaclicker.model.sprite.Sprite;
import com.example.janth.ninjaclicker.model.sprite.components.graphics.SharedComp;
import com.example.janth.ninjaclicker.model.sprite.components.physics.PhysicsComp;

public class NormalCollisionDetection extends AdvancedCollisionDetection {

    public NormalCollisionDetection(Context context) {
        super(context);
    }

    public boolean isPointWithinSpriteBoundaries(int x, int y, Sprite sprite) {
        PhysicsComp physics = sprite.comp().physics();
        SharedComp graphics = sprite.comp().shared();
        return (x >= physics.X()
                && x <= physics.X() + graphics.getBitmap().getWidth()
                && y >= physics.Y()
                && y <= physics.Y() + graphics.getBitmap().getHeight());
    }
}
