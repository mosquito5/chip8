package pl.mosquito.chip8.emulator;

import static pl.mosquito.chip8.emulator.Keyboard.KSIZE;
import static pl.mosquito.chip8.emulator.Keyboard.KeyCode;

public class Memory {

    //The default size of memory

    private final int MEMORY_4K = 0x1000; //4096
    private final int REG_SIZE = 16;

    private int opcode;

    private short[] memory;
    private int[] V;

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
        V               = new int[REG_SIZE];
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
            case 0x1:
                jumpToAddr(opcode);
                break;
            /**
             * 2NN-jump to subroutine at address NNN
             */
            case 0x2:
                jumpToSubroutineAddr(opcode);
                break;
            /**
             * 3XKK-skip next instruction if reguster VX == register KK
             */
            case 0x3:
                skipInstr("3XKK");
                break;
            /**
             * 4XKK-skip next instruction if reguster VX != register KK
             */
            case 0x4:
                skipInstr("4XKK");
                break;
            /**
             * 5XY0-skip next instruction if VX == VY
             */
            case 0x5:
                skipInstr("5XY0");
                break;

            /**
             * 6XKK
             * set VX = KK
             * The interpreter puts the value KK into register VX.
             */
            case 0x6:
                putKKintoVX();
                break;
            /**
             * 7XKK
             * Set VX = VX + KK.
             *Adds the value kk to the value of register VX, then stores the result in VX.
             */
            case 0x7:
                addValueToRegister();
                break;

            case 0x8:
                switch (opcode & 0x000F) {
                    /**
                     * Store the value of register VY in register VX
                     */
                    case 0x0:
                        registerVYinVX();
                        break;
                    /**
                     * 8XY1
                     * Set VX to VX or VY
                     */
                    case 0x1:
                        logicalOR();
                        break;
                    /**
                     * 8XY2
                     * Set VX to VX and VY
                     */
                    case 0x2:
                        logicalAND();
                        break;
                    /**
                     * 8XY3
                     *Set VX to VX XOR VY
                     */
                    case 0x3:
                        logicalXOR();
                        break;
                    /**
                     *8XY4
                     *Add the value of register VY to register VX
                     *Set VF to 01 if a carry occurs
                     *Set VF to 00 if a carry does not occur
                     */
                    case 0x4:
                        addRegisterToRegister();
                        break;

                }
                break;
            /**
             * The value of register indexRegister is set to nnn
             */
            case 0xA000:
                idndexRegisterSetTo(opcode);
            break;

        }
    }

    /**
     * Similar to the 7XKK instruction, the 8XY4 instruction will cause values too large to be stored in a register
     * to be wrapped around to a modulo equivelent. However, unlike the 7XNN instruction, the 8XY4 instruction will
     * modify the VF register to signal when a carry has taken place. A carry is a term used to describe
     * the aforementioned action of when a value is too large to be stored in a given register.
     * When a carry takes place, the interpreter will set register VF to 01.
     * Otherwise, VF will be set to 00. Therefore, the 8XY4 instruction will always modify the VF register.
     *
     * notice from:
     * http://mattmik.com/files/chip8/mastering/chip8.html
     */

    private void addRegisterToRegister() {
        int temp = V[(opcode & 0x0F00) >> 8] + V[(opcode & 0x00F0) >> 4];

        if(temp > 255) {
            V[(opcode & 0x0F00) >> 8] = temp - 256;
            V[0xF] = 1;
        }
        else {
            V[(opcode & 0x0F00) >> 8] = temp;
            V[0xF] = 0;
        }
        pc += 2;

    }
    private void logicalXOR() {
        V[(opcode & 0x0F00) >> 8] ^= V[(opcode & 0x00F0) >> 4];

    }

    private void logicalAND() {
        V[(opcode & 0x0F00) >> 8] &= V[(opcode & 0x00F0) >> 4];
        pc =+ 2;

    }

    private void logicalOR() {
        /**
         * a = a | b == a |= b
         */
        V[(opcode & 0x0F00) >> 8] |= V[(opcode & 0x00F0) >> 4];
        pc =+ 2;
    }


    private void registerVYinVX() {
        V[(opcode & 0x0F00) >> 8] = V[(opcode & 0x00F0) >> 4];
        pc += 2;
    }


    /**
     * Be aware that once the supplied number is added, if the value of the register exceeds decimal 255
     * (the highest possible value that can be stored by an eight bit register), the register will wraparound to a
     * corresponding value that can be stored by an eight bit register. In other words, the register will always be
     * reduced modulo decimal 256.
     *
     * notice from:
     * http://mattmik.com/files/chip8/mastering/chip8.html
     */
    private void addValueToRegister() {
        int result = V[(opcode & 0x0F00) >> 8] + (opcode & 0x00FF);
        V[(opcode & 0x0F00) >> 8] = (result > 255) ? result - 256 : result;
        pc += 2;
    }

    private void putKKintoVX() {
        V[(opcode & 0x0F00) >> 8] = (opcode & 0x00FF);
        pc +=2;
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
        pc =        (opcode & 0x0FFF);
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




