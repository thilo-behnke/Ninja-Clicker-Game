package com.tbeh.ninjaclicker.model.sprite;

public interface ICharacter {

    Sprite makePrototype();
    int getVelocity();
    int getScore();
    int getRareness();
}
