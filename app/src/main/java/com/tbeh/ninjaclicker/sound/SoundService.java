package com.tbeh.ninjaclicker.sound;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

public class SoundService {

    private MediaPlayer musicPlayer;
    private MediaPlayer soundPlayer;

    public SoundService() {
        musicPlayer = new MediaPlayer();
        soundPlayer = new MediaPlayer();
    }

    void playMusic(AssetFileDescriptor descriptor) {
        try {
            if (!musicPlayer.isPlaying()) {
                musicPlayer.reset();
                musicPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
                musicPlayer.prepare();
                musicPlayer.start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void stopMusic() {
        if (musicPlayer.isPlaying()) {
            musicPlayer.reset();
        }
    }

    void playSound(AssetFileDescriptor descriptor) {
        try {
            soundPlayer.reset();
            soundPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            soundPlayer.prepare();
            soundPlayer.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void stopSound() {
        soundPlayer.reset();
    }
}
