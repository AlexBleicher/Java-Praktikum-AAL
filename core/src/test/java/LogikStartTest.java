import de.aal.spiel.core.SpielManager;
import de.aal.spiel.core.Spieler;
import org.junit.*;

public class LogikStartTest {

    @Test
    public void spielerErstellen() throws Exception {


        SpielManager spielManager = new SpielManager();

        spielManager.getSpiellogik().addSpieler(new Spieler("Hansi"));

        Assert.assertEquals(spielManager.getSpiellogik().getSpielerList().size(), 1);

    }

    @Test
    public void spielstarten() throws Exception {
        SpielManager spielManager = new SpielManager();

        spielManager.setZahlGewuerfelt(6);
        spielManager.getSpiellogik().addSpieler(new Spieler("Hans"));
        spielManager.getSpiellogik().addSpieler(new Spieler("Dieter"));
        spielManager.spielVorbereiten();

        Assert.assertEquals(spielManager.getSpielbrett().getFelder().size(), 40);
    }
}