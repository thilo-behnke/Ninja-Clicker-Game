package com.example.janth.ninjaclicker;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.example.janth.ninjaclicker.model.level.Delimiter;
import com.example.janth.ninjaclicker.model.level.LevelParameter;
import com.example.janth.ninjaclicker.model.level.LevelSetting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class FileParser {

    private String docName;
    private AssetManager assetManager;
    BufferedReader bufferedReader;

    public FileParser(Context context, int levelNumber) {
        assetManager = context.getAssets();
        setDocPath(levelNumber);
    }

    private void setDocPath(int levelNumber) {
        this.docName = "level" + levelNumber + ".txt";
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(docName)));
        } catch (IOException e) {
            Log.d(e.toString(), "File could not be read.");
        }
    }

    public HashMap<LevelParameter.Type, LevelSetting> readLevelSection() {
        HashMap<LevelParameter.Type, LevelSetting> levelSectionMap = new HashMap<>();
        try {
            String readLine = bufferedReader.readLine();
            if (readLine.equals(Delimiter.LEVEL_SECTION.toString())) {
                readLine = bufferedReader.readLine();
                while (!readLine.equals(Delimiter.LEVEL_SECTION.toString())) {
                    String[] readLineSeparated = readLine.split("[|]+");
                    for (LevelParameter levelParameter : LevelParameter.values()) {
                        if (levelParameter.getName().equals(readLineSeparated[1])) {
                            levelSectionMap.put(levelParameter.getType(),
                                    new LevelSetting(levelParameter, readLineSeparated[2], readLineSeparated[3]));
                        }
                    }
                    readLine = bufferedReader.readLine();
                }
            }
        } catch (IOException e) {
            Log.d(e.toString(), "File could not be read.");
        }
        return levelSectionMap;
    }
}
