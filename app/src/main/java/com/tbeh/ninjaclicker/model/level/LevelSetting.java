package com.tbeh.ninjaclicker.model.level;

public class LevelSetting {

    private LevelParameter levelParameter;
    private String additionalParameter1;
    private String additionalParameter2;

    public LevelSetting(LevelParameter levelParameter) {
        this.levelParameter = levelParameter;
    }

    public LevelSetting(LevelParameter levelParameter, String additionalParameter1) {
        this.levelParameter = levelParameter;
        this.additionalParameter1 = additionalParameter1;
    }

    public LevelSetting(LevelParameter levelParameter, String additionalParameter1, String additionalParameter2) {
        this.levelParameter = levelParameter;
        this.additionalParameter1 = additionalParameter1;
        this.additionalParameter2 = additionalParameter2;
    }

    public LevelParameter getLevelParameter() {
        return levelParameter;
    }

    public String getAdditionalParameter1() {
        return additionalParameter1;
    }

    public String getAdditionalParameter2() {
        return additionalParameter2;
    }
}
