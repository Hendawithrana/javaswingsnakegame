package testcase;

import lk.swlc.javaswingsnakegame.model.BoardSetting;
import org.junit.Test;

import static lk.swlc.javaswingsnakegame.model.BoardSetting.*;
import static org.junit.Assert.assertEquals;

public class BoardOptionTesting {

    BoardSetting boardSettingsOptions= new BoardSetting(DOT_SIZE,
            DOT_NUMBER_PER_DIMENSION,
            SNAKE_IMAGE_PATH,
            FRUIT_IMAGE_PATH);

    @Test
    public void testGetDotSize() {
        assertEquals(16,boardSettingsOptions.getDotSize());
    }

    @Test
    public void testGetWindowSizePerDimension() {
        assertEquals(boardSettingsOptions.getDotSize()*DOT_NUMBER_PER_DIMENSION,boardSettingsOptions.getWindowSizePerDimension());
    }

    @Test
    public void testGetAllDotsNumber() {
        assertEquals((int) Math.pow(DOT_NUMBER_PER_DIMENSION, 2),boardSettingsOptions.getAllDotsNo());
    }

    @Test
    public void testGetSnakeDotImageLocation() {
        assertEquals(SNAKE_IMAGE_PATH,boardSettingsOptions.getSnakeDotImageLocation());
    }
    @Test
    public void testGetAppleImageLocation() {
        assertEquals(FRUIT_IMAGE_PATH,boardSettingsOptions.getFruitImageLocation());
    }
}
