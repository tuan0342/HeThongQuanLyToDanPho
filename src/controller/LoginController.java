package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


// Đây là lớp xử lý phương thức tại màn hình Login


public class LoginController implements Initializable {

    @FXML
    private Button button_login;

    @FXML
    private TextField tf_username;

    @FXML
    private PasswordField pwf_password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // chuyển sang màn hình chọn chức năng sau khi nhấn nút "Login"
        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.loginUser(event, tf_username.getText(), pwf_password.getText());
            }
        });
    }
}
