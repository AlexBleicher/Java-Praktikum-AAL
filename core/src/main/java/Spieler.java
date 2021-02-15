import java.util.ArrayList;
import java.util.List;

public class Spieler {

    private String name;
    private int figurenImZiel = 0;
    private boolean darfDreimalWuerfeln = false;
    private List<Figur> figuren=new ArrayList<>();

    public Spieler(String name) {
        this.name = name;
    }

    public void addFigur(Figur f){
        figuren.add(f);
    }
}
