package de.azubi.spiele.gui.fx.spielbrett;

import de.aal.spiel.core.SpielManager;
import de.aal.spiel.core.Spieler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class GUISpielbrett {

    public ChoiceBox choiceFigur;
    public Button btnPlay;
    private SpielManager spielManager = SpielManager.getInstance();
    public Label lblWuerfeln;
    public Label lblText;

    public boolean beendet=false;
    public boolean gewuerfelt;

    public int wuerfeln(ActionEvent actionEvent) {

        int hatgewuerfelt = (int) (Math.random() * 6) + 1;
        lblWuerfeln.setText("Du hast eine " + hatgewuerfelt + " gewürfelt!");
        lblWuerfeln.setVisible(true);
        spielManager.setZahlGewuerfelt(hatgewuerfelt);
        gewuerfelt=true;
        notifyAll();
        return hatgewuerfelt;

    }

    public void spielenStarten(ActionEvent actionEvent) {
        btnPlay.setVisible(false);
        lblWuerfeln.setText("Starter: " + spielManager.getStarter().getName());
        lblWuerfeln.setVisible(true);
        lblText.setText("Das Spiel beginnt! Starter zieht zuerst!");
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
    }
}

