package de.azubi.spiele.gui.fx.spielbrett;

import javafx.event.ActionEvent;

public class GUISpielbrett {

    public int wuerfeln(ActionEvent actionEvent) {
        int gewuerfelt = (int) (Math.random() * 6);
        System.out.println("Gewuerfelte Zahl: " + gewuerfelt);
        return gewuerfelt;
    }
}
