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

    private String resolution;

    private int scaleWidth;
    private int scaleHeight;
    private int pixelWidthScale;
    private int pixelHeightScale;
    private int scale;

    private boolean customRatio =  false;

    private String colorStyleSet;

    public Screen(String resolution, String colorStyleSet) {
        this.resolution = resolution;
        this.colorStyleSet = colorStyleSet;
        init();
    }

    private void init() {
        setResolution();

        colorStyle(colorStyleSet);
        window();

        clear();
    }

    private void window() {
        Canvas canvas = new Canvas(scaleWidth, scaleHeight);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(backgroundColor);
        //gc.fillRect(0,0,/*resolution**/WIDTH, /*resolution**/HEIGHT);

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
//is more efficiently?
    public void render() {
        if (customRatio) {
            for (int x = 0; x < WIDTH; x++)
                for (int y = 0; y < HEIGHT; y++) {
                    if (graphic[x][y] == 1)
                        gc.setFill(pixelColor);
                    else
                        gc.setFill(Color.BLACK);

                    gc.fillRect(x * pixelWidthScale, y * pixelHeightScale,
                            scaleWidth, scaleHeight);
                }
        } else {
                for (int x = 0; x < WIDTH; x++)
                    for (int y = 0; y < HEIGHT; y++) {
                        if (graphic[x][y] == 1)
                            gc.setFill(pixelColor);
                        else
                            gc.setFill(Color.BLACK);

                        gc.fillRect(x * scale, y * scale, scaleWidth, scaleHeight);
                    }
            }
        }

    public void clear() {
        for (int x = 0; x < WIDTH; x++)
            for (int y = 0; y < HEIGHT; y++)
                graphic[x][y] = 0;

    }


    public void setPixel(int x, int y) {
        graphic[x][y] = 1;
    }

    private void colorStyle(String colorStyleSetted) {
        switch (colorStyleSetted) {
            case "Black and white":
                backgroundColor = Color.BLACK;
                pixelColor = Color.WHITE;
                System.out.println("set black & white");
                break;
            case "Black and green":
                backgroundColor = Color.BLACK;
                pixelColor = Color.GREEN;
                System.out.println("set black & green");
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
        switch (resolution) {
            case "Orginal":
                scaleWidth = WIDTH;
                scaleHeight = HEIGHT;
                System.out.println("set orginal");
                break;
            case "640x320(2:1)":
                scale = 10;
                scaleWidth = scale*WIDTH;
                scaleHeight = scale*HEIGHT;
                System.out.println("set 640x320");
                break;
            case "1280x640(2:1)":
                scale = 20;
                scaleWidth = scale*WIDTH;
                scaleHeight = scale*HEIGHT;
                System.out.println("set 1280x640");
                break;
            case "640x480(4:3)":
                pixelWidthScale = 10;
                pixelHeightScale = 15;
                scaleWidth = pixelWidthScale*WIDTH;
                scaleHeight = pixelHeightScale*HEIGHT;
                customRatio = true;
                System.out.println("set 640x480");
                break;
        }
    }
}
