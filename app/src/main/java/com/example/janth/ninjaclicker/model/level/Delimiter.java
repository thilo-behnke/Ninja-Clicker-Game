package com.example.janth.ninjaclicker.model.level;

public enum Delimiter {

    LEVEL_SECTION("#");

    private String delimiter;

    Delimiter(String delimiter){
        this.delimiter = delimiter;
    }

    private String getDelimiter(){
        return this.delimiter;
    }

    @Override
    public String toString(){
        return getDelimiter();
    }
}
