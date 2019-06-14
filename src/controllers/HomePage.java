package controllers;

import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Contact;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomePage implements Initializable {

    @FXML
    public Button btn_delete;

    @FXML
    public Button btn_save;
    public Label popup;

    @FXML
    private TableView<Contact> tableView;
    public TextField mName;
    public TextField mPhone;

    @FXML
    TableColumn<Contact, String> column_username;
    @FXML
    TableColumn<Contact, String> column_phone;

    private DatabaseConnection connection = new DatabaseConnection();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tableInit();
        btn_save.setOnAction(event -> addContact());

        btn_delete.setOnAction(event -> deleteContact());
    }

    private void addContact()  {
        String name = mName.getText();
        String phone = mPhone.getText();

        if (popup.isVisible()) {
            popup.setVisible(false);
        }

        if(!name.isEmpty() && !phone.isEmpty()) {
            connection.createContact(name, phone);
            tableInit();

            mPhone.clear();
            mName.clear();
        } else {
            if(name.isEmpty() && phone.isEmpty()) {
                popup.setText("*Please Insert Information");
                popup.setVisible(true);
            }
            else if (name.isEmpty()) {
                popup.setText("*Please Insert Name");
                popup.setVisible(true);
            }
            else {
                popup.setText("*Please Insert Phone Number");
                popup.setVisible(true);
            }

        }
    }

    private void deleteContact() {

        if (tableView.getSelectionModel().getSelectedItem() == null) {
            System.out.println("No item selected");
        } else {
            column_username.setCellValueFactory(cellData -> cellData.getValue().getNameValue());
            column_phone.setCellValueFactory(cellData -> cellData.getValue().getPhoneValue());

            Contact contact = tableView.getSelectionModel().getSelectedItem();
            String phone  = contact.getPhoneValue().getValue();
            String sql = "DELETE FROM peoples WHERE phone = ?";

            tableView.getItems().removeAll(contact);


            connection.executeQuery(sql, phone);
        }

        tableView.refresh();
    }


    @FXML
    private void tableInit(){
        column_username.setCellValueFactory(cellData -> cellData.getValue().getNameValue());
        column_phone.setCellValueFactory(cellData -> cellData.getValue().getPhoneValue());

        ObservableList<Contact> contactList = null;
        try {
            contactList = HomePage.getAllData();
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
        tableView.setItems(contactList);
    }


}