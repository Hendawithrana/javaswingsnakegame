package lk.swlc.javaswingsnakegame.panel;

import lk.swlc.javaswingsnakegame.model.BoardSetting;
import lk.swlc.javaswingsnakegame.model.Directions;
import lk.swlc.javaswingsnakegame.model.DogObjectModel;
import lk.swlc.javaswingsnakegame.model.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class SnakeBoardPanel extends JPanel implements ActionListener,Runnable {

    private Thread thread;
    private final int dotSize;
    static final int SCREEN_WIDTH = 600;
    static final int WIDTH = 300;
    private final DogObjectModel dogObjectModel;
    private final Random random;

    private Snake snake;

    private boolean isPlaying;
    private Timer timer;
    private int fruitCount = 0;

    public SnakeBoardPanel(final BoardSetting boardSetting){
        dotSize = boardSetting.getDotSize();
        dogObjectModel = new DogObjectModel(new ImageIcon(boardSetting.getFruitImageLocation()).getImage());
        random = new Random();
        snake = new Snake(new ImageIcon(boardSetting.getSnakeDotImageLocation()).getImage(),
                dotSize, boardSetting.getAllDotsNo(), getWidth() / 2);
        isPlaying = true;
        timer = new Timer(100, this);

        addKeyListener(new FieldKeyListener());


        setSize(new Dimension(boardSetting.getWindowSizePerDimension(), boardSetting.getWindowSizePerDimension()));
        setPreferredSize(getSize());
        setBackground(Color.BLACK);
        setFocusable(true);
        setVisible(true);

        startGame();
    }

    public void start() {
        try {
            thread = new Thread(this);
            thread.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * start to play
     */

    private void startGame() {
        isPlaying = true;
        timer.start();

        snake = new Snake(
                snake.getSnakeDotImage(),
                dotSize,
                snake.getMaxDotsNumber(),
                getWidth() / 2);
        start();

    }

    //    Random put the Dog Image in Board
    private void randomDOGCoords() {
        dogObjectModel.setX(random.nextInt(getWidth() / dotSize) * dotSize);
        dogObjectModel.setY(random.nextInt(getHeight() / dotSize) * dotSize);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (isPlaying
                && !isBadCollision()) {

            if (isAppleCollision()) {
                thread.stop();
                snake.incSize();
                fruitCount++;
                start();
            }

            snake.move();
        }
        repaint();
    }

    private boolean isBadCollision() {
        return isSnakeCollision() || isBorderCollision();
    }

    private boolean isSnakeCollision() {
        for (int i = snake.getSize(); i > 0; i--) {
            if (i > 4
                    && snake.getX(0) == snake.getX(i)
                    && snake.getY(0) == snake.getY(i)) {

                isPlaying = false;
                return true;
            }
        }

        return false;
    }

    private boolean isBorderCollision() {
        if (snake.getX(0) >= getWidth()
                || snake.getX(0) < 0
                || snake.getY(0) >= getHeight()
                || snake.getY(0) < 0) {

            isPlaying = false;
            return true;
        }

        return false;
    }

    private boolean isAppleCollision() {
        return snake.getX(0) == dogObjectModel.getX()
                && snake.getY(0) == dogObjectModel.getY();
    }

    @Override
    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);

        if (isPlaying) {
            drawSnake(gr);
            drawApple(gr);
        } else {
            timer.stop();
            drawGameOver(gr);
        }
    }

    private void drawSnake(Graphics gr) {
        for (int snakeDot = 0; snakeDot < snake.getSize(); snakeDot++) {
            gr.drawImage(snake.getSnakeDotImage(), snake.getX(snakeDot), snake.getY(snakeDot), this);
        }
    }

    //    Display Images
    private void drawApple(Graphics gr) {
        gr.drawImage(dogObjectModel.getImage(), dogObjectModel.getX(), dogObjectModel.getY(), this);
        gr.setColor(Color.YELLOW);
        gr.setFont(new Font("MONOSPACED", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(gr.getFont());
        gr.drawString("Score: " + fruitCount, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + fruitCount)) / 2, gr.getFont().getSize());
    }

    //    END Game Display
    private void drawGameOver(Graphics gr) {
        gr.setColor(Color.WHITE);
        gr.setFont(new Font("MONOSPACED", Font.BOLD, 18));
        gr.drawString("Please Try Again! Press Enter Try Again", WIDTH / 2, getHeight() / 2);

        gr.setColor(Color.GRAY);
        gr.setFont(new Font("MONOSPACED", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(gr.getFont());
        gr.drawString("Score: " + fruitCount, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + fruitCount)) / 2, gr.getFont().getSize());
        fruitCount = 0;
        thread.stop();

    }

    @Override
    public void run() {
        try {
            for(int i = 6; i > 0; i--) {
                randomDOGCoords();
                Thread.sleep(6000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //    Event Handle Using key up,down,left,right
    private class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent ev) {
            super.keyPressed(ev);
            int key = ev.getKeyCode();

            if (key == KeyEvent.VK_LEFT && !snake.isSnakeMovingRight()) {
                snake.setMovingDirection(Directions.LEFT);
            } else if (key == KeyEvent.VK_RIGHT && !snake.isSnakeMovingLeft()) {
                snake.setMovingDirection(Directions.RIGHT);
            } else if (key == KeyEvent.VK_UP && !snake.isSnakeMovingDown()) {
                snake.setMovingDirection(Directions.UP);
            } else if (key == KeyEvent.VK_DOWN && !snake.isSnakeMovingUp()) {
                snake.setMovingDirection(Directions.DOWN);
            }else if (key == KeyEvent.VK_ENTER && !isPlaying) {
                startGame();
            }
        }
    }
}
