package pl.mosquito.chip8.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Buttons {

   Button runButton() {
       Button button = new Button("Run");

       return button;
   }

   Button loadRomButton() {
       Button button = new Button("Load Rom");

       return button;
   }

   Button closeButton() {
       Button button = new Button("Close");

       button.setOnAction((ae) -> {
           Platform.exit();
       });

       return button;

   }
}
