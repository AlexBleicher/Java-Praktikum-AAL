package de.aal.spiel.core;

import java.util.ArrayList;
import java.util.List;

public class SpielManager {

    private LogikStart startLogik;
    private Spiellogik spiellogik = new Spiellogik();
    private Spielbrett spielbrett = new Spielbrett();
    private List<Figur> figurenListe = new ArrayList<>();
    private List<Haus> hausListe = new ArrayList<>();
    private boolean beendet=false;
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
        spielen(starter);
    }
    public void spielen(Spieler starter){
        Spieler spielerDran = starter;
        while(!beendet){
            spielzug(spielerDran);
            if(spiellogik.getSpielerList().indexOf(spielerDran)==(spiellogik.getSpielerList().size()-1)){
                spielerDran=spiellogik.getSpielerList().get(0);
            }
            else{
                spielerDran=spiellogik.getSpielerList().get(spiellogik.getSpielerList().indexOf(spielerDran)+1);
            }
        }
    }

    public void spielzug(Spieler spielerDran){
        int zahlGewuerfelt=0;
        Figur figurZiehen;
        if(spielerDran.isDarfDreimalWuerfeln()){
            for(int i=0; i<3; i++){
                zahlGewuerfelt = 1 ;//Platzhalter später mit GUI verknüpft
                if(zahlGewuerfelt==6){
                    break;
                }
            }
        }
        else{
            zahlGewuerfelt=2; //Platzhalter später mit GUI verknüpft
        }
        figurZiehen=spielerDran.getFiguren().get(0);
        Feld neuesFeld= figurZiehen.getFeld();
        if(neuesFeld.getFeldnummer()+zahlGewuerfelt>=spielbrett.getFelder().size()){
            neuesFeld=spielbrett.getFelder().get(neuesFeld.getFeldnummer()+zahlGewuerfelt-spielbrett.getFelder().size());
        }
        else{
            neuesFeld=spielbrett.getFelder().get(neuesFeld.getFeldnummer()+zahlGewuerfelt);
        }
        figurZiehen.setFeld(neuesFeld);
    }

    public void setBeendet(boolean beendet) {
        this.beendet = beendet;
    }
}
