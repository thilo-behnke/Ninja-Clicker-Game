package com.tbeh.ninjaclicker.ninjaclicker;

import com.tbeh.ninjaclicker.activity.BaseActivity;
import com.tbeh.ninjaclicker.main.GameEngine;
import com.tbeh.ninjaclicker.main.World;
import com.tbeh.ninjaclicker.reader.ReadTypes;
import com.tbeh.ninjaclicker.reader.command.Command;
import com.tbeh.ninjaclicker.reader.expression.CharacterExpression;
import com.tbeh.ninjaclicker.reader.expression.GameListExpression;
import com.tbeh.ninjaclicker.reader.expression.NumberExpression;
import com.tbeh.ninjaclicker.spawn.RandomSpawnManager;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

public class ReaderTest {

    BaseActivity activity;


    @Test
    public void TestSpriteCommand() {

        activity.runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {
                        World world = new World();
                        world.setSpriteList(Collections.synchronizedList(new ArrayList<>()));
                        world.setSpawnManager(new RandomSpawnManager());

                        CharacterExpression characterExpression = new CharacterExpression(1);
                        NumberExpression numberExpression = new NumberExpression(5);
                        GameListExpression gameListExpression = new GameListExpression(characterExpression, numberExpression);
                        Command command = gameListExpression.evaluate(ReadTypes.Operation.ADD);

                        command.execute(world);

                        Assert.assertTrue(World.getSpriteList().size() == 5);
                    }
                }
        );


    }
}
