package de.azubi.spiele.gui.fx.startseite;

//import de.aal.spiel.core.LogikStart;

import de.aal.spiel.core.SpielManager;
import de.aal.spiel.core.Spieler;
//import de.aal.spiel.core.Spiellogik;
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

public class GUIStartseite {

    public Button btnStarten;
    @FXML
    private TextArea taSpieler;

    @FXML
    private TextField tfName;

    private SpielManager spielManager = SpielManager.getInstance();
    //private Spiellogik spiellogik = new Spiellogik();
    //private LogikStart logikStart = new LogikStart(spiellogik, spielManager);

    public void eintragen(ActionEvent actionEvent) throws Exception {
        String name = tfName.getText();
        spielManager.getSpiellogik().addSpieler(new Spieler(name));
        tfName.setText("");
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

        spielManager.spielVorbereiten();
        System.out.println(spielManager.getStarter().getName());
    }
}
