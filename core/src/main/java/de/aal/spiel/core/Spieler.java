package de.aal.spiel.core;

import java.util.ArrayList;
import java.util.List;

public class Spieler {

    private String name;
    private int figurenImZiel = 0;
    private boolean darfDreimalWuerfeln = true;
    private List<Figur> figuren = new ArrayList<>();
    private Haus haus;
    private Feld startFeld = new Feld(0); //Platzhalter
    public List<Feld> ziel = new ArrayList<>();
    public String farbe;

    public Spieler(String name) {
        this.name = name;
    }

    public void addFigur(Figur f) {
        figuren.add(f);
    }

    public void setHaus(Haus haus) {
        this.haus = haus;
    }

    public boolean isDarfDreimalWuerfeln() {
        return darfDreimalWuerfeln;
    }

    public List<Figur> getFiguren() {
        return figuren;
    }

    public Haus getHaus() {
        return haus;
    }

    public void setStartFeld(Feld startFeld) {
        this.startFeld = startFeld;
    }

    public Feld getStartFeld() {
        return startFeld;
    }

    public void setFigurenImZiel(int figurenImZiel) {
        this.figurenImZiel = figurenImZiel;
    }

    public int getFigurenImZiel() {
        return figurenImZiel;
    }

    public List<Feld> getZiel() {
        return ziel;
    }

    public void setZiel(List<Feld> ziel) {
        this.ziel = ziel;
    }

    public String getName() {
        return name;
    }

    public String getFarbe() {
        return farbe;
    }

    public void setFarbe(String farbe) {
        this.farbe = farbe;
    }
}
