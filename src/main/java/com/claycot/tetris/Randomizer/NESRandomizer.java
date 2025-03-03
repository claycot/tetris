package com.claycot.tetris.Randomizer;

import java.util.Random;

import com.claycot.tetris.Shape;

// NES Randomizer discounts the ability to get a repeat by rerolling on repeat
public class NESRandomizer implements Randomizer {
    private static final Shape[] values = Shape.values();
    private static final Random random = new Random();

    private static final NESRandomizer instance = new NESRandomizer();

    private static int lastRandom = -1;

    private NESRandomizer() {
    }

    public static NESRandomizer getInstance() {
        return instance;
    }

    // roll an n+1-sided die
    // if roll lands on n+1 or lastRandom, roll an n-sided die
    @Override
    public Shape getRandom() {
        int rand = random.nextInt(values.length + 1);

        // reroll on repeat or n+1 value
        if (rand == values.length || rand == lastRandom) {
            rand = random.nextInt(values.length);
        }

        // update last random and return
        lastRandom = rand;
        return values[rand];
    }
}
