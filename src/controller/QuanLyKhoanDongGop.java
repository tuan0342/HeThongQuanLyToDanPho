package controller;

import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class QuanLyKhoanDongGop {
    //Lien quan den Scene truoc Scene sau
    private static Scene preScene;
    private static Scene curScene;

    public static Scene getPreScene() {
        return preScene;
    }

    public static void setPreScene(Scene preScene) {
        QuanLyKhoanDongGop.preScene = preScene;
    }

    public static Scene getCurScene() {
        return curScene;
    }

    public static void setCurScene(Scene curScene) {
        QuanLyKhoanDongGop.curScene = curScene;
    }

    //Button quay lai
    public Button Back;

    public void Back (Event event) {
        DBUtils.changeScene(preScene, event);
    }
    //Cac thanh phan trong Scene nay

}
