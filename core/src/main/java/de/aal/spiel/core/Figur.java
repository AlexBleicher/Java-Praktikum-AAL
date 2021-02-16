package de.aal.spiel.core;

public class Figur {

    public Spieler spieler;
    private Feld feld;
    public int gezogeneFelder;

    public Figur(Spieler spieler) {
        this.spieler = spieler;
    }

    public void setFeld(Feld feld) {
        this.feld = feld;
    }

    public Feld getFeld() {
        return feld;
    }

    public void rauskommen() {
        feld = spieler.getStartFeld();
        gezogeneFelder = 1;
    }

    public int getGezogeneFelder() {
        return gezogeneFelder;
    }

    public void setGezogeneFelder(int gezogeneFelder) {
        this.gezogeneFelder = gezogeneFelder;
    }

    public Spieler getSpieler() {
        return spieler;
    }

    public void geschlagen() {
        gezogeneFelder = 0;
        feld = null;
        spieler.getHaus().addFigur(this);
    }
}
