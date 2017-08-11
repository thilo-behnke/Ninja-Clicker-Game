package com.example.janth.ninjaclicker.model.sprite.components.ai;

import android.graphics.Bitmap;

import com.example.janth.ninjaclicker.main.GameEngine;
import com.example.janth.ninjaclicker.model.sprite.Sprite;
import com.example.janth.ninjaclicker.model.sprite.components.ComponentsHolder;

public class SlideComp extends AiComp {

    private MoveComp moveComp;

    public SlideComp(MoveComp moveComp){
        this.moveComp = moveComp;
    }

    private SlideComp (SlideComp slideComp, ComponentsHolder holder){
        super(holder);
        this.moveComp = slideComp.getMoveComp().makeCopy(holder);
    }

    @Override
    public SlideComp makeCopy (ComponentsHolder holder) {
        return new SlideComp(this, holder);
    }

    @Override
    public void reset() {
        Bitmap bitmap = getHolder().shared().getBitmap();
        moveComp.reset(
                GameEngine.getScreenManager().getRandomPositionXWithinMap(bitmap),
                getHolder().physics().Y()
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
