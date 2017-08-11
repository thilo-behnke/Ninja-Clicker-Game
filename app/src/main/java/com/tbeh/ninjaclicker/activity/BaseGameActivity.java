package com.tbeh.ninjaclicker.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.tbeh.ninjaclicker.main.GameEngine;
import com.tbeh.ninjaclicker.handler.ThreadHandler;
import com.tbeh.ninjaclicker.model.Message;
import com.tbeh.ninjaclicker.model.sprite.PowerUpEnum;
import com.tbeh.ninjaclicker.view.EndlessGameView;

public class BaseGameActivity extends BaseActivity {
    /**
     * Renders the graphics.
     */
    private EndlessGameView gameView;
    /**
     * Controls logic.
     */
    private GameEngine gameEngine;
    /**
     * Handles threads.
     */
    private ThreadHandler threadHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameEngine = new GameEngine(this);
        gameView = new EndlessGameView(this, gameEngine);
        gameView.setOnTouchListener(new CustomTouchListener());

        threadHandler = new ThreadHandler();
        setContentView(gameView);
    }

    @Override
    public void onPause() {
        super.onPause();
        GameEngine.setRunning(false);
        threadHandler.pauseThreads();
    }

    @Override
    public void onResume() {
        super.onResume();
        GameEngine.setRunning(true);
        // Add logic Thread
        threadHandler.addThread(new Thread(gameEngine));
        // Add UI Thread
        threadHandler.addThread(new Thread(gameView));
        threadHandler.resumeThreads();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        notifyObservers(Message.Event.LEAVEGAME);
    }

    void detectCollision(final int x, final int y, final int action) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                PowerUpEnum powerUp = GameEngine.getActivePowerUp();
                if (powerUp.equals(PowerUpEnum.SWORD) || action == MotionEvent.ACTION_DOWN) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            GameEngine.getCollisionDetector().detectCollision(x, y);
                        }
                    });
                }
                return null;
            }
        }.execute();
    }

    public class CustomTouchListener implements View.OnTouchListener {

        CustomTouchListener() {
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            detectCollision((int) event.getX(), (int) event.getY(), event.getActionMasked());
            return true;
        }

    }
}


