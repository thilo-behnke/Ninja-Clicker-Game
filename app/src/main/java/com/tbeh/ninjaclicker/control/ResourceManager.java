package com.tbeh.ninjaclicker.control;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import com.tbeh.ninjaclicker.ninjaclicker.R;
import com.tbeh.ninjaclicker.model.sprite.CharacterEnum;
import com.tbeh.ninjaclicker.model.sprite.PowerUpEnum;

import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ResourceManager {

    private static ResourceManager instance;
    private static HashMap<Enum, Bitmap> spriteMap;
    private static HashMap<String, Bitmap> levelMap;
    private static HashMap<String, AssetFileDescriptor> soundMap;

    private ResourceManager(Context context) {
        spriteMap = new HashMap<>();
        levelMap = new HashMap<>();
        soundMap = new HashMap<>();
        //TODO: Load from file
        loadFiles(context);
    }

    public static ResourceManager getInstance(Context context) {
        if (instance == null) {
            instance = new ResourceManager(context);
        }
        return instance;
    }

    public Bitmap getSpriteFile(Enum spriteEnum) {
        try {
            return spriteMap.get(spriteEnum);
        } catch (NoSuchElementException e) {
            System.out.println("Enum konnte nicht gefunden werden");
            return null;
        }
    }

    public Bitmap getMapFile(String level) {
        if (level.equals("Level 1")) {
            return levelMap.get(level);
        }
        return null;
    }

    public AssetFileDescriptor getSoundFile(String soundname) {
        return soundMap.get(soundname);
    }


    private void loadFiles(Context context) {
        // character bitmaps
        spriteMap.put(CharacterEnum.NINJA_RED, BitmapFactory.decodeResource(context.getResources(), R.drawable.ninja_red50));
        spriteMap.put(CharacterEnum.NINJA_BLUE, BitmapFactory.decodeResource(context.getResources(), R.drawable.ninja_blue50));
        spriteMap.put(CharacterEnum.GIRL, BitmapFactory.decodeResource(context.getResources(), R.drawable.girl50));

        // powerup bitmaps
        spriteMap.put(PowerUpEnum.HAMMER, BitmapFactory.decodeResource(context.getResources(), R.drawable.hammer30));
        spriteMap.put(PowerUpEnum.SWORD, BitmapFactory.decodeResource(context.getResources(), R.drawable.sword30));

        // level background bitmaps
        levelMap.put("Title", BitmapFactory.decodeResource(context.getResources(), R.drawable.titlecard_nc));
        levelMap.put("Level 1", BitmapFactory.decodeResource(context.getResources(), R.drawable.background_nc));

        try {
//            soundMap.put("MAIN", context.getAssets().openFd("mario.mp3"));
//            soundMap.put("HAHA", context.getAssets().openFd("haha.wav"));
//            soundMap.put("LETSGO", context.getAssets().openFd("letsgo.wav"));
//            soundMap.put("LEAVEGAME", context.getAssets().openFd("bye.wav"));

            // https://developers.google.com/actions/tools/sound-library#Foley
            soundMap.put("MISS", context.getAssets().openFd("slap_with_glove.mp3"));
            soundMap.put("HAMMER_SELECTED", context.getAssets().openFd("thunder_crack.mp3"));
            soundMap.put("SWORD_SELECTED", context.getAssets().openFd("debris_hits.mp3"));
            soundMap.put("NINJAR_HURT", context.getAssets().openFd("cartoon_boing.mp3"));
            soundMap.put("GIRL_HURT", context.getAssets().openFd("cartoon_cowbell.mp3"));
            soundMap.put("NINJAB_HURT", context.getAssets().openFd("cartoon_ringing_hit.mp3"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
