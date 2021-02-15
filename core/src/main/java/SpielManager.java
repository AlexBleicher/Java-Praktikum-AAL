public class SpielManager {
    private LogikStart startLogik;
    private Spiellogik spiellogik = new Spiellogik();

    public SpielManager(){
        startLogik=new LogikStart(spiellogik);
    }


}
