import java.awt.*;
import javax.swing.*;

import javax.swing.*;
import java.awt.event.*;

public class NewWindow {

    JFrame gameWindow = new JFrame("Snake");
    JButton resetButton = new JButton("Reset");

    NewWindow(){

        gameWindow.setVisible(true);
        gameWindow.setSize(600, 600);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setResizable(false);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SnakeGame snakeGame = new SnakeGame(600, 600);

        gameWindow.add(snakeGame);
        gameWindow.pack();


    }

}