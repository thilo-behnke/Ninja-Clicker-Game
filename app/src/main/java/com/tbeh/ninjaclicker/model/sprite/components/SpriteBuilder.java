package com.tbeh.ninjaclicker.model.sprite.components;

import com.tbeh.ninjaclicker.model.sprite.Character;
import com.tbeh.ninjaclicker.model.sprite.CharacterEnum;
import com.tbeh.ninjaclicker.model.sprite.PowerUp;
import com.tbeh.ninjaclicker.model.sprite.Sprite;
import com.tbeh.ninjaclicker.model.sprite.components.ai.AiComp;
import com.tbeh.ninjaclicker.model.sprite.components.general.GeneralComp;
import com.tbeh.ninjaclicker.model.sprite.components.graphics.SharedComp;
import com.tbeh.ninjaclicker.model.sprite.components.physics.PhysicsComp;

import java.util.ArrayList;

public class SpriteBuilder {

    private ArrayList<IComponent> components;

    public SpriteBuilder() {
        components = new ArrayList<>();
    }

    public SpriteBuilder setGeneralComponent(GeneralComp generalComp) {
        components.add(generalComp);
        return this;
    }

    public SpriteBuilder SetSharedComp(SharedComp sharedComp) {
        components.add(sharedComp);
        return this;
    }

    public SpriteBuilder setPhysicsComponent(PhysicsComp physicsComp) {
        components.add(physicsComp);
        return this;
    }

    public SpriteBuilder setAiComponent(AiComp aiComponent) {
        components.add(aiComponent);
        return this;
    }

    public SpriteBuilder setComponents(ComponentsHolder components) {
        for (IComponent component : components.values()) {
            this.components.add(component);
        }
        return this;
    }

    public Sprite createSprite(Enum spriteEnum) {
        if (spriteEnum instanceof CharacterEnum) {
            return new Character(spriteEnum, new ComponentsHolder(components));
        } else {
            return new PowerUp(spriteEnum, new ComponentsHolder(components));
        }
    }
}
