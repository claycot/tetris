package com.claycot.tetris;
public class Point {
    private int x;
    private int y;

    Point() {
        this(0, 0);
    }

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int[] getAsArray() {
        return new int[] { this.x, this.y };
    }

    public Point translateAdd(Point add) {
        this.x += add.getX();
        this.y += add.getY();

        return this;
    }

    public Point translateSubtract(Point subtract) {
        this.x -= subtract.getX();
        this.y -= subtract.getY();

        return this;
    }

    // rotate the point about the origin in 90-degree increments
    public void rotate(boolean cw) {
        int originalX = this.x;
        int originalY = this.y;

        if (cw) {
            this.x = originalY;
            this.y = -1 * originalX;
        }
        else {
            this.x = -1 * originalY;
            this.y = originalX;
        }
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", this.x, this.y);
    }
}
