package de.azubi.spiele.gui.fx.startseite;

import de.aal.spiel.core.LogikStart;
import de.aal.spiel.core.SpielManager;
import de.aal.spiel.core.Spiellogik;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GUIController {

    @FXML
    private TextArea taSpieler;

    @FXML
    private TextField tfName;

    private SpielManager spielManager = new SpielManager();
    private Spiellogik spiellogik = new Spiellogik();
    private LogikStart logikStart = new LogikStart(spiellogik, spielManager);

    public void eintragen(ActionEvent actionEvent) {
        String name = tfName.getText();
        logikStart.spielerErstellen(name);
        taSpieler.setText(taSpieler.getText() + name + "\n");
    }

    public void starten(ActionEvent actionEvent) {
        logikStart.spielStarten();
    }
}
