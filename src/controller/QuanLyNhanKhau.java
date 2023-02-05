package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.NhanKhau;
import model.NhanKhauStatic;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class QuanLyNhanKhau implements Initializable {
    //Lien quan den Scene truoc Scene sau
    private static Scene preScene;
    private static Scene curScene;
    public static Scene getPreScene() {
        return preScene;
    }
    public static void setPreScene(Scene preScene) {
        QuanLyNhanKhau.preScene = preScene;
    }
    public static Scene getCurScene() {
        return curScene;
    }
    public static void setCurScene(Scene curScene) {
        QuanLyNhanKhau.curScene = curScene;
    }
    //Button quay lai
    public Button Back;
    public void Back (Event event) {
        DBUtils.changeScene(preScene, event);
    }
    //
    public Button Them;
    public void Them (Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ThemNhanKhau.class.getResource("/view/fxml/Them_Nhan_Khau.fxml"));
        AnchorPane them = fxmlLoader.load();
        Scene scene = new Scene(them, 1000, 600);
        controller.ThemNhanKhau.setPreScene(getCurScene());
        DBUtils.changeScene(scene, event);
    }
    @FXML
    TableView dsNhanKhau;
    @FXML
    TableColumn <NhanKhau, String> idHoKhau;
    @FXML
    TableColumn <NhanKhau, String> idNhanKhau;
    @FXML
    TableColumn <NhanKhau, String> hoTen;
    @FXML
    TableColumn <NhanKhau, Date> ngaySinh;
    @FXML
    TableColumn <NhanKhau, Integer> soCCCD;
    @FXML
    TableColumn <NhanKhau, String> ngheNghiep;
    @FXML
    TableColumn <NhanKhau, String> gioiTinh;
    public void setdsNhanKhau () {
        idHoKhau.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("idHoKhau"));
        idNhanKhau.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("idNhanKhau"));
        hoTen.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>( "hoTen"));
        gioiTinh.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("gioiTinh"));
        ngaySinh.setCellValueFactory(new PropertyValueFactory<NhanKhau, Date>("ngaySinh"));
        soCCCD.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("soCCCD"));
        ngheNghiep.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("ngheNghiep"));
        dsNhanKhau.setItems(NhanKhauStatic.getDsNhanKhau());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DBUtils.loadDsNhanKhau();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setdsNhanKhau();
    }
}
