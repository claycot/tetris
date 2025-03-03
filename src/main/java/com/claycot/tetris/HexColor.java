package com.claycot.tetris;

public enum HexColor {
    CYAN("#00ffff"),
    BLUE("#0000ff"),
    ORANGE("#ff7f00"),
    YELLOW("#ffff00"),
    GREEN("#00ff00"),
    PURPLE("#800080"),
    RED("#ff0000"),
    GRAY("#7f7f7f");

    private final String hexValue;

    HexColor(String hexValue) {
        this.hexValue = hexValue;
    }

    public String getHexValue() {
        return this.hexValue;
    }
}
