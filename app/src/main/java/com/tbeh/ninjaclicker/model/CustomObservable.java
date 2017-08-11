package com.tbeh.ninjaclicker.model;

public interface CustomObservable {

    void registerObserver(CustomObserver repositoryObserver);
    void removeObserver(CustomObserver repositoryObserver);
    void notifyObservers(Enum enumMessage);

}
