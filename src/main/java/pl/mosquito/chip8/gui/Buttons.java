package pl.mosquito.chip8.gui;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
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

   ComboBox comboBox() {
       ComboBox comboBox = new ComboBox();
       comboBox.setTooltip(new Tooltip("Select screen style"));
       comboBox.getItems().addAll("Black and white", "Black and green");
       comboBox.getSelectionModel().selectFirst();
       comboBox.setEditable(false);
       return comboBox;
   }


}