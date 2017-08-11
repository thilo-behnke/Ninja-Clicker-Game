package com.example.janth.ninjaclicker.model.sprite.components;

import com.example.janth.ninjaclicker.model.sprite.Sprite;

public interface IComponent {

    void initialize(Sprite sprite);
    void update(Sprite sprite);
    String toString();

}
