package com.tbeh.ninjaclicker.model.sprite;

import com.tbeh.ninjaclicker.activity.BaseActivity;
import com.tbeh.ninjaclicker.model.sprite.components.general.GeneralComp;
import com.tbeh.ninjaclicker.model.sprite.components.graphics.SharedComp;
import com.tbeh.ninjaclicker.model.sprite.components.SpriteBuilder;
import com.tbeh.ninjaclicker.model.sprite.components.physics.PhysicsComp;

public enum PowerUpEnum implements IPowerUp {

    SWORD {
        @Override
        public Sprite makePrototype() {
            SpriteBuilder builder = new SpriteBuilder();
            return builder
                    .setGeneralComponent(new GeneralComp())
                    .setPhysicsComponent(new PhysicsComp())
                    .SetSharedComp(new SharedComp(BaseActivity.getResourceManager().getSpriteFile(this), SCORE_SWORD, null))
                    .createSprite(this);
        }
    },

    HAMMER {
        @Override
        public Sprite makePrototype() {
            SpriteBuilder builder = new SpriteBuilder();
            return builder
                    .setGeneralComponent(new GeneralComp())
                    .setPhysicsComponent(new PhysicsComp())
                    .SetSharedComp(new SharedComp(BaseActivity.getResourceManager().getSpriteFile(this), SCORE_HAMMER, null))
                    .createSprite(this);
        }
    },

    NONE {
        @Override
        public Sprite makePrototype() {
            return null;
        }
    };

    private final static int SCORE_SWORD = 500;
    private final static int SCORE_HAMMER = 1000;

}