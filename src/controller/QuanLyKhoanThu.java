package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.KhoanThu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QuanLyKhoanThu implements Initializable {
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

    @FXML
    public Button Them;
    @FXML
    public Button Return;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void setDanhSach () {

    }

    public void themKhoanThu (Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ThemKhoanThu.class.getResource("/view/fxml/Them_Khoan_Thu.fxml"));
        AnchorPane Them = fxmlLoader.load();
        Them.setTranslateX(300);
        Them.setTranslateY(0);
        controller.ThemKhoanThu.setPreScene(this.curScene);
        Scene scene = new Scene(Them, 1000, 600);
        scene.setFill(Color.GRAY);
        DBUtils.changeScene(scene, event);
    }


}
