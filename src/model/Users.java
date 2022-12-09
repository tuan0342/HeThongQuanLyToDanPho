package model;

import javafx.beans.property.SimpleStringProperty;

public class Users {
    private SimpleStringProperty chucVu;
    private SimpleStringProperty username;
    private SimpleStringProperty password;

    public Users() {
    }

    public Users(String username, String password) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
    }

    public Users(String chucVu, String username, String password) {
        this.chucVu = new SimpleStringProperty(chucVu);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
    }

    public String getChucVu() {
        return chucVu.get();
    }

    public SimpleStringProperty chucVuProperty() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu.set(chucVu);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    @Override
    public String toString() {
        return "Users{" +
                "chucVu=" + chucVu +
                ", username=" + username +
                ", password=" + password +
                '}';
    }
}
