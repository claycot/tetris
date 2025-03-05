package com.claycot.tetris;

import java.util.List;
import java.util.stream.Collectors;

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
        // squares are modified when the tetromino is rotated
        // if using squares directly, O and I-minos wobble
        List<Point> squaresCopy = shape.getSquares().stream()
                .map(Point::copy)
                .collect(Collectors.toList());
        return new Tetromino(shape, shape.getColor(), shape.getWidth(), shape.getHeight(), squaresCopy);
    }
}