package com.claycot.tetris;

public class Game {
    private Board board = new Board();
    private int state = 0;

    public Game() {
        Tetromino activeTetromino = TetrominoFactory.createRandom();
        Tetromino nextTetromino = TetrominoFactory.createRandom();
    }
}
