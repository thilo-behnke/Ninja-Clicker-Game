package com.tbeh.ninjaclicker.model.sprite.components.graphics;


import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;

import com.tbeh.ninjaclicker.model.sprite.Sprite;
import com.tbeh.ninjaclicker.model.sprite.components.IComponent;

public class SharedComp implements IComponent {

    private Bitmap bitmap;
    private int score;
    private AssetFileDescriptor sound;

    public SharedComp(Bitmap bitmap, int score, AssetFileDescriptor sound) {
        this.bitmap = bitmap;
        this.score = score;
        this.sound = sound;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getScore() {
        return score;
    }

    public AssetFileDescriptor getSound() { return sound; }

    @Override
    public void initialize(Sprite sprite) {

    }

    @Override
    public void update(Sprite sprite) {

    }

    public String toString() {
        return getClass().getSimpleName()
                + ", " + "Width: " + bitmap.getWidth()
                + ", " + "Height: " + bitmap.getHeight()
                + ", " + "ScoreManager: " + score;
    }
}
