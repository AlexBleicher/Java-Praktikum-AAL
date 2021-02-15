package de.aal.spiel.core;

import java.util.ArrayList;
import java.util.List;

public class SpielManager {

    private LogikStart startLogik;
    private Spiellogik spiellogik = new Spiellogik();
    private Spielbrett spielbrett = new Spielbrett();
    private List<Figur> figurenListe = new ArrayList<>();
    private List<Haus> hausListe = new ArrayList<>();
    private Spieler starter;

    public SpielManager() {
        startLogik = new LogikStart(spiellogik, this);
    }

    public void spielStarten(){
        for(int i=0; i<spiellogik.getSpielerList().size(); i++){
            Spieler aktuellerSpieler=spiellogik.getSpielerList().get(i);
            Haus neuesHaus=new Haus();
            neuesHaus.setSpieler(aktuellerSpieler);
            hausListe.add(neuesHaus);
            aktuellerSpieler.setHaus(neuesHaus);
            for(int j=0; j<4; j++){
                Figur neueFigur = new Figur(aktuellerSpieler);
                aktuellerSpieler.addFigur(neueFigur);
                neuesHaus.addFigur(neueFigur);
            }
        }
        spielbrett.generiereFelder();
        starter=spiellogik.getSpielerList().get((int) (Math.random()*(spiellogik.getSpielerList().size()-1))+1);
    }


}
