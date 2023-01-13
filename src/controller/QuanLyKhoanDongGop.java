package controller;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

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

    public Button ThemKhoanDongGop;
    public void ThemKhoanDongGop (Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ThemKhoanDongGop.class.getResource("/view/fxml/Them_Khoan_Thu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        controller.ThemKhoanDongGop.setPreScene(getCurScene());
        DBUtils.changeScene(scene, event);
    }

}
