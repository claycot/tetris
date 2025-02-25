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
    public void translate(Point delta) {
        for (Point p : squares) {
            p.translateAdd(delta);
        }
    }

    // rotate a piece by rotating all of its squares and updating orientation
    public void rotate(boolean cw) {
        for (Point p : squares) {
            p.rotate(cw);
        }
        this.orientation = this.orientation.rotate(cw);
    }


}
