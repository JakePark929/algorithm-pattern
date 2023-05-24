package com.jake.privatetest.gpttest.snakegame;

import javax.swing.*;

public class SnakeGameMain extends JFrame {

    public SnakeGameMain() {

        add(new SnakeGame());

        setResizable(false);
        pack();

        setTitle("Snake Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        JFrame frame = new SnakeGameMain();
        frame.setVisible(true);
    }
}
