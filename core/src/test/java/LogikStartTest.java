import de.aal.spiel.core.LogikStart;
import de.aal.spiel.core.SpielManager;
import de.aal.spiel.core.Spiellogik;
import org.junit.*;

public class LogikStartTest{

    @Test
    public void spielerErstellen() {


        SpielManager spielManager = new SpielManager();

        spielManager.getStartLogik().spielerErstellen("Hans");

        Assert.assertEquals(spielManager.getSpiellogik().getSpielerList().size(), 1);

    }
    @Test
    public void spielstarten(){
        SpielManager spielManager = new SpielManager();

        spielManager.getStartLogik().spielerErstellen("Hans");
        spielManager.getStartLogik().spielerErstellen("Dieter");
        spielManager.getStartLogik().spielStarten();

        Assert.assertEquals(spielManager.getSpielbrett().getFelder().size(), 40);
    }
}