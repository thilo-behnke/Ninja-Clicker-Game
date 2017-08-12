package com.tbeh.ninjaclicker.sound;

import android.os.AsyncTask;

import com.tbeh.ninjaclicker.activity.BaseActivity;
import com.tbeh.ninjaclicker.main.World;
import com.tbeh.ninjaclicker.model.CustomObserver;
import com.tbeh.ninjaclicker.model.Message;
import com.tbeh.ninjaclicker.model.sprite.CharacterEnum;
import com.tbeh.ninjaclicker.spawn.BaseSpawnManager;

public class SoundManager implements CustomObserver {

    private static SoundManager instance;
    private SoundService soundService;

    private SoundManager(SoundService soundService) {
        this.soundService = soundService;
    }

    public static SoundManager getInstance(SoundService soundService) {
        if (instance == null) {
            instance = new SoundManager(soundService);
        }
        return instance;
    }

    @Override
    public void onNotify(Enum messageEnum) {
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                if(messageEnum.getDeclaringClass().equals(Message.Event.class)){
                    if (messageEnum.equals(Message.Event.MISS)) {
                        soundService.playSound(BaseActivity.getResourceManager().getSoundFile("MISS"));
                    } else if (messageEnum.equals(Message.Event.START)) {
//                        soundService.playSound(BaseActivity.getResourceManager().getSoundFile("LETSGO"));
                    } else if (messageEnum.equals(Message.Event.STARTGAME)) {
                        soundService.stopMusic();
                        soundService.stopSound();
                    } else if (messageEnum.equals(Message.Event.MENU)) {
//                        soundService.playMusic(BaseActivity.getResourceManager().getSoundFile("MAIN"));
                    } else if (messageEnum.equals(Message.Event.LEAVEGAME)) {
//                        soundService.playSound(BaseActivity.getResourceManager().getSoundFile("LEAVEGAME"));
                        soundService.stopMusic();
                        soundService.stopSound();
                    } else if (messageEnum.equals(Message.Event.PAUSED)){
                        soundService.stopSound();
                        soundService.stopMusic();
                    }
                } else if(messageEnum.getDeclaringClass().equals(CharacterEnum.class)){
                    soundService.playSound(((BaseSpawnManager) World.getSpawnManager()).getCharacterPrototype((CharacterEnum)messageEnum).comp().shared().getSound());
                }
                return null;
            }
        }.execute();

    }
}
