package pl.mosquito.chip8.emulator;


import pl.mosquito.chip8.gui.Keyboard_settings;
import pl.mosquito.chip8.gui.Labels;

import static pl.mosquito.chip8.gui.MainWindow.isSet;

public class Keyboard  {
    public static final int KSIZE = 16;
    /**
     * Originally used by the chip8 language had
     * a 16-key hexadecimal keypad.
     * This array stores the state of
     * each key
     */
    public  boolean[]           Keyboard;
    private String []           userKeys;
    private Labels              labels;
    private Keyboard_settings   keyboard_settings;



    public static final short KeyCode[] = {
            0xF0, 0x90, 0x90, 0x90, 0xF0, //0
            0x20, 0x60, 0x20, 0x20, 0x70, //1
            0xF0, 0x10, 0xF0, 0x80, 0xF0, //2
            0xF0, 0x10, 0xF0, 0x10, 0xF0, //3
            0x90, 0x90, 0xF0, 0x10, 0x10, //4
            0xF0, 0x80, 0xF0, 0x10, 0xF0, //5
            0xF0, 0x80, 0xF0, 0x90, 0xF0, //6
            0xF0, 0x10, 0x20, 0x40, 0x40, //7
            0xF0, 0x90, 0xF0, 0x90, 0xF0, //8
            0xF0, 0x90, 0xF0, 0x10, 0xF0, //9
            0xF0, 0x90, 0xF0, 0x90 ,0x90, //A
            0xE0, 0x90, 0xE0, 0x90, 0xE0, //B
            0xF0, 0x80, 0x80, 0x80, 0xF0, //C
            0xE0, 0x90, 0x90, 0x90, 0xE0, //D
            0xF0, 0x80, 0xF0, 0x80, 0xF0, //E
            0xF0, 0x80, 0xF0, 0x80, 0x80, //F

    };



    public Keyboard() {

        Keyboard            = new boolean[KSIZE];
        userKeys            = new String [KSIZE];
        labels              = new Labels();
        keyboard_settings   = new Keyboard_settings(false);
        setUserKeys();
        /**
         * sets all keys unpressed
         */
        setKeys();

    }


    private void setKeys() {
        for(int i = 0; i < KSIZE; i++)
            Keyboard[i] = false;
    }

    private void setUserKeys() {
        if(!isSet)
            for(int element = 0; element < userKeys.length; element++)
                userKeys[element] = labels.getList(element);
        else
            userKeys = keyboard_settings.getUserSettings();
    }

    /**
     *sets Keyboard true when key is pressed
     */
    private void setKeyDown(String key) {
        //0
        if (key == userKeys[0])
            Keyboard[0] = true;
        //1
        else if(key == userKeys[1])
            Keyboard[1] = true;
        //2
        else if(key == userKeys[2])
            Keyboard[2] = true;
        //3
        else if(key == userKeys[3])
            Keyboard[3] = true;
        //4
        else if(key == userKeys[4])
            Keyboard[4] = true;
        //5
        else if(key == userKeys[5])
            Keyboard[5] = true;
        //6
        else if(key == userKeys[6])
            Keyboard[6] = true;
        //7
        else if(key == userKeys[7])
            Keyboard[7] = true;
        //8
        else if(key == userKeys[8])
            Keyboard[8] = true;
        //9
        else if(key == userKeys[9])
            Keyboard[9] = true;
        //A
        else if(key == userKeys[10])
            Keyboard[10] = true;
        //B
        else if(key == userKeys[11])
            Keyboard[11] = true;
        //C
        else if(key == userKeys[12])
            Keyboard[12] = true;
        //D
        else if(key == userKeys[13])
            Keyboard[13] = true;
        //E
        else if(key == userKeys[14])
            Keyboard[14] = true;
        //F
        else if(key == userKeys[15])
            Keyboard[15] = true;
    }

    /**
     *sets Keyboard false when key is released
     */

    private void setKeyUp(String key) {
        //0
        if (key == userKeys[0])
            Keyboard[0] = false;
            //1
        else if(key == userKeys[1])
            Keyboard[1] = false;
            //2
        else if(key == userKeys[2])
            Keyboard[2] = false;
            //3
        else if(key == userKeys[3])
            Keyboard[3] = false;
            //4
        else if(key == userKeys[4])
            Keyboard[4] = false;
            //5
        else if(key == userKeys[5])
            Keyboard[5] = false;
            //6
        else if(key == userKeys[6])
            Keyboard[6] = false;
            //7
        else if(key == userKeys[7])
            Keyboard[7] = false;
            //8
        else if(key == userKeys[8])
            Keyboard[8] = false;
            //9
        else if(key == userKeys[9])
            Keyboard[9] = false;
            //A
        else if(key == userKeys[10])
            Keyboard[10] = false;
            //B
        else if(key == userKeys[11])
            Keyboard[11] = false;
            //C
        else if(key == userKeys[12])
            Keyboard[12] = false;
            //D
        else if(key == userKeys[13])
            Keyboard[13] = false;
            //E
        else if(key == userKeys[14])
            Keyboard[14] = false;
            //F
        else if(key == userKeys[15])
            Keyboard[15] = false;
    }





    public boolean isPressed(int i) {
        return Keyboard[i];
    }

}
