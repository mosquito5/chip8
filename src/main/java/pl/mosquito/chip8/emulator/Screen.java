package pl.mosquito.chip8.emulator;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Screen {
    private final int WIDTH = 64;
    private final int HEIGHT = 32;

    private int[][] graphic = new int[WIDTH][HEIGHT];

    private GraphicsContext gc;

    private Color backgroundColor;

    private Color pixelColor;

    private int scale;

    private String colorStyleSetted;

    public Screen(int scale,String colorStyleSetted) {
        this.scale = scale;
        this.colorStyleSetted = colorStyleSetted;
        init();
    }

    private void init() {
        colorStyle(colorStyleSetted);
        window();

        Canvas canvas = new Canvas(/*scale**/WIDTH, /*scale***/HEIGHT);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(backgroundColor);
        gc.fillRect(0,0,/*scale**/WIDTH, /*scale**/HEIGHT);
    }

    private void window() {
        Scene scene = new Scene(new FlowPane(10, 10), 640, 480);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.setTitle("Running");
        primaryStage.show();

    }

    public void clear() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++)
                graphic[x][y] = 0;
        }
    }

    private void colorStyle(String colorStyleSetted) {
        switch (colorStyleSetted) {
            case "Black and white":
                backgroundColor = Color.BLACK;
                pixelColor = Color.WHITE;
                break;
            case "Black and green":
                backgroundColor = Color.BLACK;
                pixelColor = Color.GREEN;
        }

    }
}
