package com.tbeh.ninjaclicker;

import com.tbeh.ninjaclicker.model.level.LevelParameter;
import com.tbeh.ninjaclicker.model.level.LevelSetting;

import java.util.HashMap;
import java.util.Iterator;

public class LevelLoader {

    private FileParser fileParser;

    private HashMap<LevelParameter.Type, LevelSetting> spriteMap;
    private HashMap<LevelParameter.Type, LevelSetting> settingMap;

    public LevelLoader(FileParser fileParser) {
        this.fileParser = fileParser;
    }

    public void loadLevelData() {
        HashMap<LevelParameter.Type, LevelSetting>
                levelSectionData = fileParser.readLevelSection();
        spriteMap = new HashMap<>();
        settingMap = new HashMap<>();
        for (Iterator<HashMap.Entry<LevelParameter.Type, LevelSetting>> iterator
             = levelSectionData.entrySet().iterator(); iterator.hasNext(); ) {
            HashMap.Entry<LevelParameter.Type, LevelSetting> entry = iterator.next();
            switch (entry.getValue().getLevelParameter().getType().getGroup()) {
                case SPRITES:
                    spriteMap.put(entry.getKey(), entry.getValue());
                    iterator.remove();
                    break;
                case SETTINGS:
                    settingMap.put(entry.getKey(), entry.getValue());
                    iterator.remove();
                    break;
            }
        }
    }

    public HashMap<LevelParameter.Type, LevelSetting> getSpriteMap() {
        return this.spriteMap;
    }

    public HashMap<LevelParameter.Type, LevelSetting> getSettingMap() {
        return this.settingMap;
    }
}
