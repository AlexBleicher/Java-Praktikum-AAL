package de.azubi.spiele.gui.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GUIController {

    @FXML
    private TextArea taSpieler;

    @FXML
    private TextField tfName;

    public void eintragen(ActionEvent actionEvent) {
        String name = tfName.getText();
        // Eintragung in LogikSpieler
        taSpieler.setText(taSpieler.getText() + name + "\n");
    }

    public void starten(ActionEvent actionEvent) {
        // Starten der eigentlichen Methode
    }
}
