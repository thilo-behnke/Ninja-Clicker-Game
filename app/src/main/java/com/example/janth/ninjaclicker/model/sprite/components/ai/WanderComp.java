package com.example.janth.ninjaclicker.model.sprite.components.ai;

import android.graphics.Bitmap;

import com.example.janth.ninjaclicker.main.GameEngine;
import com.example.janth.ninjaclicker.model.sprite.Sprite;
import com.example.janth.ninjaclicker.model.sprite.components.ComponentsHolder;

public class WanderComp extends AiComp {

    private MoveComp moveComp;

    public WanderComp(MoveComp moveComp){
        this.moveComp = moveComp;
    }

    private WanderComp (WanderComp wanderComp, ComponentsHolder holder){
        super(holder);
        this.moveComp = wanderComp.getMoveComp().makeCopy(holder);
    }

    @Override
    public WanderComp makeCopy (ComponentsHolder holder) {
        return new WanderComp(this, holder);
    }

    @Override
    public void reset(){
        Bitmap bitmap = getHolder().shared().getBitmap();
        moveComp.reset(
                GameEngine.getScreenManager().getRandomPositionXWithinMap(bitmap),
                GameEngine.getScreenManager().getRandomPositionYWithinMap(bitmap)
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
