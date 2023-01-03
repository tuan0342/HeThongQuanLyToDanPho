package controller;

import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class ThemNhanKhau {
    private static Scene preScene;
    public static Scene getPreScene() {
        return preScene;
    }
    public static void setPreScene(Scene preScene) {
        ThemNhanKhau.preScene = preScene;
    }
    public Button Back;
    public void back (Event event) {
        DBUtils.changeScene(getPreScene(), event);
    }
}
