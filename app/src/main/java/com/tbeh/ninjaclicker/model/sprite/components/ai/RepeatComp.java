package com.tbeh.ninjaclicker.model.sprite.components.ai;

import com.tbeh.ninjaclicker.model.sprite.Sprite;
import com.tbeh.ninjaclicker.model.sprite.components.ComponentsHolder;

public class RepeatComp extends AiComp {

    private AiComp aiComp;

    private int times;
//    private int originalTimes;

    public RepeatComp(AiComp aiComp){
        this.aiComp = aiComp;
    }

    private RepeatComp(RepeatComp repeatComp, ComponentsHolder holder) {
        super(holder);
        this.times = -1;
//        this.originalTimes = times;
        this.aiComp = repeatComp.getAiComp().makeCopy(holder);
    }

    @Override
    public RepeatComp makeCopy(ComponentsHolder holder) {
        return new RepeatComp(this, holder);
    }

    @Override
    public void reset() {
        aiComp.reset();
    }

    @Override
    public void start() {
        super.start();
        this.aiComp.start();
    }

    @Override
    public void act(Sprite sprite) {
        if (aiComp.isFailure()) {
            fail();
        } else if (aiComp.isSuccess()) {
            if (times == 0) {
                succeed();
                return;
            }
            if (times > 0 || times <= -1) {
                times--;
                aiComp.start();
                aiComp.reset();
            }
        }
        if (aiComp.isRunning()) {
            aiComp.act(sprite);
        }
    }

    private AiComp getAiComp() {
        return aiComp;
    }
}
