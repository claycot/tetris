package com.claycot.tetris;

public enum Movement {
    SLAM(new Point(0, -1)),
    DROP(new Point(0, -1)),
    LEFT(new Point(-1, 0)),
    RIGHT(new Point(1, 0));

    private final Point point;

    Movement(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return this.point;
    }
}
