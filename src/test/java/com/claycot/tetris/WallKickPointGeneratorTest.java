package com.claycot.tetris;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class WallKickPointGeneratorTest {
    @Test
    public void basicKick1() {
        WallKickPointGenerator generator = new WallKickPointGenerator(Shape.J, true, Orientation.TWELVE_OCLOCK);

        Point[] offsetResult = generator.getKicks();
        Point[] offsetExpect = new Point[] {
                new Point(0, 0),
                new Point(-1, 0),
                new Point(-1, 1),
                new Point(0, -2),
                new Point(-1, -2)
        };

        assertArrayEquals(
                Arrays.stream(offsetExpect).map(Point::toString).toArray(String[]::new),
                Arrays.stream(offsetResult).map(Point::toString).toArray(String[]::new));
    }

    @Test
    public void oKick1() {
        WallKickPointGenerator generator = new WallKickPointGenerator(Shape.O, true, Orientation.TWELVE_OCLOCK);

        Point[] offsetResult = generator.getKicks();
        Point[] offsetExpect = new Point[] {
                new Point(0, 1)
        };

        assertArrayEquals(
                Arrays.stream(offsetExpect).map(Point::toString).toArray(String[]::new),
                Arrays.stream(offsetResult).map(Point::toString).toArray(String[]::new));
    }

    @Test
    public void iKick1() {
        WallKickPointGenerator generator = new WallKickPointGenerator(Shape.I, true, Orientation.TWELVE_OCLOCK);

        Point[] offsetResult = generator.getKicks();
        Point[] offsetExpect = new Point[] {
                new Point(1, 0),
                new Point(-1, 0),
                new Point(2, 0),
                new Point(-1, -1),
                new Point(2, 2),
        };

        assertArrayEquals(
                Arrays.stream(offsetExpect).map(Point::toString).toArray(String[]::new),
                Arrays.stream(offsetResult).map(Point::toString).toArray(String[]::new));
    }

}
