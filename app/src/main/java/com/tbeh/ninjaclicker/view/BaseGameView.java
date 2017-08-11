package com.tbeh.ninjaclicker.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.tbeh.ninjaclicker.main.GameEngine;
import com.tbeh.ninjaclicker.GameTimer;
import com.tbeh.ninjaclicker.activity.BaseActivity;
import com.tbeh.ninjaclicker.animation.DyingAnimation;
import com.tbeh.ninjaclicker.model.sprite.PowerUp;
import com.tbeh.ninjaclicker.model.sprite.PowerUpEnum;
import com.tbeh.ninjaclicker.model.sprite.Sprite;
import com.tbeh.ninjaclicker.model.sprite.components.general.GeneralComp;

import java.util.Iterator;

import static android.os.SystemClock.sleep;
import static com.tbeh.ninjaclicker.main.GameEngine.getCollisionList;
import static com.tbeh.ninjaclicker.main.GameEngine.getSpriteList;
import static com.tbeh.ninjaclicker.main.GameEngine.isRunning;

public abstract class BaseGameView extends SurfaceView implements Runnable {

    private Bitmap background;
    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private GameEngine gameEngine;

    public BaseGameView(Context context, GameEngine gameEngine) {
        super(context);
        this.gameEngine = gameEngine;
    }

    public BaseGameView(Context context) {
        super(context);
    }

    @Override
    public void run() {
        init();
        gameLoop();
    }

    public void init() {
        paint = new Paint();
        surfaceHolder = getHolder();
        setBackgroundImage(BaseActivity.getResourceManager().getMapFile("Level 1"));
    }

    public void gameLoop() {
        final int FRAMES_PER_SECOND = 25;
        final int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
        long sleepTime;
        long nextGameTick = System.currentTimeMillis();

        while (isRunning()) {
            nextGameTick += SKIP_TICKS;
            sleepTime = nextGameTick - System.currentTimeMillis();
            if (sleepTime >= 0) {
                sleep(sleepTime);
            }
            render();
        }
    }

    public void render() {
        if (surfaceHolder.getSurface().isValid()) {
            Canvas canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);

            renderBackground(canvas);
            renderSprites(canvas);
            renderParticles(canvas);
            renderPowerUp(canvas);
            renderUI(canvas);

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void renderBackground(Canvas canvas) {
        canvas.drawBitmap(
                getBackgroundImage(),
                0,
                0,
                paint);
    }

    private void renderPowerUp(Canvas canvas) {
        PowerUp powerUp = gameEngine.getPowerUp();
        if (powerUp != null) {
            canvas.drawBitmap(powerUp.comp().shared().getBitmap(),
                    powerUp.comp().physics().X(),
                    powerUp.comp().physics().Y(),
                    paint);
        }
    }

    private void renderSprites(Canvas canvas) {
        synchronized (getSpriteList()) {
            for (Sprite sprite : gameEngine.getSpriteList()) {
                Bitmap bitmap = sprite.comp().shared().getBitmap();
                if (sprite.comp().general().getAnimation() instanceof DyingAnimation) {
                    Matrix matrix = new Matrix();
                    matrix.setRotate(sprite.comp().general().getAnimation().getEffect());
                    bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
                } else {
                    Matrix matrix = new Matrix();
                    if (sprite.comp().general().direction() == GeneralComp.Direction.LEFT) {
                        matrix.preScale(-1, 1);
                    }
                    bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
                }
                canvas.drawBitmap(
                        bitmap,
                        sprite.comp().physics().X(),
                        sprite.comp().physics().Y(),
                        paint);
            }

        }
    }

    private void renderParticles(Canvas canvas) {
        synchronized (gameEngine.getCollisionList()) {
            for (Iterator<Point> it = getCollisionList().iterator(); it.hasNext(); ) {
                Point p = it.next();
                canvas.drawText("Test", p.x, p.y, paint);
                it.remove();
            }
        }
    }

    private void renderUI(Canvas canvas) {
        paint.setColor(Color.RED);
        paint.setTextSize(100);
        canvas.drawText("Score: " + String.valueOf(gameEngine.getScoreManager().getScore()), 100, 100, paint);

        String timeString = gameEngine.gameTimer.getFormattedCurrentTime();
        paint.setColor(Color.BLACK);
        canvas.drawText("Time: " + String.valueOf(timeString), 800, 100, paint);

        paint.setColor(Color.GREEN);
        canvas.drawText("Level: " + String.valueOf(gameEngine.getLevel()), 1400, 100, paint);

        PowerUpEnum activePowerUp = gameEngine.getActivePowerUp();
        if (activePowerUp.equals(PowerUpEnum.HAMMER)) {
            paint.setColor(Color.BLUE);
            canvas.drawText("HAMMER! ", 1800, 100, paint);
        } else if (activePowerUp.equals(PowerUpEnum.SWORD)) {
            paint.setColor(Color.BLUE);
            canvas.drawText("SWORD! ", 1800, 100, paint);
        } else {
            paint.setColor(Color.BLUE);
            canvas.drawText("NormalMode!", 1800, 100, paint);
        }
    }

    protected GameTimer getGameTimer() {
        return gameEngine.gameTimer;
    }

    public Bitmap getBackgroundImage() {
        return background;
    }

    public void setBackgroundImage(Bitmap background) {
        this.background = background;
    }

}
