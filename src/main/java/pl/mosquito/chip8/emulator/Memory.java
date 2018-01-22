package pl.mosquito.chip8.emulator;

import static pl.mosquito.chip8.emulator.Keyboard.KSIZE;
import static pl.mosquito.chip8.emulator.Keyboard.KeyCode;

public class Memory {
    //The default size of memory

    private final int MEMORY_4K = 0x1000; //4096
    private final int REG_SIZE = 16;

    private int opcode;

    private short[] memory;
    private byte[] V;

    private int indexRegister;
    private int pc; // program counter whitch can contain a value from 0x000 to 0xFFF

    /**
     * timer registers that counts at 60Hz.
     * When set above 0 they will count down to 0
     */
    private int delayTimer;
    private int soundTimer; // The system's buzzer sounds whenever the sound timer reaches 0

    /**
     *@param stack - Remembers location between jumps
     *@param sp - Remember which level of stack was used.
     */
    private int stack[];
    private byte sp;

    private Screen      screen;
    //private Keyboard    keyboard;



    public Memory(Screen screen) {
        memory          = new short[MEMORY_4K];
        V               = new byte[REG_SIZE];
        stack           = new int[REG_SIZE];

        pc              = 0x200; //Program counter starts at 0x200
        opcode          = 0; //Reset current opcode
        indexRegister   = 0;
        sp              = 0; // Reset stack pointer

        this.screen     = screen;
        //keyboard        = new Keyboard();

        /**
         * @param init initialize system before running the first emulation cycle
         * @param reset resets: memory, stack registers, timers and clears display
         * @param loadfont load fontset
         */
        init();
        reset();
        loadfont();
    }

    private void reset() {
        //Resets the memory, register and stack
        for (int i = 0; i < memory.length; i++) {
            memory[i] = 0;
            if(i < REG_SIZE) {
                V[i]        = 0;
                stack[i]    = 0;
            }
        }
        screen.clear();      // Reset the screen
        delayTimer = 0;
        soundTimer = 0;
    }

    private void init() {
        pc              = 0x200; //Program counter starts at 0x200
        opcode          = 0; //Reset current opcode
        indexRegister   = 0;
        sp              = 0; // Reset stack pointer
    }

    private void loadfont() {
        for(int i = 0; i < KSIZE; i++){
            memory[i] = KeyCode[i];
        }
    }

    /**
     *Loads program into memory.
     * starts from the memory location 0x200
     */

    public void loadProgram(byte[] buffer) {
        for(int i = 0; i < buffer.length; i++ )
            memory[i + 0x200] = (buffer[i]);
    }

    /**
     * fetch a single opcode
     */
    public void fetchOpcode() {
        opcode = memory[pc] << 8 | memory[pc + 1];
    }

    /**
     * Decodes an opcode and perform target action
     */

    public void decodeOpcode() {


        switch (opcode & 0xF000) {
            case 0x0000:
                switch (opcode & 0x000F) {
                    /**
                     * clear the display
                     */
                    case 0x0000:
                        scrClr();
                    break;
                    /**
                     * Return from subroutine call
                     */
                    case 0x000E:
                        returnFromSubroutine();
                    break;
                }
                /**
                 * jump to address NNN
                 */
            case 0x1000:
                jumpToAddr(opcode);
            break;
            /**
             * 2NN-jump to subroutine at address NNN
             */
            case 0x2000:
                jumpToSubroutineAddr(opcode);
            break;
            /**
             * 3XKK-skip next instruction if reguster VX == register KK
             */
            case 0x3000:
                skipInstr("3XKK");
            break;
            /**
             * 4XKK-skip next instruction if reguster VX != register KK
             */
            case 0x4000:
                skipInstr("4XKK");
            break;
            /**
             * 5XY0 skip next instruction if VX == VY
             */
            case 0x5000:
                skipInstr("5XY0");
            break;


            /**
             * The value of register indexRegister is set to nnn
             */
            case 0xA000:
                idndexRegisterSetTo(opcode);
                break;

        }
    }

    private void skipInstr(String instruction) {
        switch (instruction) {
            case "3XKK":
                if (V[(opcode & 0x0F00) >> 8] == (opcode & 0x00FF))
                    pc += 4;
                else
                    pc += 2;
                break;

            case "4XKK":
                if (V[(opcode & 0x0F00) >> 8] != (opcode & 0x00FF))
                    pc += 4;
                else
                    pc += 2;
            break;

            case "5XY0" :
                if (V[(opcode & 0x0F00) >> 8] == (opcode & 0x00F0) >> 4)
                    pc += 4;
                else
                    pc += 2;

            break;


        }
    }

    private void jumpToSubroutineAddr(int opcode) {
        stack[sp] = pc;
        sp++;
        pc = (opcode & 0x0FFF);
    }

    private void jumpToAddr(int opcode) {
        pc = (opcode & 0x0FFF);
    }

    private void idndexRegisterSetTo(int opcode) {
        indexRegister = (opcode & 0x0FFF);
        pc += 2;
    }

    private void scrClr() {
        screen.clear();
        pc += 2;
    }

    private void returnFromSubroutine() {
        pc =    stack[sp--];
        pc +=   2;
    }
}




