package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;


// Đây là lớp xử lý các sự kiện tại màn hình lựa chọn chức năng


public class SelectManagementController implements Initializable {

    @FXML
    private Button button_back_login;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // quay về màn hình đăng nhập
        button_back_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // bấm và back sẽ chuyển sang màn hình đăng nhập
                DBUtils.changeScene(event, "/view/fxml/logged-in.fxml", "Log in!", null);
            }
        });
    }
}
