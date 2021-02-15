import de.aal.spiel.core.LogikStart;
import de.aal.spiel.core.SpielManager;
import de.aal.spiel.core.Spiellogik;
import org.junit.*;

public class LogikStartTest{

    @Test
    public void spielerErstellen() {

        Spiellogik spiellogik = new Spiellogik();
        SpielManager spielManager = new SpielManager();
        LogikStart logikStart = new LogikStart(spiellogik, spielManager);

        logikStart.spielerErstellen("Hans");

        Assert.assertEquals(spiellogik.getSpielerList().size(), 1);

    }
}