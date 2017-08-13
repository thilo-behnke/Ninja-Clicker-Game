package com.tbeh.ninjaclicker.ninjaclicker;

import android.graphics.Bitmap;
import android.os.Looper;

import com.tbeh.ninjaclicker.activity.BaseActivity;
import com.tbeh.ninjaclicker.control.ResourceManager;
import com.tbeh.ninjaclicker.main.World;
import com.tbeh.ninjaclicker.model.sprite.CharacterEnum;
import com.tbeh.ninjaclicker.reader.ReadTypes;
import com.tbeh.ninjaclicker.reader.command.Command;
import com.tbeh.ninjaclicker.reader.expression.CharacterExpression;
import com.tbeh.ninjaclicker.reader.expression.GameListExpression;
import com.tbeh.ninjaclicker.reader.expression.GameObjectExpression;
import com.tbeh.ninjaclicker.reader.expression.NumberExpression;
import com.tbeh.ninjaclicker.reader.expression.TimerExpression;
import com.tbeh.ninjaclicker.spawn.RandomSpawnManager;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({BaseActivity.class, ResourceManager.class, Looper.class})
public class ReaderTest {

    private final static ScheduledExecutorService mainThread = Executors.newSingleThreadScheduledExecutor();

    ResourceManager resourceManager;
    World world;

    @Before
    public void prepare() throws Exception {
        resourceManager = mock(ResourceManager.class);
        when(resourceManager.getSpriteFile(CharacterEnum.NINJA_RED)).thenReturn(mock(Bitmap.class));

        mockStatic(BaseActivity.class);
        when(BaseActivity.getResourceManager()).thenReturn(resourceManager);

//        Looper mockMainThreadLooper = mock(Looper.class);
//        when(Looper.getMainLooper()).thenReturn(mock(Looper.class));
//        Handler mockMainThreadHandler = mock(Handler.class);
//        Answer<Boolean> handlerPostAnswer = new Answer<Boolean>() {
//            @Override
//            public Boolean answer(InvocationOnMock invocation) throws Throwable {
//                Runnable runnable = invocation.getArgumentAt(0, Runnable.class);
//                Long delay = 0L;
//                if (invocation.getArguments().length > 1) {
//                    delay = invocation.getArgumentAt(1, Long.class);
//                }
//                if (runnable != null) {
//                    mainThread.schedule(runnable, delay, TimeUnit.MILLISECONDS);
//                }
//                return true;
//            }
//        };
//        doAnswer(handlerPostAnswer).when(mockMainThreadHandler).post(any(Runnable.class));
//        doAnswer(handlerPostAnswer).when(mockMainThreadHandler).postDelayed(any(Runnable.class), anyLong());
//        PowerMockito.whenNew(Handler.class).withArguments(mockMainThreadLooper).thenReturn(mockMainThreadHandler);

        world = new World();
        world.setSpriteList(Collections.synchronizedList(new ArrayList<>()));
        world.setSpawnManager(new RandomSpawnManager());
    }

    @Test
    public void SimpleSpriteListTest() {

        CharacterExpression characterExpression = new CharacterExpression(CharacterEnum.NINJA_RED.ordinal());
        NumberExpression numberExpression = new NumberExpression(ReadTypes.Count.FIVE.getIndex());
        GameListExpression gameListExpression = new GameListExpression(characterExpression, numberExpression);
        Command command = gameListExpression.evaluate(ReadTypes.Operation.ADD);

        command.execute(world);

        Assert.assertTrue(World.getSpriteList().size() == 5);
        Assert.assertTrue(World.getSpriteList().stream().allMatch(x -> x.getType().equals(CharacterEnum.NINJA_RED)));
    }

