package pl.mosquito.chip8.emulator;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Screen {
    private final int WIDTH = 64;
    private final int HEIGHT = 32;

    private int[][] graphic = new int[WIDTH][HEIGHT];

    private GraphicsContext gc;

    private Color backgroundColor;

    private Color pixelColor;

    private String scale;

    private int scaleWidth;
    private int scaleHeight;

    private String colorStyleSetted;

    public Screen(String scale,String colorStyleSetted) {
        this.scale = scale;
        this.colorStyleSetted = colorStyleSetted;
        init();
    }

    private void init() {
        setResolution();

        colorStyle(colorStyleSetted);
        window();

        clear();
    }

    private void window() {
        Canvas canvas = new Canvas(scaleWidth, scaleHeight);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(backgroundColor);
        //gc.fillRect(0,0,/*scale**/WIDTH, /*scale**/HEIGHT);

        Group root = new Group();


        //Scene scene = new Scene(new FlowPane(10, 10), 640, 480);
        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.setTitle("Running");

        root.getChildren().addAll(canvas);

        /**
         * for testing
         */
        fillBackground();

        primaryStage.show();

    }

    public void render() {
        
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
                System.out.println("setted black & white");
                break;
            case "Black and green":
                backgroundColor = Color.BLACK;
                pixelColor = Color.GREEN;
                System.out.println("setted black & green");
                break;
        }

    }

    private void fillBackground() {
        for(int x = 0; x < scaleWidth; x++)
            for (int y = 0; y < scaleHeight; y++) {
            gc.fillRect(0,0,scaleWidth,scaleHeight);
            }
    }

    private void setResolution() {
        switch (scale) {
            case "Orginal":
                scaleWidth = WIDTH;
                scaleHeight = HEIGHT;
                System.out.println("setted orginal");
                break;
            case "640x320(2:1)":
                scaleWidth = 10*WIDTH;
                scaleHeight = 10*HEIGHT;
                System.out.println("setted 640x320");
                break;
            case "1280x640(2:1)":
                scaleWidth = 20*WIDTH;
                scaleHeight = 20*HEIGHT;
                System.out.println("setted 1280x640");
                break;
            case "640x480(4:3)":
                scaleWidth = 10*WIDTH;
                scaleHeight = 15*HEIGHT;
                System.out.println("setted 640x480");
                break;
        }
    }
}
