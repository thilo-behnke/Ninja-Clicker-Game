package com.example.janth.ninjaclicker.model.sprite.components.ai;

import android.graphics.Bitmap;
import android.graphics.Point;

import com.example.janth.ninjaclicker.main.GameEngine;
import com.example.janth.ninjaclicker.model.sprite.Sprite;
import com.example.janth.ninjaclicker.model.sprite.components.ComponentsHolder;

public class FlyAwayComp extends AiComp {

    private MoveComp moveComp;

    public FlyAwayComp(ComponentsHolder holder, int velocity){
        super(holder);
        moveComp = new MoveComp(holder);
        holder.physics().setVelocity(velocity);
    }

    private FlyAwayComp (FlyAwayComp flyAwayComp, ComponentsHolder holder){
        super(holder);
        this.moveComp = flyAwayComp.getMoveComp().makeCopy(holder);
    }

    @Override
    public FlyAwayComp makeCopy (ComponentsHolder holder) {
        return new FlyAwayComp(this, holder);
    }

    @Override
    public void reset() {
        Bitmap bitmap = getHolder().shared().getBitmap();
        Point point = GameEngine.getScreenManager().getRandomPositionOutsideMap(bitmap.getWidth(), bitmap.getHeight());
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
        if(!this.moveComp.isRunning()){
            return;
        }
        this.moveComp.act(sprite);
        if (this.moveComp.isSuccess()) {
            succeed();
        }
        else if (this.moveComp.isFailure()) {
            fail();
        }
    }

    private MoveComp getMoveComp(){
        return moveComp;
    }

}
