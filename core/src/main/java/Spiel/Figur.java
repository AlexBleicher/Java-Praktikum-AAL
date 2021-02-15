package Spiel;

import Spiel.Feld;

public class Figur {

    public Spieler spieler;
    private Feld feld;

    public Figur(Spieler spieler) {
        this.spieler = spieler;
    }

    public void setFeld(Feld feld) {
        this.feld = feld;
    }
}
