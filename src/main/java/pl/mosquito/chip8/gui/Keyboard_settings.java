package pl.mosquito.chip8.gui;


import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class Keyboard_settings  {
    private Thread thread;

    private Scene               scene;
    private Stage               primarystage;
    private FlowPane            flowPaneLeft;
    private FlowPane            flowPaneRight;
    private FlowPane            flowPaneBottom;
    private BorderPane          borderPane;
    private Button              DoneButton;
    private Button              saveSetButton;
    private Button              closeButton;
    private Buttons             buttons;

    public Keyboard_settings() {
        thread = new Thread( "chip8 keyboard settings");
        thread.start();
        window();

    }

    private void window() {
        System.out.println("Keyboard_settings");

        buttons         = new Buttons();
        primarystage    = new Stage();
        borderPane      = new BorderPane();
        flowPaneLeft    = new FlowPane();
        flowPaneRight   = new FlowPane();
        flowPaneBottom  = new FlowPane();
        DoneButton      = buttons.Done();
        saveSetButton   = buttons.saveSet();
        closeButton     = buttons.closeKeyboard();
        setPane();
        primarystage.setTitle("Keyboard settings");

        scene         = new Scene(borderPane);
        primarystage.setScene(scene);
        primarystage.show();
    }

    private void setPane() {
        flowPaneSet();
        borderPaneSet();
    }

    private void flowPaneSet() {
        flowPaneLeft.setOrientation(Orientation.VERTICAL);
        flowPaneRight.setOrientation(Orientation.VERTICAL);

        flowPaneBottom.setAlignment(Pos.BOTTOM_RIGHT);
        flowPaneBottom.setHgap(10);
        flowPaneBottom.getChildren().addAll(closeButton,saveSetButton,DoneButton);


    }

    private void borderPaneSet() {
        borderPane.setPadding(new Insets(10,10,10,10));

        borderPane.setLeft(flowPaneLeft);
        borderPane.setRight(flowPaneRight);

        borderPane.setBottom(flowPaneBottom);

    }
}
