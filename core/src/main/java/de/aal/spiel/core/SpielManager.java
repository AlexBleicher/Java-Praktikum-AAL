package de.aal.spiel.core;

import java.util.ArrayList;
import java.util.List;


public class SpielManager {

    private static final SpielManager instance = new SpielManager();
    //private LogikStart startLogik;
    private Spiellogik spiellogik = new Spiellogik();
    private Spielbrett spielbrett = new Spielbrett();
    private List<Figur> figurenListe = new ArrayList<>();
    private List<Haus> hausListe = new ArrayList<>();
    private boolean beendet = false;
    private Spieler starter;
    private int zahlGewuerfelt;
    private int indexFigur;


    public SpielManager() {

    }

    public void spielVorbereiten() {
        for (int i = 0; i < spiellogik.getSpielerList().size(); i++) {
            Spieler aktuellerSpieler = spiellogik.getSpielerList().get(i);
            String[] farben = {"red", "blue", "green", "yellow"};
            aktuellerSpieler.setFarbe(farben[i]);
            Haus neuesHaus = new Haus();
            neuesHaus.setSpieler(aktuellerSpieler);
            hausListe.add(neuesHaus);
            aktuellerSpieler.setHaus(neuesHaus);
            for (int k = 0; k < 4; k++) {
                Figur neueFigur = new Figur(aktuellerSpieler);
                aktuellerSpieler.addFigur(neueFigur);
                figurenListe.add(neueFigur);
                neuesHaus.addFigur(neueFigur);
            }
        }
        spielbrett.generiereFelder();
        int aktuellesFeld = 1;
        for (Spieler spieler : spiellogik.getSpielerList()) {
            spieler.setStartFeld(spielbrett.getFelder().get(aktuellesFeld));
            aktuellesFeld += 10;
            List<Feld> ziel = new ArrayList<>();
            for (int i = 1; i <= 4; i++) {
                Feld nFeld = new Feld(40 + i);
                ziel.add(nFeld);
            }
            spieler.setZiel(ziel);
        }
        starter = spiellogik.getSpielerList().get((int) (Math.random() * (spiellogik.getSpielerList().size() - 1)));
    }


    public Spieler spielerAendern(Spieler spielerDran) {
        if (spiellogik.getSpielerList().indexOf(spielerDran) == (spiellogik.getSpielerList().size() - 1)) {
            spielerDran = spiellogik.getSpielerList().get(0);
        } else {
            spielerDran = spiellogik.getSpielerList().get(spiellogik.getSpielerList().indexOf(spielerDran) + 1);
        }
        return spielerDran;
    }


    public void setBeendet(boolean beendet) {
        this.beendet = beendet;
    }

    public void figurZiehen(Spieler spielerDran, int zahlGewuerfelt) {
        if (zahlGewuerfelt == 6) {
            if (spielerDran.getHaus().getEnthalteneFiguren().size() != 0) {
                Figur figur = spielerDran.getHaus().getEnthalteneFiguren().get(0);
                figur.rauskommen();
                spielerDran.getHaus().getEnthalteneFiguren().remove(figur);
            }
        } else {
            Figur figur = spielerDran.getFiguren().get(indexFigur);
            if (figur.getGezogeneFelder() + zahlGewuerfelt < spielbrett.getFelder().size()) {
                Feld neuesFeld = figur.getFeld();
                if ((neuesFeld.getFeldnummer() + zahlGewuerfelt) >= spielbrett.getFelder().size()) {
                    neuesFeld = spielbrett.getFelder().get(neuesFeld.getFeldnummer() + zahlGewuerfelt - spielbrett.getFelder().size());
                } else {
                    neuesFeld = spielbrett.getFelder().get(neuesFeld.getFeldnummer() + zahlGewuerfelt);
                }
                figur.setFeld(neuesFeld);
                for (Figur andereFigur : figurenListe) {
                    if (andereFigur.getFeld().equals(neuesFeld)) {
                        andereFigur.geschlagen();
                        break;
                    }
                }
                figur.setGezogeneFelder(figur.getGezogeneFelder() + zahlGewuerfelt);
            } else if (spielbrett.getFelder().size() < figur.getGezogeneFelder() + zahlGewuerfelt && figur.getGezogeneFelder() + zahlGewuerfelt <= spielbrett.getFelder().size() + 4) {
                figur.getSpieler().setFigurenImZiel(figur.getSpieler().getFigurenImZiel() + 1);
                figur.setFeld(figur.getSpieler().getZiel().get(figur.getGezogeneFelder() + zahlGewuerfelt - 41));
                if (figur.getSpieler().getFigurenImZiel() == 4) {
                    setBeendet(true);
                }
            }
        }
    }


    public int wuerfeln() {
        int gewuerfelt = (int) (Math.random() * 6) + 1;
        return gewuerfelt;
    }

    public Spielbrett getSpielbrett() {
        return spielbrett;
    }

   /* public LogikStart getStartLogik() {
        return startLogik;
    }*/

    public Spiellogik getSpiellogik() {
        return spiellogik;
    }

    public int getZahlGewuerfelt() {
        return zahlGewuerfelt;
    }

    public void setZahlGewuerfelt(int zahlGewuerfelt) {
        this.zahlGewuerfelt = zahlGewuerfelt;
    }

    public void setIndexFigur(int indexFigur) {
        this.indexFigur = indexFigur;
    }

    public Spieler getStarter() {
        return starter;
    }

    public static SpielManager getInstance() {
        return instance;
    }
}

