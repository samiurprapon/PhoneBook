package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private static Image appIcon;
    public static boolean isSplashLoaded = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        appIcon = new Image("/resources/drawable/ic_confused.png");

        Parent root = FXMLLoader.load(getClass().getResource("/resources/layout/main_stage.fxml"));
        primaryStage.getIcons().add(appIcon);
        primaryStage.setTitle("Phone Book");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }


}
