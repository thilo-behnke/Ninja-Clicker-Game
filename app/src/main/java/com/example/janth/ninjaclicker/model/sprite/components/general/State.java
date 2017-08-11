package com.example.janth.ninjaclicker.model.sprite.components.general;

import com.example.janth.ninjaclicker.model.sprite.components.ComponentsHolder;

public interface State {

    void enter(ComponentsHolder componentsHolder);
    void exit(ComponentsHolder componentsHolder);

}
