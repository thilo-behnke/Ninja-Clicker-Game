package com.tbeh.ninjaclicker.ninjaclicker;

import android.graphics.Bitmap;
import android.test.mock.MockContext;

import com.tbeh.ninjaclicker.activity.BaseActivity;
import com.tbeh.ninjaclicker.control.ResourceManager;
import com.tbeh.ninjaclicker.main.World;
import com.tbeh.ninjaclicker.model.sprite.CharacterEnum;
import com.tbeh.ninjaclicker.reader.ReadTypes;
import com.tbeh.ninjaclicker.reader.command.Command;
import com.tbeh.ninjaclicker.reader.expression.CharacterExpression;
import com.tbeh.ninjaclicker.reader.expression.GameListExpression;
import com.tbeh.ninjaclicker.reader.expression.NumberExpression;
import com.tbeh.ninjaclicker.spawn.RandomSpawnManager;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Collections;

import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({BaseActivity.class, ResourceManager.class})
public class ReaderTest {

    ResourceManager resourceManager;
    World world;

    @Before
    public void prepare() {
        resourceManager = PowerMockito.mock(ResourceManager.class);
        when(resourceManager.getSpriteFile(CharacterEnum.NINJA_RED)).thenReturn(PowerMockito.mock(Bitmap.class));

        mockStatic(BaseActivity.class);
        when(BaseActivity.getResourceManager()).thenReturn(resourceManager);

        world = new World();
        world.setSpriteList(Collections.synchronizedList(new ArrayList<>()));
        world.setSpawnManager(new RandomSpawnManager());
    }

    @Test
    public void test() {

        CharacterExpression characterExpression = new CharacterExpression(1);
        NumberExpression numberExpression = new NumberExpression(5);
        GameListExpression gameListExpression = new GameListExpression(characterExpression, numberExpression);
        Command command = gameListExpression.evaluate(ReadTypes.Operation.ADD);

        command.execute(world);

        System.out.println("Expected size: " + 5);
        System.out.println("Actual size: " + World.getSpriteList().size());
        Assert.assertTrue(World.getSpriteList().size() == 5);


    }
}
