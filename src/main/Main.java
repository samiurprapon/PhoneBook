package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private static Image appIcon = new Image("/resources/drawable/icon.png");

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.getIcons().add(appIcon);

        Parent root = FXMLLoader.load(getClass().getResource("/resources/home_page.fxml"));
        primaryStage.setTitle("Phone Book");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
