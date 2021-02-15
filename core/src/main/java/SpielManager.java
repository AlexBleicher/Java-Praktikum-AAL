public class SpielManager {
    private LogikStart startLogik;
    private Spiellogik spiellogik = new Spiellogik();
    private Spielbrett spielbrett = new Spielbrett();

    public SpielManager() {
        startLogik = new LogikStart(spiellogik);
    }


}
