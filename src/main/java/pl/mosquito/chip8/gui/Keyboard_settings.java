package pl.mosquito.chip8.gui;


import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import static pl.mosquito.chip8.gui.MainWindow.isSet;


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
    private Labels              labels;
    private Label[]             label;
    private TextField[]         textFields;
    private String[]            keySet;

    public Keyboard_settings(boolean newThread) {
        if(newThread) {
            thread = new Thread("chip8 keyboard settings");
            thread.start();
            window();
        }

    }

    private void window() {
        System.out.println("Keyboard_settings");

        buttons         = new Buttons();
        labels          = new Labels();
        label           = new Label[16];
        textFields      = new TextField[16];
        keySet          = new String[16];
        primarystage    = new Stage();
        borderPane      = new BorderPane();
        flowPaneLeft    = new FlowPane();
        flowPaneRight   = new FlowPane();
        flowPaneBottom  = new FlowPane();
        DoneButton      = buttons.Done();
        closeButton     = buttons.closeKeyboard();
        saveSetButtonSetOnAction();
        setUI();
        setPane();
        primarystage.setTitle("Keyboard settings");
        primarystage.setResizable(false);


        scene         = new Scene(borderPane,260,470);
        primarystage.setScene(scene);
        primarystage.show();
    }

    private void saveSetButtonSetOnAction() {
        saveSetButton.setOnAction((ae) -> {
            saveSettings();
        });
    }

    private void setUI() {
        for(int element = 0; element < label.length; element++) {
            label[element]      = new Label(labels.getList(element));
            /**
             * defalut settings
             */
            if(!isSet)
                textFields[element] = new TextField(labels.getList(element));
            else
                textFields[element] = new TextField();
            textFields[element].setPrefWidth(25);
        }
    }

    private void setPane() {
        flowPaneSet();
        borderPaneSet();
    }

    /**
     * temporary solution
     */
    private void flowPaneSet() {
        flowPaneLeft.setOrientation(Orientation.VERTICAL);
        flowPaneLeft.setVgap(10.48);
        flowPaneLeft.getChildren().addAll(label);

        flowPaneRight.setOrientation(Orientation.VERTICAL);
        flowPaneRight.getChildren().addAll(textFields);

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

    private void saveSettings() {
        for(int i = 0; i < textFields.length; i++)
            keySet[i] = textFields[i].getText();
        //static MainWindow
        isSet = true;
    }

    public String[] getUserSettings() {
        return this.keySet;
    }
}
