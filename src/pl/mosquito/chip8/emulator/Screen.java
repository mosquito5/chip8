package pl.mosquito.chip8.emulator;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Screen {
    private final int WIDTH = 64;
    private final int HEIGHT = 32;

    private int[][] graphic = new int[WIDTH][HEIGHT];

    private GraphicsContext gc;

    private Color backgroundColor;

    private Color foregroundColor;

    private int scale;

    public Screen(int scale) {
        this.scale = scale;
        backgroundColor = Color.BLACK;
        foregroundColor = Color.WHITE;
        Canvas canvas = new Canvas(/*scale**/WIDTH, /*scale***/HEIGHT);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(backgroundColor);
        gc.fillRect(0,0,/*scale**/WIDTH, /*scale**/HEIGHT);
    }

    public void clear() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++)
                graphic[x][y] = 0;
        }
    }




}
