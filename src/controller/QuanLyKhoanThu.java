package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class QuanLyKhoanThu {
    //Lien quan den Scene truoc Scene sau
    private static Scene preScene;
    private static Scene curScene;

    public static Scene getPreScene() {
        return preScene;
    }

    public static void setPreScene(Scene preScene) {
        QuanLyKhoanThu.preScene = preScene;
    }

    public static Scene getCurScene() {
        return curScene;
    }

    public static void setCurScene(Scene curScene) {
        QuanLyKhoanThu.curScene = curScene;
    }

    //Button quay lai
    public Button Back;

    public void Back (Event event) {
        DBUtils.changeScene(preScene, event);
    }
    //Cac thanh phan trong Scene nay
    @FXML
    public HBox Khung;
    @FXML
    public AnchorPane CacButton;
    @FXML
    public AnchorPane KhungThongTin;
    public Button ThemKhoanThu;
    public Button ThayDoiKhoanThu;
    public TableView DanhSach;

    public void Initialize () {

    }

    public void themKhoanThu (Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ThemKhoanThu.class.getResource("/view/fxml/Them_Khoan_Thu.fxml"));
        AnchorPane Them = fxmlLoader.load();
        Khung.getChildren().clear();
        Khung.getChildren().addAll(Them, KhungThongTin);
    }

}
