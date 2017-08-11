package com.tbeh.ninjaclicker.model.sprite.components.ai;

import com.tbeh.ninjaclicker.model.sprite.Sprite;
import com.tbeh.ninjaclicker.model.sprite.components.ComponentsHolder;
import com.tbeh.ninjaclicker.model.sprite.components.IComponent;

public abstract class AiComp implements IComponent {

    public enum RoutineState {
        Success,
        Failure,
        Running
    }

    private AiComp.RoutineState state;

    private ComponentsHolder holder;

    public AiComp() {
    }

    AiComp(ComponentsHolder holder) {
        setHolder(holder);
    }

    public abstract AiComp makeCopy(ComponentsHolder holder);

    @Override
    public void update(Sprite sprite) {
        act(sprite);
    }

    @Override
    public void initialize(Sprite sprite) {
        reset();
        start();
    }

    public abstract void reset();

    public ComponentsHolder getHolder() {
        return holder;
    }

    private void setHolder(ComponentsHolder holder) {
        this.holder = holder;
    }

    public void start() {
        this.state = AiComp.RoutineState.Running;
    }

    public abstract void act(Sprite sprite);

    protected void succeed() {
        this.state = AiComp.RoutineState.Success;
    }

    protected void fail() {
        this.state = AiComp.RoutineState.Failure;
    }

    public boolean isSuccess() {
        return state.equals(AiComp.RoutineState.Success);
    }

    public boolean isFailure() {
        return state.equals(AiComp.RoutineState.Failure);
    }

    public boolean isRunning() {
        return state.equals(AiComp.RoutineState.Running);
    }

    public AiComp.RoutineState getState() {
        return state;
    }

    public String toString() {
        return getClass().getSimpleName()
                + ", " + "Routine: " + state.toString();
    }

}
