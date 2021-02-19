package de.aal.spiel.core;

import java.util.ArrayList;
import java.util.List;


public class SpielManager {

    private static final SpielManager instance = new SpielManager();
    private Spiellogik spiellogik = new Spiellogik();
    private Spielbrett spielbrett = new Spielbrett();
    private List<Figur> figurenListe = new ArrayList<>();
    private List<Haus> hausListe = new ArrayList<>();
    private boolean beendet = false;
    private Spieler starter;
    private int zahlGewuerfelt;
    private int indexFigur;
    private boolean figurGeschlagen = false;

    private Figur andereFigur;


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
        int aktuellesFeld = 0;
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

    public boolean isBeendet() {
        return beendet;
    }

    public void setBeendet(boolean beendet) {
        this.beendet = beendet;
    }

    public boolean isFigurGeschlagen() {
        return figurGeschlagen;
    }

    public void setFigurGeschlagen(boolean figurGeschlagen) {
        this.figurGeschlagen = figurGeschlagen;
    }

    public Figur getAndereFigur() {
        return andereFigur;
    }

    public void setAndereFigur(Figur andereFigur) {
        this.andereFigur = andereFigur;
    }

    public void figurZiehen(Spieler spielerDran, int zahlGewuerfelt) {
        if (kannRausziehen(spielerDran)) {
            if (hatnochFigurenImHaus(spielerDran)) {
                Figur figur = spielerDran.getHaus().getEnthalteneFiguren().get(0);
                figur.rauskommen();
                spielerDran.getHaus().getEnthalteneFiguren().remove(figur);
                for (Figur andereFigurAusListe : figurenListe) {
                    andereFigur = andereFigurAusListe;
                    if (wirdGeschlagen(andereFigurAusListe, figur, spielerDran.getStartFeld())) {
                        andereFigurAusListe.geschlagen();
                        figurGeschlagen = true;
                        break;
                    }
                }
            }
        } else {
            Figur figur = spielerDran.getFiguren().get(indexFigur);

            if (istNochKeineRundeGelaufen(figur)) {

                Feld neuesFeld = figur.getFeld();

                int feldNummer = neuesFeld.getFeldnummer() + zahlGewuerfelt;

                if (kommtueberRotenStart(feldNummer)) {
                    feldNummer = feldNummer % spielbrett.getFelder().size();
                }
                if(feldNummer==0){
                    feldNummer = 1;
                }
                neuesFeld = spielbrett.getFelder().get(feldNummer - 1);
                figur.setFeld(neuesFeld);

                for (Figur andereFigurAusListe : figurenListe) {
                    andereFigur = andereFigurAusListe;
                    if (wirdGeschlagen(andereFigurAusListe, figur, neuesFeld)) {
                        andereFigurAusListe.geschlagen();
                        figurGeschlagen = true;
                        break;
                    }
                }
                figur.setGezogeneFelder(figur.getGezogeneFelder() + zahlGewuerfelt);
            } else if (kommtInsZiel(figur)) {
                figur.getSpieler().setFigurenImZiel(figur.getSpieler().getFigurenImZiel() + 1);
                figur.setFeld(figur.getSpieler().getZiel().get(figur.getGezogeneFelder() + zahlGewuerfelt));
                if (figur.getSpieler().getFigurenImZiel() == figur.getSpieler().getFiguren().size()) {
                    setBeendet(true);
                }
            }
        }
    }

    public boolean eigeneFigurBereitsAufFeld(Spieler spielerDran) {
        for (Figur figur : spielerDran.getFiguren()) {
            if (figur.getFeld().equals(spielerDran.getStartFeld())) {
                return true;
            }
        }
        return false;
    }

    public Spielbrett getSpielbrett() {
        return spielbrett;
    }

    public Spiellogik getSpiellogik() {
        return spiellogik;
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

    public boolean kannRausziehen(Spieler spielerDran) {
        return (zahlGewuerfelt == 6 && !eigeneFigurBereitsAufFeld(spielerDran));
    }

    public boolean hatnochFigurenImHaus(Spieler spielerDran) {
        return (spielerDran.getHaus().getEnthalteneFiguren().size() != 0);
    }

    public boolean istNochKeineRundeGelaufen(Figur figur) {
        return (figur.getGezogeneFelder() + zahlGewuerfelt < spielbrett.getFelder().size());
    }

    public boolean kommtueberRotenStart(int feldNummer) {
        return (feldNummer >= spielbrett.getFelder().size());
    }

    public boolean wirdGeschlagen(Figur andereFigur, Figur figur, Feld neuesFeld) {
        return (andereFigur.getFeld().equals(neuesFeld) && andereFigur != figur);
    }

    public boolean kommtInsZiel(Figur figur) {
        return (spielbrett.getFelder().size() < figur.getGezogeneFelder() + zahlGewuerfelt && figur.getGezogeneFelder() + zahlGewuerfelt <= spielbrett.getFelder().size() + figur.getSpieler().getFiguren().size());
    }
}

