package com.example.janth.ninjaclicker.model.sprite;

import com.example.janth.ninjaclicker.activity.BaseActivity;
import com.example.janth.ninjaclicker.model.sprite.components.general.GeneralComp;
import com.example.janth.ninjaclicker.model.sprite.components.graphics.SharedComp;
import com.example.janth.ninjaclicker.model.sprite.components.SpriteBuilder;
import com.example.janth.ninjaclicker.model.sprite.components.physics.PhysicsComp;
import com.example.janth.ninjaclicker.model.sprite.components.ai.MoveComp;
import com.example.janth.ninjaclicker.model.sprite.components.ai.RepeatComp;
import com.example.janth.ninjaclicker.model.sprite.components.ai.SlideComp;
import com.example.janth.ninjaclicker.model.sprite.components.ai.WanderComp;

public enum CharacterEnum implements ICharacter {

    NINJA_RED {
        @Override
        public Sprite makePrototype() {
            SpriteBuilder builder = new SpriteBuilder();
            return builder
                    .setGeneralComponent(new GeneralComp())
                    .SetSharedComp(new SharedComp(
                            BaseActivity.getResourceManager().getSpriteFile(this),
                            SCORE_NINJAR,
                            BaseActivity.getResourceManager().getSoundFile("NINJAR_HURT")))
                    .setPhysicsComponent(new PhysicsComp(V_NINJAR))
                    .setAiComponent(new RepeatComp(new WanderComp(new MoveComp())))
                    .createSprite(this);
        }
    },

    NINJA_BLUE {
        @Override
        public Sprite makePrototype() {
            SpriteBuilder builder = new SpriteBuilder();
            return builder
                    .setGeneralComponent(new GeneralComp())
                    .SetSharedComp(new SharedComp(
                            BaseActivity.getResourceManager().getSpriteFile(this),
                            SCORE_NINJAB,
                            BaseActivity.getResourceManager().getSoundFile("NINJAB_HURT")))
                    .setPhysicsComponent(new PhysicsComp(V_NINJAB))
                    .setAiComponent(new RepeatComp(new WanderComp(new MoveComp())))
                    .createSprite(this);
        }
    },

    GIRL {
        @Override
        public Sprite makePrototype() {
            SpriteBuilder builder = new SpriteBuilder();
            return builder
                    .setGeneralComponent(new GeneralComp())
                    .SetSharedComp(new SharedComp(
                            BaseActivity.getResourceManager().getSpriteFile(this),
                            SCORE_GIRL,
                            BaseActivity.getResourceManager().getSoundFile("GIRL_HURT")))
                    .setPhysicsComponent(new PhysicsComp(V_GIRL))
                    .setAiComponent(new RepeatComp(new SlideComp(new MoveComp())))
                    .createSprite(this);
        }
    };

    private static final int V_NINJAR = 5;
    private static final int V_NINJAB = 20;
    private static final int V_GIRL = 5;

    private final static int SCORE_NINJAR = 100;
    private final static int SCORE_NINJAB = 200;
    private final static int SCORE_GIRL = -500;
}
