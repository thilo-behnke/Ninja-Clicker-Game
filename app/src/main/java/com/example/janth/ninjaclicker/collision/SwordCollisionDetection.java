package com.example.janth.ninjaclicker.collision;

import android.content.Context;

import com.example.janth.ninjaclicker.model.sprite.Sprite;

// TODO: When hit, the direction of the sword should be put into calculation
class SwordCollisionDetection extends AdvancedCollisionDetection {

    SwordCollisionDetection(Context context) {
        super(context);
    }
    public boolean isPointWithinSpriteBoundaries(int pointX, int pointY, Sprite sprite) {
        return pointX >= sprite.comp().physics().X()
                && pointX <= sprite.comp().physics().X() + sprite.comp().shared().getBitmap().getWidth()
                && pointY >= sprite.comp().physics().Y()
                && pointY <= sprite.comp().physics().Y() + sprite.comp().shared().getBitmap().getHeight();
    }
}
