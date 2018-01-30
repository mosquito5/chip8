package pl.mosquito.chip8.gui;


import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Keyboard_settings  {
    private Thread thread;

    private Group       root;
    private Scene       scene;
    private Stage       primarystage;
    private FlowPane    flowPaneLeft;
    private FlowPane    flowPaneRight;
    private BorderPane  borderPane;

    public Keyboard_settings() {
        thread = new Thread( "chip8 keyboard settings");
        thread.start();
        window();

    }

    private void window() {
        System.out.println("Keyboard_settings");
        root          = new Group();
        scene         = new Scene(root);
        primarystage  = new Stage();
        borderPane    = new BorderPane();
        flowPaneLeft  = new FlowPane();
        flowPaneRight = new FlowPane();
        setPane();
        primarystage.setTitle("Keyboard settings");

        primarystage.show();
    }

    private void setPane() {
        borderPaneSet();
        flowPaneSet();
    }

    private void flowPaneSet() {
        flowPaneLeft.setOrientation(Orientation.VERTICAL);
        flowPaneRight.setOrientation(Orientation.VERTICAL);

    }

    private void borderPaneSet() {
        borderPane.setLeft(flowPaneLeft);
        borderPane.setRight(flowPaneRight);
    }
}
