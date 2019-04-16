package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Contact {
    private String phone;
    private String name;
    private String image;

    private StringProperty nameproperty;
    private  StringProperty phoneNumberProperty;

    public Contact() {
        // default constructor

        this.nameproperty = new SimpleStringProperty();
        this.phoneNumberProperty = new SimpleStringProperty();
    }

    public Contact(String phone, String name ) {
        this.phone = phone;
        this.name = name;
    }

    public Contact(String phone, String name, String image) {
        this.phone = phone;
        this.name = name;
        this.image = image;
    }

    public ObservableValue<String> getPhoneValue() {
        return phoneNumberProperty;
    }

    public ObservableValue<String> getNameValue() {
        return nameproperty;
    }

    public String getImage() {
        return image;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getNameproperty() {
        return nameproperty.get();
    }

    public StringProperty namepropertyProperty() {
        return nameproperty;
    }

    public void setNameproperty(String nameproperty) {
        this.nameproperty.set(nameproperty);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
