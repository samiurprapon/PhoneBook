package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;

public class Main extends Application {

    public static Stage openStage = null;
    InputStream is;
    private static Image appIcon = new Image("/resources/drawable/icon.png");

    @Override
    public void start(Stage primaryStage) throws Exception {

        openStage = primaryStage;
        openStage.getIcons().add(appIcon);

        Parent root = FXMLLoader.load(getClass().getResource("/resources/Contacts.fxml"));
        openStage.setTitle("Phone Book");
        openStage.setScene(new Scene(root));
        openStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
