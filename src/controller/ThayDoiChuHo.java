package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class ThayDoiChuHo {
    private static Scene preScene;
    public static Scene getPreScene() { return preScene; }
    public static void setPreScene(Scene preScene) {
        ThayDoiChuHo.preScene = preScene;
    }
    @FXML
    public Button Back;
    public void Back (Event event) {
        DBUtils.changeScene(getPreScene(), event);
    }
}
