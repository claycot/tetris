package com.claycot.tetris.Randomizer;

import com.claycot.tetris.Shape;
import java.util.Random;

public class SimpleRandomizer implements Randomizer {
    private static final Shape[] shapes = Shape.values();
    private static final Random random = new Random();

    private static final SimpleRandomizer instance = new SimpleRandomizer();

    private SimpleRandomizer() {
    }

    public static SimpleRandomizer getInstance() {
        return instance;
    }

    @Override
    public Shape getRandom() {
        return shapes[random.nextInt(shapes.length)];
    }
}
