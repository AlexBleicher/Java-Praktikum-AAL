package de.azubi.spiele.gui.fx;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author tz
 */
public class Starter extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        URL resStart = getClass().getResource("/startseite/GUIStartseite.fxml");
        Parent rootStart = FXMLLoader.load(resStart);

        Scene sceneStart = new Scene(rootStart);

        primaryStage.setTitle("GUIStartseite");
        primaryStage.setScene(sceneStart);
        primaryStage.show();

    }
}
