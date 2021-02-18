package de.azubi.spiele.gui.fx.spielbrett;

import de.aal.spiel.core.Figur;
import de.aal.spiel.core.SpielManager;
import de.aal.spiel.core.Spieler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class GUISpielbrett {

    public Button btnField1;
    public Button btnField11;
    public Button btnField21;
    public Button btnField31;
    public Button redBase1;

    @FXML
    private List<Button> fields;

    @FXML
    private List<Button> houses;

    public Button btnPlay;
    private SpielManager spielManager = SpielManager.getInstance();
    public Label lblWuerfeln;
    public Label lblText;

    public int zahlGewuerfelt;
    public boolean hatgewuerfelt;
    public boolean gezogen;
    public int anzahlWuerfe = 0;

    public Spieler spielerDran;

    public void wuerfeln(ActionEvent actionEvent) {
        if (spielerDran.isDarfNochWuerfeln()) {
            int gewuerfelt = (int) (Math.random() * 6) + 1;
            lblWuerfeln.setText("Du hast eine " + gewuerfelt + " gewürfelt!");
            lblWuerfeln.setVisible(true);
            spielManager.setZahlGewuerfelt(gewuerfelt);
            zahlGewuerfelt = gewuerfelt;
            anzahlWuerfe++;
            if (gewuerfelt == 6) {
                if(spielerDran.getHaus().getEnthalteneFiguren().size()>0){
                    spielManager.figurZiehen(spielerDran, gewuerfelt);
                    setIcon(redBase1, "blank");
                    setIcon(btnField1, spielerDran.getFarbe());
                }
                spielerDran.setDarfNochWuerfeln(true);
            }
            else if (spielerDran.isDarfDreimalWuerfeln() && anzahlWuerfe < 3) {
                spielerDran.setDarfNochWuerfeln(true);
            }
            else {
                spielerDran.setDarfNochWuerfeln(false);
                hatgewuerfelt = true;
                if (anzahlWuerfe == 3) {
                    gezogen = true;
                }
            }
        } else {
            lblWuerfeln.setText("Du darfst nicht mehr wuerfeln!");
            lblWuerfeln.setVisible(true);
        }
    }

    public void spielenStarten(ActionEvent actionEvent) {
        setBaseIcons();
        btnPlay.setVisible(false);
        lblWuerfeln.setText("Starter: " + spielManager.getStarter().getName());
        lblWuerfeln.setVisible(true);
        spielerDran = spielManager.getStarter();
        lblText.setText("Das Spiel beginnt! Starter zieht zuerst! Farbe: " + spielerDran.getFarbe());
        lblText.setVisible(true);
    }


    public void zugBeenden() {
        if (hatgewuerfelt && gezogen) {
            spielerDran = spielManager.spielerAendern(spielerDran);
            lblWuerfeln.setText("");
            lblText.setText("Spieler dran: " + spielerDran.getName() + " Farbe: " + spielerDran.getFarbe());
            zahlGewuerfelt = 0;
            spielerDran.setDarfNochWuerfeln(true);
            anzahlWuerfe = 0;
        } else {
            lblText.setText("Noch nicht alle Funktionen ausgeführt!");
        }
    }

    @FXML
    public void getButtonPressedNumber(ActionEvent event) {
        Button btn = (Button) event.getSource();
        String fieldName = btn.getId();

        int feld = getFieldNumber(fieldName);

        if (checkIfFigureOnField(feld)) {
            setIcon(btn, "blank");

            /*if (fieldName.contains(spielerDran.getFarbe() + "Base") && zahlGewuerfelt == 6) {
                switch (spielerDran.getFarbe()) {
                    case "red":
                        setIcon(btnField1, "red");
                        break;
                    case "blue":
                        setIcon(btnField11, "blue");
                        break;
                    case "green":
                        setIcon(btnField21, "green");
                        break;
                    case "yellow":
                        setIcon(btnField31, "yellow");
                        break;
                }
            }*/ if (fieldName.contains("Goal") && fieldName.contains(spielerDran.getFarbe())) {
                spielManager.figurZiehen(spielerDran, zahlGewuerfelt);

            } else {
                spielManager.figurZiehen(spielerDran, zahlGewuerfelt);
                setIconOnNormalFields(feld);
            }
        }
    }

    public int getFieldNumber(String fieldName) {
        int fieldNumber = Integer.parseInt(fieldName.replaceAll("\\D+", ""));
        return fieldNumber;
    }

    public boolean checkIfFigureOnField(int feld) {
        for (Figur figur : spielerDran.getFiguren()) {
            if (figur.getFeld().getFeldnummer() == (feld)) {
                int indexFigur = spielerDran.getFiguren().indexOf(figur);
                spielManager.setIndexFigur(indexFigur);
                spielManager.figurZiehen(spielerDran, zahlGewuerfelt);
                gezogen = true;
                return true;
            }
        }
        return false;
    }


    private void setIconOnNormalFields(int feld) {
        String buttonNow = "btnField" + feld;
        String buttonNext = "btnField" + feld + spielManager.getZahlGewuerfelt();
        for (Button field : fields) {
            if (field.getId().equals(buttonNext)) {
                setIcon(field, spielerDran.getFarbe());
            }
            if (field.getId().equals(buttonNow)) {
                setIcon(field, "blank");
            }
        }
    }

    public void setBaseIcons() {
        for (Button field : houses) {
            setIcon(field, "black");
        }
    }

    public void setIcon(Button field, String color) {
        Image image = new Image("/figuren/" + color + ".png", 10, 10, true, true);
        field.setGraphic(new ImageView(image));
    }
}

