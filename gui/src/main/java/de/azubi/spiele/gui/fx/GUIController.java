package de.azubi.spiele.gui.fx;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GUIController {

    @FXML
    private TextArea taSpieler;

    @FXML
    private TextField tfName;


    public void eintragen(){
        String name = tfName.getText();

        taSpieler.setText(taSpieler.getText() + name + "\n");
    }
}
