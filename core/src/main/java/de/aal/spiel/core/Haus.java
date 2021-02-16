package de.aal.spiel.core;

import java.util.ArrayList;
import java.util.List;

public class Haus {

    private Spieler spieler;
    private Feld startFeld;
    private List<Figur> enthalteneFiguren = new ArrayList<>();

    public void setSpieler(Spieler spieler) {
        this.spieler = spieler;
    }

    public void addFigur(Figur f){
        enthalteneFiguren.add(f);
    }

    public List<Figur> getEnthalteneFiguren() {
        return enthalteneFiguren;
    }
}
