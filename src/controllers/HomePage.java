package controllers;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Contact;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePage implements Initializable {

    @FXML
    private Button btn_delete;

    @FXML
    private TableView tableView;

    @FXML
    private TableColumn<Contact, String> column_username;

    @FXML
    private TableColumn<Contact, String> column_phone;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btn_delete.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

            StringProperty selectedItem = tableView.getSelectionModel().getSelectedItem().getValue().link1;

            System.out.println("That is selected item : "+selectedItem);

            if (selectedItem.equals(null)) {

                System.out.println(" No item selected");


            } else {
                System.out.println("Index to be deleted:" + selectedItem.getValue());

                //Here was my database data retrieving and selectd
                // item deleted and then table refresh
                tableView.refresh();

                return;
            }

        });


    }




}
