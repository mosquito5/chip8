package pl.mosquito.chip8.gui;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow extends Application {
    private Button RunButton;
    private Button LoadRomButton;
    private Button CloseButton;

    @Override
    public void init() {

        Buttons buttons = new Buttons();

        RunButton = buttons.runButton();
        LoadRomButton = buttons.loadRomButton();
        CloseButton = buttons.closeButton();

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Chip8 emulator");
        primaryStage.setResizable(true);

        FlowPane flowPane = new FlowPane(10,10);
        Scene scene = new Scene(flowPane);

        primaryStage.setScene(scene);

        flowPane.getChildren().addAll(RunButton, LoadRomButton, CloseButton);

        primaryStage.show();

    }

    @Override
    public void stop() throws IOException {

    }
}

