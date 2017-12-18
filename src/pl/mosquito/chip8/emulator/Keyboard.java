package pl.mosquito.chip8.emulator;


import javafx.scene.input.KeyEvent;

public class Keyboard  {

    static final int [] KeyCodeMap = {
            1,
            2,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            0,
            (int) 'a',
            (int) 'b',
            (int) 'f',
            (int) 'e',
            (int) 'd',
            (int) 'c',
    };

    private int CurrentKey = 0;



    int getCurrentKey() {
        return CurrentKey;
    }
}
