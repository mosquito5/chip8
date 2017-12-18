package pl.mosquito.chip8.emulator;

public class Memory {
    //The default size of memory
    private final int DEFAULT_MEMORY_SIZE = 0x1000;

    short[] memory;

    public Memory() {
        this.memory = new short[DEFAULT_MEMORY_SIZE];

    }

}
