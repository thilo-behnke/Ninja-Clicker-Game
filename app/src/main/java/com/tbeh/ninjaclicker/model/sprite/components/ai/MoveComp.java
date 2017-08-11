package com.tbeh.ninjaclicker.model.sprite.components.ai;

import com.tbeh.ninjaclicker.model.sprite.Sprite;
import com.tbeh.ninjaclicker.model.sprite.components.ComponentsHolder;
import com.tbeh.ninjaclicker.model.sprite.components.general.GeneralComp;

public class MoveComp extends AiComp {

    private int destX;
    private int destY;

    public MoveComp (){}

    MoveComp (ComponentsHolder holder){
        super(holder);
    }

    private MoveComp (MoveComp moveComp, ComponentsHolder holder){
        super(holder);
        destX = moveComp.getDestX();
        destY = moveComp.getDestY();
    }

    @Override
    public MoveComp makeCopy (ComponentsHolder holder) {
        return new MoveComp(this, holder);
    }

    @Override
    public void reset() {}

    void reset(int newX, int newY) {
        if(newX > getHolder().physics().X()){
            getHolder().general().setDirection(GeneralComp.Direction.RIGHT);
        } else if (newX < getHolder().physics().X()){
            getHolder().general().setDirection(GeneralComp.Direction.LEFT);
        }
        destX = newX;
        destY = newY;
    }

    @Override
    public void act(Sprite sprite) {
        if (isRunning()) {
            moveSprite(sprite);
        }
    }

    private void moveSprite(Sprite sprite) {
        if (sprite.comp().physics().move(this).equals(AiComp.RoutineState.Success)){
            succeed();
        }
    }

    public int getDestX() {
        return destX;
    }

    public int getDestY() {
        return destY;
    }

}
