package de.azubi.spiele.gui.fx.spielbrett;

import de.aal.spiel.core.Figur;
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


    public void zugBeenden() {
        if (hatgewuerfelt && gezogen) {
            spielerDran = spielManager.spielerAendern(spielerDran);
            lblWuerfeln.setText("");
            lblText.setText("Spieler dran: " + spielerDran.getName());
        } else {
            lblText.setText("Noch nicht alle Funktionen ausgeführt!");
        }
    }

    @FXML
    public void getButtonPressedNumber(ActionEvent event){
        Button btn = (Button) event.getSource();
        String fieldName = btn.getId();
        int feld = getFieldNumber(fieldName);
        checkIfFigureOnField(feld);
    }

    public int getFieldNumber(String fieldName){
        int fieldNumber = Integer.parseInt(fieldName.replaceAll("\\D+",""));
        return fieldNumber;
    }

    public void checkIfFigureOnField(int feld){
        for (Figur figur : spielerDran.getFiguren()) {
            if(figur.getFeld().equals(feld)){
                int indexFigur = spielerDran.getFiguren().indexOf(figur);
                spielManager.setIndexFigur(indexFigur);
                gezogen = true;
                int nextFeld = feld + spielManager.getZahlGewuerfelt();
                String buttonNow = "btnField" + feld;
                String buttonNext = "btnField" + nextFeld;
                for (Button field : fields) {
                    if(field.getId().equals(buttonNext)){
                        setIcon(field, spielerDran.getFarbe());
                    }
                    if(field.getId().equals(buttonNow)){
                        field.setGraphic(null);
                    }
                }
            }
        }
    }

    public void setBaseIcons(){
        for (Button field : fields) {
            if(field.getId().contains("Base")){
                setIcon(field, "black");
            }
        }
    }

    public void setIcon(Button field, String color) {
        Image image = new Image("/figuren/" + color + ".png", 10, 10, true, true);
        field.setGraphic(new ImageView(image));
    }
}

