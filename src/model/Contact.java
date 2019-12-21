package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Contact {
    private String phone;
    private String name;

    private StringProperty nameProperty;
    private  StringProperty phoneNumberProperty;

    public Contact() {
        // default constructor

        this.nameProperty = new SimpleStringProperty();
        this.phoneNumberProperty = new SimpleStringProperty();
    }

    public Contact(String name, String phone ) {
        this.phone = phone;
        this.name = name;
    }

    public ObservableValue<String> getPhoneValue() {
        return phoneNumberProperty;
    }

    public ObservableValue<String> getNameValue() {
        return nameProperty;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public void setPhoneNumber(String phoneNumberProperty) {
        this.phoneNumberProperty.set(phoneNumberProperty);
    }

    public StringProperty getPhoneNumberProperty() {
        return phoneNumberProperty;
    }

    public String getNameProperty() {
        return nameProperty.get();
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
