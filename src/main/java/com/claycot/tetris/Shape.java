package com.claycot.tetris;

import java.util.Arrays;
import java.util.List;

public enum Shape {
    I(HexColor.CYAN, 5, 5, Arrays.asList(
            new Point(-1, 0),
            new Point(0, 0),
            new Point(1, 0),
            new Point(2, 0))),
    J(HexColor.BLUE, 3, 3, Arrays.asList(
            new Point(-1, 1),
            new Point(-1, 0),
            new Point(0, 0),
            new Point(1, 0))),
    L(HexColor.ORANGE, 3, 3, Arrays.asList(
            new Point(-1, 0),
            new Point(0, 0),
            new Point(1, 0),
            new Point(1, 1))),
    O(HexColor.YELLOW, 3, 3, Arrays.asList(
            new Point(0, 0),
            new Point(1, 0),
            new Point(1, 1),
            new Point(0, 1))),
    S(HexColor.GREEN, 3, 3, Arrays.asList(
            new Point(-1, 0),
            new Point(0, 0),
            new Point(0, 1),
            new Point(1, 1))),
    T(HexColor.PURPLE, 3, 3, Arrays.asList(
            new Point(-1, 0),
            new Point(0, 0),
            new Point(1, 0),
            new Point(0, 1))),
    Z(HexColor.RED, 3, 3, Arrays.asList(
            new Point(-1, 1),
            new Point(0, 0),
            new Point(1, 0),
            new Point(0, 1)));

    private final HexColor color;
    private final int width;
    private final int height;
    private final List<Point> squares;

    Shape(HexColor color, int width, int height, List<Point> squares) {
        this.color = color;
        this.width = width;
        this.height = height;
        this.squares = squares;
    }

    public HexColor getColor() {
        return color;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Point> getSquares() {
        return squares;
    }
};
