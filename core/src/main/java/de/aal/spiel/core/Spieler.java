package de.aal.spiel.core;

import java.util.ArrayList;
import java.util.List;

public class Spieler {

    private String name;
    private int figurenImZiel = 0;
    private boolean darfDreimalWuerfeln = false;
    private List<Figur> figuren=new ArrayList<>();
    private Haus haus;

    public Spieler(String name) {
        this.name = name;
    }

    public void addFigur(Figur f){
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
}
