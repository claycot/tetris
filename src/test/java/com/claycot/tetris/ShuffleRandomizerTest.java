package com.claycot.tetris;

import com.claycot.tetris.Randomizer.ShuffleRandomizer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class ShuffleRandomizerTest {
    private static final ShuffleRandomizer instance = ShuffleRandomizer.getInstance();
    private static final int len = instance.getSequenceLength();

    @Test
    public void noDuplicatesInSequence() {
        for (int t = 0; t < 1000; t++) {
            instance.reset();
            List<Shape> sequence = new ArrayList<>(len);
            for (int i = 0; i < len; i++) {
                Shape next = instance.getRandom();
                assertFalse(sequence.contains(next), "Shuffle Randomizer contained duplicates in sequence");
                sequence.add(next);
            }
        }
    }

    @Test
    public void yesDuplicatesAfterSequence() {
        for (int t = 0; t < 1000; t++) {
            instance.reset();
            List<Shape> sequence = new ArrayList<>(len);
            for (int i = 0; i < len; i++) {
                System.out.println(i);
                Shape next = instance.getRandom();
                assertFalse(sequence.contains(next), "Shuffle Randomizer contained duplicates in sequence");
                sequence.add(next);
            }
            Shape firstDuplicate = instance.getRandom();
            assertTrue(sequence.contains(firstDuplicate), "Shuffle Randomizer did not reset after sequence");
        }
    }
}
