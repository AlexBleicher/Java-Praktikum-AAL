import java.util.ArrayList;
import java.util.List;

public class SpielManager {

    private LogikStart startLogik;
    private Spiellogik spiellogik = new Spiellogik();
    private Spielbrett spielbrett = new Spielbrett();
    private List<Figur> figurenListe = new ArrayList<>();
    private List<Haus> hausListe = new ArrayList<>();

    public SpielManager() {
        startLogik = new LogikStart(spiellogik, this);
    }

    public void spielStarten(){
        for(int i=0; i<spiellogik.getSpielerList().size(); i++){
            Haus neuesHaus=new Haus();
            neuesHaus.setSpieler(spiellogik.getSpielerList().get(i));
            hausListe.add(neuesHaus);
            for(int j=0; j<4; j++){
                Figur neueFigur = new Figur(spiellogik.getSpielerList().get(i));
                spiellogik.getSpielerList().get(i).addFigur(neueFigur);
                neuesHaus.addFigur(neueFigur);
            }
        }

    }


}
