import org.junit.*;

public class LogikStartTest{

    @Test
    public void spielerErstellen() {

        Spiellogik spiellogik = new Spiellogik();
        LogikStart logikStart = new LogikStart(spiellogik);

        logikStart.spielerErstellen("Hans");

        Assert.assertEquals(spiellogik.getSpielerList().size(), 1);

    }
}