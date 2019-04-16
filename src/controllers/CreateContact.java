package controllers;

import database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

import static main.Main.openStage;

public class CreateContact {

    @FXML
    TextField mName = new TextField();
    @FXML
    TextField mPhone;

    @FXML
    Button mBack;

    DatabaseConnection connection = new DatabaseConnection();


    @FXML
    private void saveToDatabase() {
        System.out.println("Clicked");
        String name = mName.getText();

        String phone = mPhone.getText();

        connection.createContact(name, phone);

    }

    public void backToHome() {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/resources/Contacts.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (root != null) {
            openStage.setScene(new Scene(root));
        }

    }
}
