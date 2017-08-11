package com.tbeh.ninjaclicker.model.sprite.components.general;

import com.tbeh.ninjaclicker.model.sprite.components.ComponentsHolder;
import com.tbeh.ninjaclicker.model.sprite.components.ai.FlyAwayComp;

public class FlyAwayState implements State {

    @Override
    public void enter(ComponentsHolder holder) {
        holder.replaceAiComponent(new FlyAwayComp(holder, 50));
    }

    @Override
    public void exit(ComponentsHolder holder) {

    }

}
