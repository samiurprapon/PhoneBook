package controllers;

import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Contact;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static main.Main.openStage;

public class ShowContacts implements Initializable {

    public Button btn_delete;
    @FXML
    private Button btn_add_contact;
    @FXML
    private Button btn_edit;
    @FXML
    TableView<Contact> contactTableView;
    @FXML
    TableColumn<Contact, String> column_name;
    @FXML
    TableColumn<Contact, String> column_phone;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tableInit();
        btn_add_contact.setOnAction(event -> addContact());

        btn_edit.setOnAction(event -> updateContact());

        btn_delete.setOnAction(event -> {
            deleteContact();
        });
    }

    public void addContact()  {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/resources/create_contact.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (root != null) {
            openStage.setScene(new Scene(root));
        }
    }

    private void updateContact()  {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/resources/update_contact.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (root != null) {
            openStage.setScene(new Scene(root));
        }
    }

    private void deleteContact() {
    }



    @FXML
    private void tableInit(){
        column_name.setCellValueFactory(cellData -> cellData.getValue().getNameValue());
        column_phone.setCellValueFactory(cellData -> cellData.getValue().getPhoneValue());

        ObservableList<Contact> contactList = null;
        try {
            contactList = ShowContacts.getAllData();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        populateTable (contactList);

    }

    private static ObservableList<Contact> getAllData() throws ClassNotFoundException, SQLException {
        String sql = "SELECT name, phone FROM peoples";

        try{
            ResultSet rs = DatabaseConnection.dbExecute(sql);
            return getContactObjects(rs);
        } catch (SQLException e){
            System.out.println("Problem");
            e.printStackTrace();
            throw e;
        }


    }

    private static ObservableList<Contact> getContactObjects(ResultSet rs) throws SQLException {

        try {
            ObservableList<Contact> contactList = FXCollections.observableArrayList();

            while (rs.next()) {
                Contact contact = new Contact();

                contact.setNameproperty(rs.getString("name"));
                contact.setPhoneNumber(rs.getString("phone"));
                contactList.add(contact);

//                System.out.println(contact);
            }
            return contactList;
        } catch (SQLException e) {
            System.out.println("Problem 1");
            e.printStackTrace();
            throw e;
        }

    }

    private void populateTable(ObservableList<Contact> contactList) {
        contactTableView.setItems(contactList);
    }


}
