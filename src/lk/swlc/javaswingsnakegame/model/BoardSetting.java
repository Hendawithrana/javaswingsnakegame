package lk.swlc.javaswingsnakegame.model;

public class BoardSetting {

    // board dot size and dimension of dot no
    public static final int DOT_SIZE = 16;
    public static final int DOT_NUMBER_PER_DIMENSION = 40;

    // images path
    public static final String FRUIT_IMAGE_PATH = "images.png";
    public static final String SNAKE_IMAGE_PATH = "snake.png";

    private final int dotSize;
    private final int windowSizePerDimension;
    private final int allDotsNo;

    private final String snakeDotImageLocation;
    private final String fruitImageLocation;

    public BoardSetting(int dotSize, int dotNumberPerDimension, String snakeDotImageLocation, String fruitImageLocation) {
        this.dotSize = dotSize;

        windowSizePerDimension = this.dotSize * dotNumberPerDimension;
        allDotsNo = (int) Math.pow(dotNumberPerDimension, 2);

        this.snakeDotImageLocation = snakeDotImageLocation;
        this.fruitImageLocation = fruitImageLocation;
    }

    public int getDotSize() {
        return dotSize;
    }

    public int getWindowSizePerDimension() {
        return windowSizePerDimension;
    }

    public int getAllDotsNo() {
        return allDotsNo;
    }

    public String getSnakeDotImageLocation() {
        return snakeDotImageLocation;
    }

    public String getFruitImageLocation() {
        return fruitImageLocation;
    }
}
