package com.claycot.tetris.Randomizer;

import com.claycot.tetris.Shape;
import java.util.Random;

public class ShuffleRandomizer implements Randomizer {
    private static final Shape[] shapes = Shape.values();
    private static final Random random = new Random();
    private static final int[] sequence = new int[shapes.length];

    private static final ShuffleRandomizer instance = new ShuffleRandomizer();

    private static int bagPtr = 0;

    private ShuffleRandomizer() {
        reset();
    }

    public static ShuffleRandomizer getInstance() {
        return instance;
    }

    @Override
    public Shape getRandom() {
        // if sequence is exhausted, shuffle and reset the pointer
        if (bagPtr >= shapes.length) {
            reset();
        }

        // return the shape at the index indicated by the sequence
        return shapes[sequence[bagPtr++]];
    }

    public int getSequenceLength() {
        return ShuffleRandomizer.sequence.length;
    }

    public void reset() {
        shuffleSequence();
        bagPtr = 0;
    }

    // shuffle the shapes by generating a new sequence
    private void shuffleSequence() {
        // reset the sequence
        for (int i = 0; i < sequence.length; i++) {
            sequence[i] = i;
        }

        // Fisher-Yates shuffle
        for (int i = sequence.length - 1; i > 0; i--) {
            // pick a random index at or before i and swap them
            int iSwap = random.nextInt(i + 1);

            // if they're not the same, swap them!
            if (iSwap != i) {
                int swap = sequence[i];
                sequence[i] = sequence[iSwap];
                sequence[iSwap] = swap;
            }
        }

        return;
    }
}
