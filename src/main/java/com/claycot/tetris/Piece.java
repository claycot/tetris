package com.claycot.tetris;

import java.util.List;

public abstract class Piece {
    private final Shape shape;
    private final HexColor hexColor;
    private final int width;
    private final int height;
    private final List<Point> squares;

    private Orientation orientation;

    Piece(Shape shape, HexColor hexColor, int width, int height, List<Point> squares) {
        this.shape = shape;
        this.hexColor = hexColor;
        this.width = width;
        this.height = height;
        this.squares = squares;

        this.orientation = Orientation.TWELVE_OCLOCK;
    }

    // translate a piece by translating all of its squares
    public void translate(Point delta, boolean positive) {
        if (positive) {
            for (Point p : squares) {
                p.translateAdd(delta);
            }
        } else {
            for (Point p : squares) {
                p.translateSubtract(delta);
            }
        }
    }

    // rotate a piece by rotating all of its squares, applying kicks, and updating
    // orientation
    public void rotate(boolean cw, HexColor[][] existingSquares) {
        // get kicks
        WallKickPointGenerator kickGenerator = new WallKickPointGenerator(this.shape, cw, this.orientation);
        Point[] kicks = kickGenerator.getKicks();

        // rotate the piece
        for (Point p : squares) {
            p.rotate(cw);
        }

        // loop through the kicks, updating orientation and returning if a valid one is
        // found
        for (Point kick : kicks) {
            // translate the piece by the kick
            this.translate(kick, true);

            // if the new location is valid, update the orientation and return
            if (this.validatePosition(existingSquares)) {
                this.orientation = this.orientation.rotate(cw);
                return;
            }
            // if not valid, undo the translation
            else {
                this.translate(kick, false);
            }
        }

        // if a valid kick isn't found, undo the rotation
        for (Point p : squares) {
            p.rotate(!cw);
        }
    }

    // the piece will never go outside of the bounding box
    // therefore, the piece is valid if it doesn't overlap any existing squares
    private boolean validatePosition(HexColor[][] existingSquares) {
        for (Point p : this.squares) {
            if (existingSquares[p.getX()][p.getY()] != null) {
                return false;
            }
        }

        return true;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public HexColor getColor() {
        return this.hexColor;
    }
}
