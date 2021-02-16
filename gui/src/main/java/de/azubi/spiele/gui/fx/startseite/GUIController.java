package de.azubi.spiele.gui.fx.startseite;

import de.aal.spiel.core.LogikStart;
import de.aal.spiel.core.SpielManager;
import de.aal.spiel.core.Spiellogik;
import de.azubi.spiele.gui.fx.spielbrett.GUISpielbrett;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class GUIController {

    public Button btnStarten;
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

    public void starten(ActionEvent actionEvent) throws IOException {

        btnStarten.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/spielbrett/GUISpielbrett.fxml"));
        Parent rootBrett = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(rootBrett));
        stage.setTitle("Mensch Ärgere dich nicht!");
        stage.show();

        logikStart.spielStarten();
    }
}
