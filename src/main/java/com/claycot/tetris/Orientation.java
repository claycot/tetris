package com.claycot.tetris;

public enum Orientation {
    TWELVE_OCLOCK,
    THREE_OCLOCK,
    SIX_OCLOCK,
    NINE_OCLOCK;

    private static final Orientation[] VALUES = values();

    public Orientation rotate(boolean cw) {
        if (cw) {
            return VALUES[(this.ordinal() + 1) % VALUES.length];
        } else {
            return VALUES[(this.ordinal() + VALUES.length - 1) % VALUES.length];
        }
    }

    public int getOrdinal() {
        return this.ordinal();
    }
}
