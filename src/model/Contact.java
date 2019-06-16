package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Contact {
    private String phone;
    private String name;

    private StringProperty nameProperty;
    private  StringProperty phoneNumberProperty;

    // default constructor
    public Contact() {
        this.nameProperty = new SimpleStringProperty();
        this.phoneNumberProperty = new SimpleStringProperty();
    }

    public Contact(String phone, String name ) {
        this.phone = phone;
        this.name = name;
    }

    public ObservableValue<String> getPhoneValue() {
        return phoneNumberProperty;
    }

    public ObservableValue<String> getNameValue() {
        return nameProperty;
    }


    public void setPhoneNumber(String phoneNumberProperty) {
        this.phoneNumberProperty.set(phoneNumberProperty);
    }

    public void setNameProperty(String nameProperty) {
        this.nameProperty.set(nameProperty);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
