package com.claycot.tetris;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Tetromino extends Piece {
    Tetromino(Shape shape) {
        super(
            shape,
            getColor(shape),
            getWidth(shape),
            getHeight(shape),
            getSquares(shape)
        );
    }

    private static int getWidth(Shape shape) {
        if (shape == Shape.I) {
            return 5;
        } else {
            return 3;
        }
    }

    private static int getHeight(Shape shape) {
        if (shape == Shape.I) {
            return 5;
        } else {
            return 3;
        }
    }

    private static HexColor getColor(Shape shape) {
        switch (shape) {
            case I:
                return HexColor.CYAN;
            case J:
                return HexColor.BLUE;
            case L:
                return HexColor.ORANGE;
            case O:
                return HexColor.YELLOW;
            case S:
                return HexColor.GREEN;
            case T:
                return HexColor.PURPLE;
            case Z:
                return HexColor.RED;
            default:
                return HexColor.GRAY;
        }
    }

    private static List<Point> getSquares(Shape shape) {
        switch (shape) {
            case I:
                return new LinkedList<Point>(
                        Arrays.asList(
                                new Point(-1, 0),
                                new Point(0, 0),
                                new Point(1, 0),
                                new Point(2, 0),
                                new Point(3, 0)));
            case J:
                return new LinkedList<Point>(
                        Arrays.asList(
                                new Point(-1, 1),
                                new Point(-1, 0),
                                new Point(0, 0),
                                new Point(1, 0)));
            case L:
                return new LinkedList<Point>(
                        Arrays.asList(
                                new Point(-1, 0),
                                new Point(0, 0),
                                new Point(1, 0),
                                new Point(1, 1)));
            case O:
                return new LinkedList<Point>(
                        Arrays.asList(
                                new Point(0, 0),
                                new Point(1, 0),
                                new Point(1, 1),
                                new Point(0, 1)));
            case S:
                return new LinkedList<Point>(
                        Arrays.asList(
                                new Point(-1, 0),
                                new Point(0, 0),
                                new Point(0, 1),
                                new Point(1, 1)));
            case T:
                return new LinkedList<Point>(
                        Arrays.asList(
                                new Point(-1, 0),
                                new Point(0, 0),
                                new Point(1, 0),
                                new Point(0, 1)));
            case Z:
                return new LinkedList<Point>(
                        Arrays.asList(
                                new Point(-1, 1),
                                new Point(0, 0),
                                new Point(1, 0),
                                new Point(0, 1)));
            default:
                return new LinkedList<Point>(
                        Arrays.asList(
                                new Point(0, 0)));
        }
    }
}