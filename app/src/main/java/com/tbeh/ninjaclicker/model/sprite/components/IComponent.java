package com.tbeh.ninjaclicker.model.sprite.components;

import com.tbeh.ninjaclicker.model.sprite.Sprite;

public interface IComponent {

    void initialize(Sprite sprite);
    void update(Sprite sprite);
    String toString();

}
