package lk.swlc.javaswingsnakegame.main;

import lk.swlc.javaswingsnakegame.model.BoardSetting;
import lk.swlc.javaswingsnakegame.view.SnakeGameView;

import static lk.swlc.javaswingsnakegame.model.BoardSetting.*;

public class Main {

    public static void main(String[] args) {
        new SnakeGameView(new BoardSetting(
                DOT_SIZE,
                DOT_NUMBER_PER_DIMENSION,
                SNAKE_IMAGE_PATH,
                FRUIT_IMAGE_PATH
        ));
    }

}