    @Test
    public void AdvancedSpriteListTest() {

        CharacterExpression characterExpression = new CharacterExpression(CharacterEnum.NINJA_BLUE.ordinal());
        NumberExpression numberExpression = new NumberExpression(ReadTypes.Count.FOUR.getIndex());
        GameListExpression gameListExpression = new GameListExpression(characterExpression, numberExpression);
        Command command = gameListExpression.evaluate(ReadTypes.Operation.ADD);

        command.execute(world);

        Assert.assertTrue(World.getSpriteList().size() == 4);
        Assert.assertTrue(World.getSpriteList().stream().filter(x -> x.getType().equals(CharacterEnum.NINJA_BLUE)).count() == ReadTypes.Count.FOUR.getIndex());

        characterExpression = new CharacterExpression(CharacterEnum.NINJA_RED.ordinal());
        numberExpression = new NumberExpression(ReadTypes.Count.NINE.getIndex());
        gameListExpression = new GameListExpression(characterExpression, numberExpression);
        command = gameListExpression.evaluate(ReadTypes.Operation.ADD);

        command.execute(world);

        Assert.assertTrue(World.getSpriteList().size() == 13);
        Assert.assertTrue(World.getSpriteList().stream().filter(x -> x.getType().equals(CharacterEnum.NINJA_BLUE)).count() == ReadTypes.Count.FOUR.getIndex());
        Assert.assertTrue(World.getSpriteList().stream().filter(x -> x.getType().equals(CharacterEnum.NINJA_RED)).count() == ReadTypes.Count.NINE.getIndex());

        characterExpression = new CharacterExpression(CharacterEnum.GIRL.ordinal());
        numberExpression = new NumberExpression(ReadTypes.Count.ONE.getIndex());
        gameListExpression = new GameListExpression(characterExpression, numberExpression);
        command = gameListExpression.evaluate(ReadTypes.Operation.ADD);

        command.execute(world);

        Assert.assertTrue(World.getSpriteList().size() == 14);
        Assert.assertTrue(World.getSpriteList().stream().filter(x -> x.getType().equals(CharacterEnum.NINJA_BLUE)).count() == ReadTypes.Count.FOUR.getIndex());
        Assert.assertTrue(World.getSpriteList().stream().filter(x -> x.getType().equals(CharacterEnum.NINJA_RED)).count() == ReadTypes.Count.NINE.getIndex());
        Assert.assertTrue(World.getSpriteList().stream().filter(x -> x.getType().equals(CharacterEnum.GIRL)).count() == ReadTypes.Count.ONE.getIndex());

        characterExpression = new CharacterExpression(CharacterEnum.NINJA_RED.ordinal());
        numberExpression = new NumberExpression(ReadTypes.Count.TWO.getIndex());
        gameListExpression = new GameListExpression(characterExpression, numberExpression);
        command = gameListExpression.evaluate(ReadTypes.Operation.REMOVE);

        command.execute(world);

        Assert.assertTrue(World.getSpriteList().size() == 12);
        Assert.assertTrue(World.getSpriteList().stream().filter(x -> x.getType().equals(CharacterEnum.NINJA_RED)).count() == ReadTypes.Count.SEVEN.getIndex());
        Assert.assertTrue(World.getSpriteList().stream().filter(x -> x.getType().equals(CharacterEnum.NINJA_BLUE)).count() == ReadTypes.Count.FOUR.getIndex());
        Assert.assertTrue(World.getSpriteList().stream().filter(x -> x.getType().equals(CharacterEnum.GIRL)).count() == ReadTypes.Count.ONE.getIndex());

        gameListExpression = new GameListExpression(null, null);
        command = gameListExpression.evaluate(ReadTypes.Operation.CLEAR);

        command.execute(world);

        Assert.assertTrue(World.getSpriteList().size() == 0);
        Assert.assertTrue(World.getSpriteList().stream().filter(x -> x.getType().equals(CharacterEnum.NINJA_RED)).count() == 0);
        Assert.assertTrue(World.getSpriteList().stream().filter(x -> x.getType().equals(CharacterEnum.NINJA_BLUE)).count() == 0);
        Assert.assertTrue(World.getSpriteList().stream().filter(x -> x.getType().equals(CharacterEnum.GIRL)).count() == 0);

    }

    // TODO: Find a way to test gameTimer...
    //    @Test
    public void TimerCommandTest() {

        GameObjectExpression gameObjectExpression = new GameObjectExpression(ReadTypes.GameObject.GAME_TIMER.ordinal());
        NumberExpression numberExpression = new NumberExpression(ReadTypes.Count.NINE.getIndex());
        TimerExpression timerExpression = new TimerExpression(gameObjectExpression, numberExpression);

        Command command = timerExpression.evaluate(ReadTypes.Operation.SET);
        command.execute(world);

        Assert.assertTrue(World.getGameTimer().getFormattedCurrentTime().equals("10"));
    }
}
