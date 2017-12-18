package pl.mosquito.chip8.emulator;

import java.awt.event.KeyAdapter;

public class Keyboard extends KeyAdapter {

    static final int [] KeyCodeMap = {

    };

    private int CurrentKey = 0;

    int getCurrentKey() {
        return CurrentKey;
    }
}
