package controllers;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static main.Main.isSplashLoaded;

public class MainStage implements Initializable {

    public AnchorPane root;

    public void initialize(URL location, ResourceBundle resources) {
        if (!isSplashLoaded) {
            loadSplashScreen();
        }
    }

    private void loadSplashScreen() {
        try {
            isSplashLoaded = true;

            AnchorPane pane = FXMLLoader.load(getClass().getResource(("/resources/layout/splash_screen.fxml")));
            root.getChildren().setAll(pane);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e) -> fadeOut.play());

            fadeOut.setOnFinished((e) -> {
                try {
                    AnchorPane parentContent = FXMLLoader.load(getClass().getResource("/resources/layout/home_page.fxml"));

                    root.getChildren().setAll(parentContent);

                } catch (IOException ex) {
                    // Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (IOException ex) {
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
