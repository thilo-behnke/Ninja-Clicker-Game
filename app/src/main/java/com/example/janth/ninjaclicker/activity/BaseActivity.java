package com.example.janth.ninjaclicker.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.janth.ninjaclicker.control.ResourceManager;
import com.example.janth.ninjaclicker.model.CustomObservable;
import com.example.janth.ninjaclicker.model.CustomObserver;
import com.example.janth.ninjaclicker.sound.SoundManager;
import com.example.janth.ninjaclicker.sound.SoundService;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity implements CustomObservable {

    private List<CustomObserver> observerList;
    private static ResourceManager resourceManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().hide();

        resourceManager = ResourceManager.getInstance(getApplicationContext());
        observerList = new ArrayList<>();
        registerObserver(SoundManager.getInstance(new SoundService()));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void registerObserver(CustomObserver customObserver) {
        if (!observerList.contains(customObserver)) {
            observerList.add(customObserver);
        }
    }

    @Override
    public void removeObserver(CustomObserver customObserver) {
        if (observerList.contains(customObserver)) {
            observerList.remove(customObserver);
        }
    }

    @Override
    public void notifyObservers(Enum enumMessage) {
        for (CustomObserver observer : observerList) {
            observer.onNotify(enumMessage);
        }
    }

    public static ResourceManager getResourceManager(){
        return resourceManager;
    }
}
