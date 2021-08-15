package testcase;

import lk.swlc.javaswingsnakegame.model.Snake;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import static lk.swlc.javaswingsnakegame.model.BoardSetting.DOT_NUMBER_PER_DIMENSION;
import static org.junit.Assert.assertEquals;

public class SnakeRunTest {

    Snake snake = new Snake(new ImageIcon("iamges.png").getImage(),16,
            (int)Math.pow(DOT_NUMBER_PER_DIMENSION,2),600/2);

    @Test
    public void testGetSize() {
        assertEquals(3,snake.getSize());
    }

    @Test
    public void testGetSnakeDotImage() {
        assertEquals(new ImageIcon("images.png").getImage(),snake.getSnakeDotImage());
    }
    @Test
    public void testIsMovingLeft() {
        assertEquals(true,snake.isSnakeMovingLeft());
    }
    @Test
    public void testIsMovingUp() {
        assertEquals(true,snake.isSnakeMovingUp());
    }
    @Test
    public void testIsMovingRight() {
        assertEquals(true,snake.isSnakeMovingRight());
    }
    @Test
    public void testIsMovingDown() {
        assertEquals(true,snake.isSnakeMovingDown());
    }

}
