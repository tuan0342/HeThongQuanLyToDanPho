package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class QuanLyHoKhau {
    //Lien quan den Scene truoc Scene sau
    private static Scene preScene;
    private static Scene curScene;
    public static Scene getPreScene() {
        return preScene;
    }
    public static void setPreScene(Scene preScene) {
        QuanLyHoKhau.preScene = preScene;
    }
    public static Scene getCurScene() {
        return curScene;
    }
    public static void setCurScene(Scene curScene) {
        QuanLyHoKhau.curScene = curScene;
    }
    //Button quay lai
    @FXML
    public Button Back;
    public void Back (Event event) {
        DBUtils.changeScene(preScene, event);
    }

    //Cac thanh phan khac trong Scene nay
    public HBox Khung;
    public AnchorPane CacButton;
    public AnchorPane KhungThongTin;

    public Button XoaHo;
    public Button TachHo;
    public Button ThayDoiChuHo;

    public void xoaHo (Event event) {

    }

    public void tachHo (Event event) {

    }

    public void thayDoiChuHo (Event event) {

    }

}
