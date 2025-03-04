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
    private Timer drawTimer;
    private Timer gravityTimer;

    public Game() {
        this.frame = new JFrame();
        this.frame.addKeyListener(new KeyboardInput(this));

        this.panel = new JPanel();
        this.panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        this.panel.setLayout(new GridLayout(height, width));

        this.boardDisplay = new JButton[height][width];
        this.boardOccupied = new boolean[height][width];

        for (int r = height - 1; r >= 0; r--) {
            for (int c = 0; c < width; c++) {
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
        gravityTimer = new Timer(200, event -> applyGravityToTetromino());
        gravityTimer.setRepeats(true);
        gravityTimer.start();

        drawTimer = new Timer(15, event -> drawBoard());
        drawTimer.setRepeats(true);
        drawTimer.start();
    }

    private void applyGravityToTetromino() {
        boolean moved = moveTetrominoCenter(Movement.DROP);

        // if impossible, place and get the next tetromino!
        if (!moved) {
            // write the position
            List<Point> squares = activeTetromino.getSquares();
            for (Point s : squares) {
                int globalX = activeTetrominoCenter.getX() + s.getX();
                int globalY = activeTetrominoCenter.getY() + s.getY();

                if (globalX >= 0 && globalX < width && globalY >= 0 && globalY < height) {
                    this.boardOccupied[globalY][globalX] = true;
                }
            }

            // move to the next tetromino, and generate a new next
            activeTetromino = nextTetromino;
            activeTetrominoCenter = spawnPoint.copy();
            nextTetromino = TetrominoFactory.createRandom();
        }
    }

    private void drawBoard() {
        List<Point> squares = activeTetromino.getSquares();

        // wipe any non-solidified squares
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                if (!this.boardOccupied[r][c]) {
                    this.boardDisplay[r][c].setBackground(HexColor.GRAY.getColor());
                }
            }
        }

        // draw new location
        for (Point s : squares) {
            int globalX = activeTetrominoCenter.getX() + s.getX();
            int globalY = activeTetrominoCenter.getY() + s.getY();

            if (globalX >= 0 && globalX < width && globalY >= 0 && globalY < height) {
                this.boardDisplay[globalY][globalX].setBackground(activeTetromino.getHexColor().getColor());
            }
        }

        this.panel.revalidate();
        this.panel.repaint();
    }

    public Tetromino getTetromino() {
        return this.activeTetromino;
    }

    public Point getTetrominoCenter() {
        return this.activeTetrominoCenter;
    }

    public boolean moveTetrominoCenter(Movement movement) {
        // get the squares that make up the tetromino
        List<Point> squares = activeTetromino.getSquares();

        // get the next location
        Point dir = movement.getPoint();
        Point nextCenter = activeTetrominoCenter.copy().translateAdd(dir);

        // check if the tetromino can move in that direction
        boolean canMove = true;
        for (Point s : squares) {
            int nextX = nextCenter.getX() + s.getX();
            int nextY = nextCenter.getY() + s.getY();

            // if the square is outside of the board or hitting another tetromino, abandon the move
            if (nextX < 0 || nextX >= width || nextY < 0 || this.boardOccupied[nextY][nextX]) {
                canMove = false;
                break;
            }
        }

        // if possible, update the location to continue
        if (canMove) {
            activeTetrominoCenter = nextCenter;
        }

        return canMove;
    }
}
