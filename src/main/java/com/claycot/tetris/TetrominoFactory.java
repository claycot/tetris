package com.claycot.tetris;

import com.claycot.tetris.Randomizer.Randomizer;
import com.claycot.tetris.Randomizer.ShuffleRandomizer;

public class TetrominoFactory {
    private static Randomizer randomizer = ShuffleRandomizer.getInstance();

    public static void setRandomizer(Randomizer newRandomizer) {
        randomizer = newRandomizer;
    }

    public static Shape getRandom() {
        return randomizer.getRandom();
    }

    public static Tetromino createRandom() {
        return create(getRandom());
    }

    public static Tetromino create(Shape shape) {
        return new Tetromino(shape, shape.getColor(), shape.getWidth(), shape.getHeight(), shape.getSquares());
    }
}