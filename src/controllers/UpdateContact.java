package controllers;

import database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

import static main.Main.openStage;

public class UpdateContact {

    private DatabaseConnection connection = new DatabaseConnection();

    @FXML
    public TextField tf_name;

    @FXML
    public Button btn_update;
    @FXML
    public TextField tf_phone;

    public void updateContact() {
        String name = tf_name.getText();
        String phone = tf_phone.getText();

        if (name != null && phone != null) {

            connection.updateContact(phone, name);
        }

    }

    public void backToHome() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/resources/show_contacts.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (root != null) {
            openStage.setScene(new Scene(root));
        }

    }

}
