package de.azubi.spiele.gui.fx.spielbrett;

import de.aal.spiel.core.Feld;
import de.aal.spiel.core.Figur;
import de.aal.spiel.core.SpielManager;
import de.aal.spiel.core.Spieler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class GUISpielbrett {

    public Button btnField1;
    public Button btnField11;
    public Button btnField21;
    public Button btnField31;
    public Button redBase1;

    public HBox hBoxNamen;

    @FXML
    private List<Button> fields;

    @FXML
    private List<Button> houses;

    @FXML
    private List<Button> goals;

    public Button btnPlay;
    private SpielManager spielManager = SpielManager.getInstance();
    public Label lblWuerfeln;
    public Label lblText;

    public int zahlGewuerfelt;
    public boolean hatgewuerfelt;
    public boolean gezogen = false;
    public boolean ausHaus = false;
    public int anzahlWuerfe = 0;
    public int lastNumber = 0;

    public Spieler spielerDran;

    public void wuerfeln(ActionEvent actionEvent) {
        if (!spielManager.isBeendet()) {
            System.out.println(spielerDran.isDarfDreimalWuerfeln());
            if (spielerDran.isDarfNochWuerfeln()) {
                int gewuerfelt = (int) (Math.random() * 6) + 1;
                lblWuerfeln.setText("Du hast eine " + gewuerfelt + " gewürfelt!");
                lblWuerfeln.setVisible(true);
                spielManager.setZahlGewuerfelt(gewuerfelt);
                zahlGewuerfelt = gewuerfelt;
                anzahlWuerfe++;

                System.out.println(anzahlWuerfe);

                if (gewuerfelt == 6 && !spielManager.eigeneFigurBereitsAufFeld(spielerDran)) {
                    if (spielerDran.getHaus().getEnthalteneFiguren().size() > 0) {
                        spielManager.figurZiehen(spielerDran, gewuerfelt);
                        setIcon(removeFigureFromHouse(), "blank");
                        setIcon(getSpielerStart(), spielerDran.getFarbe());
                        ausHaus = true;
                        spielerDran.setDarfDreimalWuerfeln(false);
                        lastNumber = 6;
                    }
                    spielerDran.setDarfNochWuerfeln(true);
                } else if ((spielerDran.isDarfDreimalWuerfeln() && anzahlWuerfe < 3) || lastNumber == 6) {
                    spielerDran.setDarfNochWuerfeln(true);
                    ausHaus = false;
                    lastNumber = gewuerfelt;
                } else {
                    hatgewuerfelt = true;
                    ausHaus = false;
                    if (anzahlWuerfe >= 3) {
                        gezogen = true;
                        spielerDran.setDarfNochWuerfeln(false);
                    }
                }
            } else {
                lblWuerfeln.setText("Du darfst nicht mehr wuerfeln!");
                lblWuerfeln.setVisible(true);
                spielerDran.setDarfNochWuerfeln(false);
                hatgewuerfelt = true;
            }
        }
    }

    public void spielenStarten(ActionEvent actionEvent) {
        setBaseIcons();
        generateLegend();
        btnPlay.setVisible(false);
        lblWuerfeln.setVisible(true);
        spielerDran = spielManager.getStarter();
        lblText.setText("Das Spiel beginnt! " + spielerDran.getName() + " zieht zuerst!");
        lblText.setVisible(true);
    }

    public void zugBeenden() {
        if (hatgewuerfelt && gezogen) {
            spielerDran = spielManager.spielerAendern(spielerDran);
            lblWuerfeln.setText("");
            lblText.setText("Spieler dran: " + spielerDran.getName());
            zahlGewuerfelt = 0;
            spielerDran.setDarfNochWuerfeln(true);
            anzahlWuerfe = 0;
            hatgewuerfelt = false;
            gezogen = false;
        } else {
            lblText.setText("Noch nicht alle Funktionen ausgeführt!");
        }
    }

    public void generateLegend() {
        for (Spieler spieler : spielManager.getSpiellogik().getSpielerList()) {
            Color color = Color.web(spieler.getFarbe());
            Label name = new Label(spieler.getName());
            name.setTextFill(color);
            hBoxNamen.getChildren().add(name);
        }
    }

    @FXML
    public void getButtonPressedNumber(ActionEvent event) {
        Button btn = (Button) event.getSource();
        String fieldName = btn.getId();
        int feld = getFieldNumber(fieldName);
        if (checkIfFigureOnField(feld) && !gezogen && !ausHaus) {
            spielManager.figurZiehen(spielerDran, zahlGewuerfelt);
            setIcon(btn, "blank");
            setAndCheckIfInGoal();
            refillHouse();
            if (fieldName.contains(spielerDran.getFarbe() + "Goal")) {
                spielerDran.setDarfNochWuerfeln(false);
                gezogen = true;
            } else {
                setIconOnNormalFields(feld);
                spielerDran.setDarfNochWuerfeln(false);
                gezogen = true;
            }
        } else {
            System.out.println("I died here?");
        }
    }

    public Button getSpielerStart() {
        int startFeld = spielerDran.getStartFeld().getFeldnummer();
        String startButton = "btnField" + startFeld;
        for (Button field : fields) {
            if (field.getId().equals(startButton)) {
                return field;
            }
        }
        return null;
    }

    public int getFieldNumber(String fieldName) {
        int fieldNumber = Integer.parseInt(fieldName.replaceAll("\\D+", ""));
        return fieldNumber;
    }

    private Button removeFigureFromHouse() {
        List<Figur> enthalteneFiguren = spielerDran.getHaus().getEnthalteneFiguren();
        List<Button> figurenImHaus = new ArrayList<>();
        for (Button house : houses) {
            if (house.getId().contains(spielerDran.getFarbe())) {
                figurenImHaus.add(house);
            }
        }
        return figurenImHaus.get(enthalteneFiguren.size());
    }

    public boolean checkIfFigureOnField(int feld) {
        for (Figur figur : spielerDran.getFiguren()) {
            if (figur.getFeld().getFeldnummer() == (feld)) {
                int indexFigur = spielerDran.getFiguren().indexOf(figur);
                spielManager.setIndexFigur(indexFigur);
                return true;
            }
        }
        return false;
    }

    public void refillHouse() {
        List<Spieler> allPlayers = spielManager.getSpiellogik().getSpielerList();
        for (Spieler spieler : allPlayers) {
            int figurenImHaus = spieler.getHaus().getEnthalteneFiguren().size();
            for (int i = 0; i < figurenImHaus; i++) {
                String playerHouse = spieler.getFarbe() + "Base" + i;
                for (Button house : houses) {
                    if (house.getId().equals(playerHouse)) ;
                    setIcon(house, spielerDran.getFarbe());
                }
            }
        }
    }

    public void setAndCheckIfInGoal() {
        for (Feld feld : spielerDran.getZiel()) {
            for (Figur figur : spielerDran.getFiguren()) {
                if (figur.getFeld().getFeldnummer() == feld.getFeldnummer()) {
                    String btnGoal = spielerDran.getFarbe() + "Goal" + (feld.getFeldnummer() - 41);
                    for (Button goal : goals) {
                        if (goal.getId().equals(btnGoal)) {
                            setIcon(goal, "black");
                        }
                    }
                }
            }
        }
    }

    private void setIconOnNormalFields(int feld) {
        String buttonNext = "btnField" + (feld + zahlGewuerfelt);
        for (Button field : fields) {
            if (field.getId().equals(buttonNext)) {
                setIcon(field, spielerDran.getFarbe());
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

