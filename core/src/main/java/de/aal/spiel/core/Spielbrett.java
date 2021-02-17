package de.aal.spiel.core;

import java.util.ArrayList;
import java.util.List;

public class Spielbrett {

    private List<Feld> felder = new ArrayList<>();
    private List<Haus> haeuser = new ArrayList<>();

    public void generiereFelder() {
        for (int i = 1; i <= 40; i++) {
            Feld currentField = new Feld(i + 1);
            felder.add(currentField);
        }
    }

    public List<Feld> getFelder() {
        return felder;
    }
}
