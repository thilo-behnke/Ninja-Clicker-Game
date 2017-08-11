package com.example.janth.ninjaclicker.model.sprite.components.ai;

import android.graphics.Bitmap;
import android.graphics.Point;

import com.example.janth.ninjaclicker.main.GameEngine;
import com.example.janth.ninjaclicker.model.sprite.Sprite;
import com.example.janth.ninjaclicker.model.sprite.components.ComponentsHolder;

public class FallFlatComp extends AiComp {

    private MoveComp moveComp;

    public FallFlatComp(ComponentsHolder holder, int velocity) {
        super(holder);
        moveComp = new MoveComp(holder);
        holder.physics().setVelocity(velocity);
    }

    private FallFlatComp(FallFlatComp fallFlatComp, ComponentsHolder holder) {
        super(holder);
        this.moveComp = fallFlatComp.getMoveComp().makeCopy(holder);
    }

    @Override
    public FallFlatComp makeCopy(ComponentsHolder holder) {
        return new FallFlatComp(this, holder);
    }

    @Override
    public void reset() {
        Bitmap bitmap = getHolder().shared().getBitmap();
        Point point = GameEngine.getScreenManager().getPositionBelowMap(getHolder().physics().X(), bitmap);
        moveComp.reset(
                point.x,
                point.y
        );
    }

    @Override
    public void start() {
        super.start();
        this.moveComp.start();
    }

    @Override
    public void act(Sprite sprite) {
        if (!this.moveComp.isRunning()) {
            return;
        }
        this.moveComp.act(sprite);
        if (this.moveComp.isSuccess()) {
            succeed();
        } else if (this.moveComp.isFailure()) {
            fail();
        }
    }

    private MoveComp getMoveComp() {
        return moveComp;
    }

}
