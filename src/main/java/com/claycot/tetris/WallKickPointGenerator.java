package com.claycot.tetris;

import java.util.Map;

// when a piece rotation is attempted, there are 5 tests
// each test produces a "kick" that offsets the piece
// a successful kick offsets the piece into a valid location
// this factory returns the 5 potential kicks that can be used
public class WallKickPointGenerator {
    // offset values from https://tetris.wiki/Super_Rotation_System
    private static final Point[][] basicOffsets = new Point[][] {
            { new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0) },
            { new Point(0, 0), new Point(1, 0), new Point(1, -1), new Point(0, 2), new Point(1, 2) },
            { new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0) },
            { new Point(0, 0), new Point(-1, 0), new Point(-1, -1), new Point(0, 2), new Point(1, 2) }
    };
    private static final Point[][] iOffsets = new Point[][] {
            { new Point(0, 0), new Point(-1, 0), new Point(2, 0), new Point(-1, 0), new Point(2, 0) },
            { new Point(-1, 0), new Point(0, 0), new Point(0, 0), new Point(0, 1), new Point(0, -2) },
            { new Point(-1, 1), new Point(1, 1), new Point(-2, 1), new Point(1, 0), new Point(-2, 0) },
            { new Point(0, 1), new Point(0, 1), new Point(0, 1), new Point(0, -1), new Point(0, 2) }
    };
    private static final Point[][] oOffsets = new Point[][] {
            { new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0) },
            { new Point(0, -1), new Point(0, -1), new Point(0, -1), new Point(0, -1), new Point(0, -1) },
            { new Point(-1, -1), new Point(-1, -1), new Point(-1, -1), new Point(-1, -1), new Point(-1, -1) },
            { new Point(-1, 0), new Point(-1, 0), new Point(-1, 0), new Point(-1, 0), new Point(-1, 0) },
    };
    private static final Map<Shape, Point[][]> OFFSET_MAP = Map.of(Shape.I, iOffsets, Shape.O, oOffsets);

    private final Orientation orientation;
    private final boolean cw;
    private final Shape shape;

    WallKickPointGenerator(Shape shape, boolean cw, Orientation orientation) {
        this.shape = shape;
        this.cw = cw;
        this.orientation = orientation;
    }

    // to offset from A -> B, subtract B from A
    public Point[] getKicks() {
        int initialOrientation = this.orientation.getOrdinal();
        int nextOrientation = this.orientation.rotate(this.cw).getOrdinal();

        Point[][] offsets = OFFSET_MAP.getOrDefault(this.shape, basicOffsets);
        Point[] kicks = new Point[offsets[initialOrientation].length];

        for (int i = 0; i < kicks.length; i++) {
            kicks[i] = offsets[initialOrientation][i].translateSubtract(offsets[nextOrientation][i]);
        }

        return kicks;
    }

}
