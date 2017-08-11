package com.example.janth.ninjaclicker.model.sprite;

import com.example.janth.ninjaclicker.model.sprite.components.ComponentsHolder;
import com.example.janth.ninjaclicker.model.sprite.components.SpriteBuilder;

public abstract class Sprite {

    private Enum spriteEnum;
    private ComponentsHolder components;

    public Sprite (Enum spriteEnum, ComponentsHolder components){
        this.spriteEnum = spriteEnum;
        this.components = components;
    }

    public void update() {
        comp().update(this);
    }

    private void initializeComponents() {
        comp().initialize(this);
    }

    public ComponentsHolder comp(){
        return components;
    }

    public Enum getType(){
        return spriteEnum;
    }

    public Sprite makeCopy(Sprite sprite) {
        SpriteBuilder builder = new SpriteBuilder();
        Sprite s = builder.setComponents(sprite.comp().makeCopy()).createSprite(sprite.getType());
        s.initializeComponents();
        return s;
    }

    public String toString() {
        return getClass().getSimpleName()
                + ", " + hashCode()
                + ", " + getType().toString()
                + ", " + comp().toString();
    }
}
