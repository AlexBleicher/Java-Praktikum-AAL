public class LogikStart {

    private Spiellogik spiellogik;

    public LogikStart(Spiellogik spiellogik) {
        this.spiellogik = spiellogik;
    }

    public void spielerErstellen(String name) {
        Spieler neuerSpieler = new Spieler(name);
        spiellogik.addSpieler(neuerSpieler);
    }

    public void spielStarten() {

    }
}
