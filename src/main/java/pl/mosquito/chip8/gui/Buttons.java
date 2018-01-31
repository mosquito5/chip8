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
import javafx.stage.Window;

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

   Button keyboardSettings() {
       Button button = new Button("Keyboard settings");

       button.setOnAction((ae) -> {
           new Keyboard_settings();

       });
       return button;
   }

   ComboBox screenStyleComboBox() {
       ComboBox comboBox = new ComboBox();
       comboBox.setTooltip(new Tooltip("Select screen style"));
       comboBox.getItems().addAll("Black and white", "Black and green");
       comboBox.getSelectionModel().selectFirst();
       comboBox.setEditable(false);
       return comboBox;
   }

   ComboBox resolutionComboBox() {
       ComboBox comboBox = new ComboBox();
       comboBox.setTooltip(new Tooltip("Select screen resolution"));
       comboBox.getItems().addAll("Orginal","640x320(2:1)", "1280x640(2:1)", "640x480(4:3)");
       comboBox.getSelectionModel().select(1);
       comboBox.setEditable(false);
       return comboBox;
   }
    /**
     * buttons for keyboard settings class
     */
    Button Done() {
        Button button = new Button("Done");
        button.setOnAction((ae) -> {
            Platform.exit();
        });

        return button;
    }

    Button saveSet() {
        Button button = new Button("Save settings");

        return button;
    }

    Button closeKeyboard() {
        Button button = new Button("Close");
        button.setOnAction((ae) -> {
            Platform.exit();
        });
        return button;
    }



}
