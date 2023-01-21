package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.HoKhau;
import model.HoKhauStatic;
import model.NhanKhau;
import model.NhanKhauStatic;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class QuanLyHoKhau implements Initializable {
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
    public Button TachHoButton;
    public Button ThayDoiChuHo;
    public Button ThemHoKhauButton;
    @FXML
    public TableView<HoKhau> dsHoKhau;
    @FXML
    public TableColumn<HoKhau, String> idHoKhau;
    @FXML
    public TableColumn<HoKhau, String> tenChuHo;
    @FXML
    public TableColumn<HoKhau, String> diaChi;
    @FXML
    public TableColumn<HoKhau, Integer> soLuong;
    public void setTable () {
        try {
            DBUtils.loadDsHoKhau();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        idHoKhau.setCellValueFactory(new PropertyValueFactory<HoKhau, String>("idHoKhau"));
        tenChuHo.setCellValueFactory(new PropertyValueFactory<HoKhau, String>("tenChuHo"));
        diaChi.setCellValueFactory(new PropertyValueFactory<HoKhau, String>("diaChi"));
        soLuong.setCellValueFactory(new PropertyValueFactory<HoKhau, Integer>("soLuongNhanKhau"));
        dsHoKhau.setItems(HoKhauStatic.getDsHoKhau());
        for (HoKhau e: HoKhauStatic.getDsHoKhau()) {
            System.out.println(e.toString());
        }
        if (HoKhauStatic.getDsHoKhau().isEmpty()) {
            System.out.println("EMPTY");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTable();
    }
    public void xoaHo (Event event) {
    }
    public void themHoKhau (Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ThemHoKhau.class.getResource("/view/fxml/ThemHoKhau.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ThemHoKhau.setPreScene(getCurScene());
        ThemHoKhau.setCurScene(scene);
        ThemHoKhau controller = fxmlLoader.getController();
        HoKhauStatic.setThemHoKhau(controller);
        DBUtils.changeScene(scene, event);
        for (NhanKhau e: NhanKhauStatic.getDsNhanKhau()) {
            System.out.println(e.getHoTen() + " " + e.getQuanHeChuHo());
        }
    }

    public void tachHo (Event event) throws IOException {
        FXMLLoader fxmlLoader  = new FXMLLoader(TachHo.class.getResource("/view/fxml/TachHo.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1000, 600);
        TachHo.setPreScene(getCurScene());
        DBUtils.changeScene(scene, event);
    }

    public void thayDoiChuHo (Event event) throws IOException {
        if (dsHoKhau.getSelectionModel().getSelectedItem() != null &&
                dsHoKhau.getSelectionModel().getSelectedItem().getSoLuongNhanKhau() > 1) {
            FXMLLoader fxmlLoader  = new FXMLLoader(TachHo.class.getResource("/view/fxml/ThayDoiChuHo.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 1000, 600);
            controller.ThayDoiChuHo.setPreScene(getCurScene());
            ThayDoiChuHo thayDoiChuHo = fxmlLoader.getController();
            thayDoiChuHo.setHoKhauCanThayDoi(dsHoKhau.getSelectionModel().getSelectedItem());
            thayDoiChuHo.setDsHoKhau(dsHoKhau);
            DBUtils.changeScene(scene, event);
        } else {
            ShowAlert.showAlertError("Thất bại", "Chọn hộ khẩu cần thay đổi chủ hộ hoặc chọn hộ khẩu có nhiều hơn 1 nhân khẩu");
        }
    }
}
