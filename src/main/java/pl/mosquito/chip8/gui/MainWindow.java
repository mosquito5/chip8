package pl.mosquito.chip8.gui;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.mosquito.chip8.emulator.Screen;

import java.io.*;

public class MainWindow extends Application {
    private Button RunButton;
    private Button LoadRomButton;
    private Button CloseButton;
    private ComboBox styleComboBox;
    private ComboBox resolutionComboBox;
    private FileChooser fileChooser;
    private File file;

    @Override
    public void init() {

        fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        Buttons buttons = new Buttons();

        LoadRomButton = buttons.loadRomButton();
        RunButton = buttons.runButton();
        CloseButton = buttons.closeButton();
        styleComboBox = buttons.screenStyleComboBox();
        resolutionComboBox = buttons.resolutionComboBox();

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Chip8 CPU");
        primaryStage.setResizable(true);

        FlowPane flowPane = new FlowPane(10,10);
        Scene scene = new Scene(flowPane);

        primaryStage.setScene(scene);

        LoadRomButton.setOnAction((ae) -> {
            file = fileChooser.showOpenDialog(primaryStage);
            if(file != null)
                loadRom(file);

        });

        RunButton.setOnAction((ae) -> {
            new Screen((String) resolutionComboBox.getValue(), (String) styleComboBox.getValue());
        });


        flowPane.getChildren().addAll(RunButton, LoadRomButton, CloseButton, styleComboBox, resolutionComboBox);

        primaryStage.show();

    }

    @Override
    public void stop() throws IOException {

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("Select rom");
    }

    private void loadRom(File file) {
        try {
            DataInputStream program = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
            
            byte[] buffer = new byte[(int)file.length()];

            program.read(buffer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

