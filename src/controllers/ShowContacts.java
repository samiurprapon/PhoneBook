package controllers;

import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Contact;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static main.Main.openStage;

public class ShowContacts {

    ObservableList<Contact> contactList;

    @FXML
    TableView<Contact> contactTableView;

    @FXML
    TableColumn<Contact, String> column_name;

    @FXML
    TableColumn<Contact, String> column_phone;


    @FXML
    public void initialize() throws Exception{
        column_name.setCellValueFactory(cellData -> cellData.getValue().getNameValue());
        column_phone.setCellValueFactory(cellData -> cellData.getValue().getPhoneValue());

        ObservableList <Contact> contactList = ShowContacts.getAllData();
        populateTable (contactList);

    }


    public void addContact() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/CreateContact.fxml"));
        openStage.setScene(new Scene(root));
    }

    private static ObservableList<Contact> getAllData() throws ClassNotFoundException, SQLException {
        String sql = "SELECT name, phone FROM peoples GROUP BY phone";

        try{
            ResultSet rs = DatabaseConnection.dbExecute(sql);
            return getContactObjects(rs);
        } catch (SQLException e){
            System.err.println("Problem");
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
            System.err.println("Problem 1");
            e.printStackTrace();
            throw e;
        }

    }

    private void populateTable(ObservableList<Contact> contactList) {
        contactTableView.setItems(contactList);
    }
}
