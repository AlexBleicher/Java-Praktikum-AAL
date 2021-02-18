package de.azubi.spiele.gui.fx.spielbrett;

import de.aal.spiel.core.SpielManager;
import de.aal.spiel.core.Spieler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.util.List;

public class GUISpielbrett {

    @FXML
    private List<Button> fields;
    public ChoiceBox choiceFigur;
    public Button btnPlay;
    private SpielManager spielManager = SpielManager.getInstance();
    public Label lblWuerfeln;
    public Label lblText;

    public boolean hatgewuerfelt;
    public boolean gezogen;

    public Spieler spielerDran = spielManager.getStarter();

    public int wuerfeln(ActionEvent actionEvent) {

        int gewuerfelt = (int) (Math.random() * 6) + 1;
        lblWuerfeln.setText("Du hast eine " + gewuerfelt + " gewürfelt!");
        lblWuerfeln.setVisible(true);
        spielManager.setZahlGewuerfelt(gewuerfelt);
        hatgewuerfelt = true;
        notifyAll();
        return gewuerfelt;

    }

    public void spielenStarten(ActionEvent actionEvent) {
        setBaseIcons();
        btnPlay.setVisible(false);
        lblWuerfeln.setText("Starter: " + spielManager.getStarter().getName());
        lblWuerfeln.setVisible(true);
        lblText.setText("Das Spiel beginnt! Starter zieht zuerst!");
        lblText.setVisible(true);
    }

    public void move(ActionEvent actionEvent) {

        String figur = (String) choiceFigur.getSelectionModel().getSelectedItem();

        switch (figur) {
            case "Figur 1":
                spielManager.setIndexFigur(0);
                break;
            case "Figur 2":
                spielManager.setIndexFigur(1);
                break;
            case "Figur 3":
                spielManager.setIndexFigur(2);
                break;
            case "Figur 4":
                spielManager.setIndexFigur(3);
                break;
        }
        gezogen = true;
    }

    public void zugBeenden() {
        if (hatgewuerfelt && gezogen) {
            spielerDran = spielManager.spielerAendern(spielerDran);
            lblWuerfeln.setText("");
            lblText.setText("Spieler dran: " + spielerDran.getName());
        } else {
            lblText.setText("Noch nicht alle Funktionen ausgeführt!");
        }
    }

    public void setBaseIcons(){
        for (Button field : fields) {
            if(field.getId().contains("Base")){
                Image image = new Image("/figuren/black.png", 10, 10, true, true);
                field.setGraphic(new ImageView(image));
            }
        }
    }
}

