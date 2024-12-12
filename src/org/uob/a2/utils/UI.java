package org.uob.a2.utils;

import org.uob.a2.gameobjects.Colour;

public class UI {

    public String addMapPlayerPosColour(String s) {
        return new String(Colour.RED.code + s + Colour.RESET.code);
    }

    public static String addColour(String s) {
        return s;
    }
}
