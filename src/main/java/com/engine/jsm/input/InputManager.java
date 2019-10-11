package com.engine.jsm.input;

public class InputManager {

    private final static Mouse mouse = new Mouse();
    private final static Keyboard keyboard = new Keyboard();

    public static Mouse getMouse() {
        return mouse;
    }

    public static void setMouse(Mouse mouse) {
        mouse = mouse;
    }

    public static Keyboard getKeyboard() {
        return keyboard;
    }

    public static void setKeyboard(Keyboard keyboard) {
        keyboard = keyboard;
    }
}
