package com.claycot.tetris;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game {
    private final JFrame frame;
    private final JPanel panel;
    private int state = 0;
    // private Board board = new Board();
    private final int width = 10;
    private final int height = 20;
    private JButton[][] boardDisplay;
    private boolean[][] boardOccupied;

    private final Point spawnPoint = new Point(width / 2, height - 1);
    
    private Tetromino activeTetromino;
    private Point activeTetrominoCenter;
    private Tetromino nextTetromino;
    private Timer tick;

    public Game() {
        this.frame = new JFrame();
        this.frame.addKeyListener(new KeyboardInput());

        this.panel = new JPanel();
        this.panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        this.panel.setLayout(new GridLayout(height, width));

        this.boardDisplay = new JButton[height][width];
        this.boardOccupied = new boolean[height][width];

        for (int r = height - 1; r >= 0; r--) {
            for (int c = width - 1; c >= 0; c--) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(40, 40));
                button.setBackground(HexColor.GRAY.getColor());
                button.setEnabled(false);
                this.panel.add(button);
                this.boardDisplay[r][c] = button;
            }
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle(String.format("Tetris!"));
        frame.pack();
        frame.setVisible(true);

        gameLoop();
    }

    private void gameLoop() {
        activeTetromino = TetrominoFactory.createRandom();
        activeTetrominoCenter = spawnPoint.copy();
        nextTetromino = TetrominoFactory.createRandom();
        tick = new Timer(200, event -> movePiece());
        tick.setRepeats(true);
        tick.start();
    }

    private void movePiece() {
        List<Point> squares = activeTetromino.getSquares();
        Point nextCenter = activeTetrominoCenter.copy().translateSubtract(new Point(0, 1));

        // check if the piece can move down
        boolean validPos = true;
        for (Point s : squares) {
            int globalX = nextCenter.getX() + s.getX();
            int globalY = nextCenter.getY() + s.getY();

            // if it's outside of the board or hitting another piece, stop
            if (globalX < 0 || globalX >= width || globalY < 0 || this.boardOccupied[globalY][globalX]) {
                validPos = false;
                break;
            }
        }

        // if impossible, place and get the next piece!
        if (!validPos) {
            for (Point s : squares) {
                int globalX = activeTetrominoCenter.getX() + s.getX();
                int globalY = activeTetrominoCenter.getY() + s.getY();
    
                // if it's outside of the board or hitting another piece, solidify the position
                if (globalX >= 0 && globalX < width && globalY >= 0 && globalY < height) {
                    this.boardOccupied[globalY][globalX] = true;
                }
            }
            activeTetromino = nextTetromino;
            activeTetrominoCenter = spawnPoint.copy();
            nextTetromino = TetrominoFactory.createRandom();
        }
        // otherwise, display the new location and continue
        else {
            for (Point s : squares) {
                int lastX = activeTetrominoCenter.getX() + s.getX();
                int lastY = activeTetrominoCenter.getY() + s.getY();

                if (lastX >= 0 && lastX < width && lastY >= 0 && lastY < height) {
                    this.boardDisplay[lastY][lastX].setBackground(HexColor.GRAY.getColor());
                }
            }
             
            for (Point s : squares) {
                int nextX = nextCenter.getX() + s.getX();
                int nextY = nextCenter.getY() + s.getY();

                if (nextX >= 0 && nextX < width && nextY >= 0 && nextY < height) {
                    this.boardDisplay[nextY][nextX].setBackground(activeTetromino.getHexColor().getColor());
                }
            }
            activeTetrominoCenter = nextCenter;
        }

        this.panel.revalidate();
        this.panel.repaint();
    }
}
