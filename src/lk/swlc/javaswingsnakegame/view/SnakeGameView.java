package lk.swlc.javaswingsnakegame.view;

import lk.swlc.javaswingsnakegame.model.BoardSetting;
import lk.swlc.javaswingsnakegame.panel.SnakeBoardPanel;

import javax.swing.*;
import java.awt.*;

public class SnakeGameView extends JFrame {

    public SnakeGameView(final BoardSetting boardSetting) {
        super("---Snake Game----");

        getContentPane().add(new SnakeBoardPanel(boardSetting));
        pack();

        setWindow();
    }

    /**
     * Open Windows Settings
     */
    private void setWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationInCenter();
        setVisible(true);
    }

    /**
     * Manage Windows Center on Screen
     */
    private void setLocationInCenter() {
        setLocationRelativeTo(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(
                screenSize.width / 2 - this.getSize().width / 2,
                screenSize.height / 2 - this.getSize().height / 2);
    }
}
