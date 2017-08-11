package com.tbeh.ninjaclicker.collision;

import android.content.Context;

import com.tbeh.ninjaclicker.model.sprite.Sprite;

public class HammerCollisionDetection extends AdvancedCollisionDetection {

    HammerCollisionDetection(Context context) {
        super(context);
    }

    public boolean isPointWithinSpriteBoundaries(int pointX, int pointY, Sprite sprite) {
        return pointX + 100 >= sprite.comp().physics().X()
                && pointX - 100 <= sprite.comp().physics().X() + sprite.comp().shared().getBitmap().getWidth()
                && pointY + 100 >= sprite.comp().physics().Y()
                && pointY - 100 <= sprite.comp().physics().Y() + sprite.comp().shared().getBitmap().getHeight();
    }
}
