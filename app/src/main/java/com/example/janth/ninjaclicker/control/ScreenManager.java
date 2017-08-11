package com.example.janth.ninjaclicker.control;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.example.janth.ninjaclicker.model.sprite.Sprite;
import com.example.janth.ninjaclicker.model.sprite.components.physics.PhysicsComp;

import java.util.Random;

public class ScreenManager {

    private static ScreenManager instance;

    private static Random random = new Random();

    private int boundaryTop;
    private int boundaryBottom;
    private int boundaryLeft;
    private int boundaryRight;

    private ScreenManager(Context context) {
        loadScreen(context);
    }

    public static ScreenManager getInstance(Context context) {
        if (instance == null) {
            instance = new ScreenManager(context);
        }
        return instance;
    }

    private void loadScreen(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        boundaryTop = 0;
        boundaryBottom = size.y;
        boundaryLeft = 0;
        boundaryRight = size.x;
    }

    private int getBoundaryLeft() {
        return boundaryLeft;
    }

    private int getBoundaryBottom() {
        return boundaryBottom;
    }

    private int getBoundaryTop() {
        return boundaryTop;
    }

    private int getBoundaryRight() {
        return boundaryRight;
    }

    public int getRandomPositionXWithinMap(Bitmap bitmap) {
        try {
            return random.nextInt(getBoundaryRight() - bitmap.getWidth());
        } catch (NullPointerException e) {
            Log.d("Sprite", bitmap.toString());
        }
        return 0;
    }

    public int getRandomPositionYWithinMap(Bitmap bitmap) {
        return random.nextInt(getBoundaryBottom()
                - bitmap.getHeight());
    }

    public Point getRandomPositionOutsideMap(int width, int height) {
        int randomInt = random.nextInt(100 - 1 + 1) + 1;
        if (randomInt < 25) {
            // left
            int x = getBoundaryLeft() - 2 * width;
            int y = random.nextInt(getBoundaryBottom() - getBoundaryTop() + 1) + getBoundaryTop();
            return new Point(x, y);
        } else if (randomInt >= 25 && randomInt < 50) {
            // right
            int x = getBoundaryRight() + 2 * width;
            int y = random.nextInt(getBoundaryBottom() - getBoundaryTop() + 1) + getBoundaryTop();
            return new Point(x, y);
        } else if (randomInt >= 50 && randomInt < 75) {
            // up
            int x = random.nextInt(getBoundaryRight() - getBoundaryLeft() + 1) + getBoundaryLeft();
            int y = getBoundaryTop() - 2 * height;
            return new Point(x, y);
        } else {
            // down
            int x = random.nextInt(getBoundaryRight() - getBoundaryLeft() + 1) + getBoundaryLeft();
            int y = getBoundaryBottom() + 2 * height;
            return new Point(x, y);
        }
    }

    public Point getPositionBelowMap(int x, Bitmap bitmap) {
        return new Point(x, getBoundaryBottom() + bitmap.getHeight());
    }

    public boolean isOutsideOfScreen(Sprite sprite) {
        PhysicsComp physics = sprite.comp().physics();
        Bitmap bitmap = sprite.comp().shared().getBitmap();
        return physics.X() + bitmap.getWidth() < getBoundaryLeft()
                || physics.X() > getBoundaryRight()
                || physics.Y() + bitmap.getHeight() < getBoundaryTop()
                || physics.Y() > getBoundaryBottom();
    }

}
