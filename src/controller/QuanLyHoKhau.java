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
import model.*;

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
    public Button XemChiTiet;
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
        if (dsHoKhau.getSelectionModel().getSelectedItem() == null) {
            ShowAlert.showAlertError("Chọn lại đê", "Chọn sai");
            return;
        } else {
            HoKhau duocChon = dsHoKhau.getSelectionModel().getSelectedItem();
            if (duocChon.getSoLuongNhanKhau() <= 1) {
                ShowAlert.showAlertError("Chọn lại đê", "Chọn hộ khẩu có một đứa thì tách kiểu gì");
            } else {
                FXMLLoader fxmlLoader  = new FXMLLoader(TachHo.class.getResource("/view/fxml/TachHo.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root, 1000, 600);
                TachHo tachHo = fxmlLoader.getController();
                tachHo.setDsNhanKhau(duocChon.getIdHoKhau(), duocChon.getDiaChi(), duocChon);
                TachHo.setPreScene(getCurScene());
                tachHo.setHoKhauTableView(dsHoKhau);
                DBUtils.changeScene(scene, event);
            }
        }
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

    public void xoaHo (Event event) throws SQLException {
        if (ShowAlert.showAlertYN("Chắc chưa", "Xóa là mất đấy")) {
            HoKhau duocChon = dsHoKhau.getSelectionModel().getSelectedItem();
            String idHoKhau = duocChon.getIdHoKhau();
            DBUtils.XoaHoKhau(idHoKhau);
            NhanKhauStatic.xoaNhanKhau(idHoKhau);
            HoKhauStatic.getDsHoKhau().remove(duocChon);
            dsHoKhau.refresh();
            LichSuStatic.taoLichSu(duocChon.getIdHoKhau(), "Xóa Hộ", "Xóa hộ khẩu");
        }
    }

    public void XemChiTiet (Event event) throws IOException {
        if (dsHoKhau.getSelectionModel().getSelectedItem() != null) {
            HoKhau duocChon = dsHoKhau.getSelectionModel().getSelectedItem();
            String idHoKhau = duocChon.getIdHoKhau();
            FXMLLoader XemChiTiet = new FXMLLoader(QuanLyNhanKhau.class.getResource("/view/fxml/Quan_Ly_Nhan_Khau.fxml"));
            Scene scene = new Scene(XemChiTiet.load());
            QuanLyNhanKhau.setPreScene(this.curScene);
            QuanLyNhanKhau.setCurScene(scene);
            QuanLyNhanKhau quanLyNhanKhau = XemChiTiet.getController();
            quanLyNhanKhau.hienThiThongTinHoKhau(duocChon);
            DBUtils.changeScene(scene, event);
        }
    }
}
