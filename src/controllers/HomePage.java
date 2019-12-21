package controllers;

import database.DatabaseConnection;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
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
    public Button btn_update;
    public AnchorPane insert_pane;

    @FXML
    private TableView<Contact> tableView;
    public TextField mName;
    public TextField mPhone;

    Contact contact;

    @FXML
    TableColumn<Contact, String> column_username;
    @FXML
    TableColumn<Contact, String> column_phone;

    private DatabaseConnection connection;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        connection = new DatabaseConnection();
        tableInit();

        btn_save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addContact();
            }
        });

        btn_delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteContact();
            }
        });
        btn_update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateContact();
            }
        });

        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() > 0) {
                    onEdit();
                }
            }
        });

    }

    public void onEdit() {
        // check the table's selected item and get selected item
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            column_username.setCellValueFactory(cellData -> cellData.getValue().getNameValue());
            column_phone.setCellValueFactory(cellData -> cellData.getValue().getPhoneValue());

            Contact contact = tableView.getSelectionModel().getSelectedItem();
            mPhone.setText(contact.getPhoneValue().getValue());
            mName.setText(contact.getNameValue().getValue());
        }
    }

    private void addContact()  {
        String name = mName.getText();
        String phone = mPhone.getText();

        if (popup.isVisible()) {
            popup.setVisible(false);
        }

        if(!name.isEmpty() && !phone.isEmpty()) {
            contact = new Contact(name, phone);

            connection.createContact(contact);
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

            tableView.getItems().removeAll(contact);

            mPhone.clear();
            mName.clear();

            String sql = "DELETE FROM peoples WHERE phone = ?";
            connection.executeQuery(sql, phone);

            tableInit();
        }


    }

    private void updateContact() {

        if (tableView.getSelectionModel().getSelectedItem() == null) {
            System.out.println("No item selected");
        } else {
            String name = mName.getText();
            String phone = mPhone.getText();

            mPhone.clear();
            mName.clear();

            contact = new Contact(name, phone);

            connection.updateContact(contact);

        }
        tableInit();

    }


    @FXML
    private void tableInit(){
        column_username.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Contact, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Contact, String> cellData) {
                return cellData.getValue().getNameValue();
            }
        });
        column_phone.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Contact, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Contact, String> cellData) {
                return cellData.getValue().getPhoneValue();
            }
        });

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

                contact.setNameProperty(rs.getString("name"));
                contact.setPhoneNumber(rs.getString("phone"));
                contactList.add(contact);
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