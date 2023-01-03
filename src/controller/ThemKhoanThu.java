package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ThemKhoanThu {
    public static Scene preScene;
    public static Scene getPreScene() {
        return preScene;
    }
    public static void setPreScene(Scene preScene) {
        ThemKhoanThu.preScene = preScene;
    }

    @FXML
    public Button Them;
    @FXML
    public Button Back;
    @FXML
    public TextField idKhoanThu;
    @FXML
    public TextField tenKhoanThu;
    @FXML
    public TextField soTien;
    @FXML
    public DatePicker ngayNop;

    public void them (Event event) {

        back(event);
    }

    public void back (Event event) {
        DBUtils.changeScene(getPreScene(), event);
    }
}
