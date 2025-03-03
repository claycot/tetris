package com.claycot.tetris;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game {
    private final JFrame frame;
    private final JPanel panel;
    private int state = 0;
    private Board board = new Board();

    public Game() {
        Tetromino activeTetromino = TetrominoFactory.createRandom();
        Tetromino nextTetromino = TetrominoFactory.createRandom();

        this.frame = new JFrame();
        this.panel = new JPanel();

        this.panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        this.panel.setLayout(new GridLayout(1, 1));

        this.frame.addKeyListener(new KeyboardInput());

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle(String.format("Tetris!"));
        frame.pack();
        frame.setVisible(true);
    }
}
