package com.tbeh.ninjaclicker.model.sprite;

import com.tbeh.ninjaclicker.activity.BaseActivity;
import com.tbeh.ninjaclicker.model.sprite.components.general.GeneralComp;
import com.tbeh.ninjaclicker.model.sprite.components.graphics.SharedComp;
import com.tbeh.ninjaclicker.model.sprite.components.SpriteBuilder;
import com.tbeh.ninjaclicker.model.sprite.components.physics.PhysicsComp;
import com.tbeh.ninjaclicker.model.sprite.components.ai.MoveComp;
import com.tbeh.ninjaclicker.model.sprite.components.ai.RepeatComp;
import com.tbeh.ninjaclicker.model.sprite.components.ai.SlideComp;
import com.tbeh.ninjaclicker.model.sprite.components.ai.WanderComp;

public enum CharacterEnum implements ICharacter {

    NINJA_RED {
        @Override
        public Sprite makePrototype() {
            SpriteBuilder builder = new SpriteBuilder();
            return builder
                    .setGeneralComponent(new GeneralComp())
                    .SetSharedComp(new SharedComp(
                            BaseActivity.getResourceManager().getSpriteFile(this),
                            getScore(),
                            BaseActivity.getResourceManager().getSoundFile("NINJAR_HURT")))
                    .setPhysicsComponent(new PhysicsComp(getVelocity()))
                    .setAiComponent(new RepeatComp(new WanderComp(new MoveComp())))
                    .createSprite(this);
        }

        @Override
        public int getVelocity() {
            return 5;
        }

        @Override
        public int getScore() {
            return 100;
        }

        @Override
        public int getRareness() {
            return 100;
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
                            getScore(),
                            BaseActivity.getResourceManager().getSoundFile("NINJAB_HURT")))
                    .setPhysicsComponent(new PhysicsComp(getVelocity()))
                    .setAiComponent(new RepeatComp(new WanderComp(new MoveComp())))
                    .createSprite(this);
        }

        @Override
        public int getVelocity() {
            return 20;
        }

        @Override
        public int getScore() {
            return 5;
        }

        @Override
        public int getRareness() {
            return 50;
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
                            getScore(),
                            BaseActivity.getResourceManager().getSoundFile("GIRL_HURT")))
                    .setPhysicsComponent(new PhysicsComp(getVelocity()))
                    .setAiComponent(new RepeatComp(new SlideComp(new MoveComp())))
                    .createSprite(this);
        }

        @Override
        public int getVelocity() {
            return 5;
        }

        @Override
        public int getScore() {
            return -500;
        }

        @Override
        public int getRareness() {
            return 20;
        }
    };
}
