import java.util.ArrayList;
import java.util.List;

public class SpielManager {

    private LogikStart startLogik;
    private Spiellogik spiellogik = new Spiellogik();
    private Spielbrett spielbrett = new Spielbrett();
    private List<Figur> figurenListe = new ArrayList<>();
    public SpielManager() {
        startLogik = new LogikStart(spiellogik, this);
    }

    public void spielStarten(){
        for(int i=0; i<spiellogik.getSpielerList().size(); i++){
            for(int j=0; j<4; j++){
                Figur neueFigur = new Figur(spiellogik.getSpielerList().get(i));
                spiellogik.getSpielerList().get(i).addFigur(neueFigur);
            }
        }

    }


}
