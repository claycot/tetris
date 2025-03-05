package com.claycot.tetris;

import java.util.ArrayList;
import java.util.List;

public class Tetromino {
    private final Shape shape;
    private final HexColor hexColor;
    private final int width;
    private final int height;
    private final List<Point> squares;

    private Orientation orientation;

    public Tetromino(Shape shape, HexColor hexColor, int width, int height, List<Point> squares) {
        this.shape = shape;
        this.hexColor = hexColor;
        this.width = width;
        this.height = height;
        this.squares = squares;

        this.orientation = Orientation.TWELVE_OCLOCK;
    }

    // rotate a tetromino by rotating all of its squares, applying kicks, and
    // updating orientation
    public void rotate(boolean cw, Point globalCenter, boolean[][] occupied) {
        // get kicks
        WallKickPointGenerator kickGenerator = new WallKickPointGenerator(this.shape, cw, this.orientation);
        Point[] kicks = kickGenerator.getKicks();

        // copy the current squares
        List<Point> squaresCopy = new ArrayList<>(this.squares);

        // loop through the kicks, updating orientation and returning if a valid one is
        // found
        for (Point kick : kicks) {
            // if the kick passed, update the orientation and return
            if (this.tryMove(squaresCopy, kick, globalCenter, occupied)) {
                // update the orientation
                this.orientation = this.orientation.rotate(cw);
                // rotate the tetromino
                for (Point p : squares) {
                    p.rotate(cw);
                }
                // kick its center
                globalCenter.translateAdd(kick);
                return;
            }
        }
    }

    // the tetromino will never go outside of the bounding box
    // therefore, the tetromino is valid if it doesn't overlap any existing squares
    private boolean tryMove(List<Point> squareCopy, Point move, Point center, boolean[][] occupied) {
        System.out.println(String.format("validating position for tetromino at %s", center.toString()));
        for (Point p : squareCopy) {
            int x = p.getX() + center.getX();
            int y = p.getY() + center.getY();
            int boardHeight = occupied.length;
            int boardWidth = occupied[0].length;
            if (x < 0 || x >= boardWidth || y < 0 || y >= boardHeight || occupied[y][x]) {
                return false;
            }
        }
        System.out.println("passed!");
        return true;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public HexColor getHexColor() {
        return this.hexColor;
    }

    public List<Point> getSquares() {
        return this.squares;
    }
}
