package org.uob.a2.gameobjects;

public enum Colour {
    RED("\u001b[31m"),
    GREEN("\u001b[32m"),
    YELLOW("\u001b[33m"),
    BLUE("\u001b[34m"),
    MAGENTA("\u001b[35m"),
    CYAN("\u001b[36m"),
    RESET("\u001b[0m");

    public final String code;

    Colour(String colourCode) {
        this.code = colourCode;
    }
}
