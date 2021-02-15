package de.aal.spiel.core;

import java.util.ArrayList;
import java.util.List;

public class Spiellogik {

    private List<Spieler> spielerList = new ArrayList<>();

    public void addSpieler(Spieler p) {
        if (spielerList.size() < 4) {
            spielerList.add(p);
        } else {
            System.out.println("Fehler!");
        }
    }

    public List<Spieler> getSpielerList() {
        return spielerList;
    }
}
