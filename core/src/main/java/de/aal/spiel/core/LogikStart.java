package de.aal.spiel.core;

public class LogikStart {

    private Spiellogik spiellogik;
    private SpielManager spielmanager;

    public LogikStart(Spiellogik spiellogik, SpielManager manager) {
        this.spiellogik = spiellogik;
        this.spielmanager = manager;
    }

    public void spielerErstellen(String name) {
        Spieler neuerSpieler = new Spieler(name);
        spiellogik.addSpieler(neuerSpieler);
    }

    public void spielStarten() {
        spielmanager.spielStarten();
    }
}
