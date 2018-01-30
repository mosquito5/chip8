package pl.mosquito.chip8.gui;


import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class Keyboard_settings  {
    private Thread thread;

    private Group               root;
    private Scene               scene;
    private Stage               primarystage;
    private FlowPane            flowPaneLeft;
    private FlowPane            flowPaneRight;
    private BorderPane          borderPane;
    private Button              DoneButton;
    private Button              saveSetButton;
    private Buttons buttons     = new Buttons();

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
        DoneButton    = buttons.Done();
        saveSetButton = buttons.saveSet();
        setPane();
        primarystage.setTitle("Keyboard settings");
        borderPane.getChildren().addAll(flowPaneLeft);

        primarystage.show();
    }

    private void setPane() {
        borderPaneSet();
        flowPaneSet();
    }

    private void flowPaneSet() {
        flowPaneLeft.setOrientation(Orientation.VERTICAL);
        flowPaneLeft.getChildren().addAll(DoneButton, saveSetButton);
        flowPaneRight.setOrientation(Orientation.VERTICAL);

    }

    private void borderPaneSet() {
        borderPane.setLeft(flowPaneLeft);
        borderPane.setRight(flowPaneRight);
    }
}
