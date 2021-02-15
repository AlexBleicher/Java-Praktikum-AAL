package de.azubi.spiele.gui.fx;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
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

        URL res = getClass().getResource("GUIStartseite.fxml");
        Parent root = FXMLLoader.load(res);

        Scene scene = new Scene(root);

        primaryStage.setTitle("GUIStartseite");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


}
