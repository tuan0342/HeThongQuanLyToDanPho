package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ThemKhoanDongGop {
    public static Scene preScene;
    public static Scene getPreScene() {
        return preScene;
    }
    public static void setPreScene(Scene preScene) {
        ThemKhoanDongGop.preScene = preScene;
    }

    public void them (Event event) {
        back(event);
    }
    public Button Back;

    public void back (Event event) {
        DBUtils.changeScene(getPreScene(), event);
    }
}
