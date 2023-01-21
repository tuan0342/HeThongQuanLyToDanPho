package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import model.HoKhauStatic;
import model.NhanKhau;
import model.NhanKhauStatic;
import org.w3c.dom.Text;

import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

import static java.lang.Integer.parseInt;
import static java.sql.Date.valueOf;

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

    public Button Save;
    @FXML
    public TextField idNhanKhau;
    @FXML
    public TextField idHoKhau;
    public TextField hoTen;
    public TextField biDanh;
    public DatePicker ngaySinh;
    public TextField nguyenQuan;
    public TextField danToc;
    public TextField ngheNghiep;
    public TextField noiLamViec;
    public TextField soCCCD;
    public DatePicker ngayCap;
    public TextField  noiCap;
    public TextField diaChiThuongTru;
    public TextField quanHeChuHo;
    public DatePicker TGDKTT;
    public void save (Event event) throws SQLException {
        NhanKhau nhanKhau = new NhanKhau();
        if (idNhanKhau.getText() == "" || DBUtils.timNhanKhauTheoID(idNhanKhau.getText()) != null) {
            ShowAlert.showAlertError("Nhập sai", "Nhập lại đê");
            return;
        } else {
            nhanKhau.setIdNhanKhau(idNhanKhau.getText());
        }

        if (idHoKhau.getText() == null) {
            ShowAlert.showAlertError("Nhập sai", "Nhập lại đê");
            return;
        } else {
            nhanKhau.setIdHoKhau(idHoKhau.getText());
        }

        if (biDanh.getText() == null) {
            ShowAlert.showAlertError("Nhập sai", "Nhập lại đê");
            return;
        } else {
            nhanKhau.setBiDanh(biDanh.getText());
        }

        if (hoTen.getText() == null) {
            ShowAlert.showAlertError("Nhập sai", "Nhập lại đê");
            return;
        } else {
            nhanKhau.setHoTen(hoTen.getText());
        }

        if (ngaySinh.getValue() == null) {
            ShowAlert.showAlertError("Nhập sai", "Nhập lại đê");
            return;
        } else {
            nhanKhau.setNgaySinh(valueOf(ngaySinh.getValue()));
        }

        if (nguyenQuan.getText() == null) {
            ShowAlert.showAlertError("Nhập sai", "Nhập lại đê");
            return;
        } else {
            nhanKhau.setNguyenQuan(nguyenQuan.getText());
        }
        if (danToc.getText() == null) {
            ShowAlert.showAlertError("Nhập sai", "Nhập lại đê");
            return;
        } else {
            nhanKhau.setDanToc(danToc.getText());
        }

        if (ngheNghiep.getText() == null) {
            ShowAlert.showAlertError("Nhập sai", "Nhập lại đê");
            return;
        } else {
            nhanKhau.setNgheNghiep(ngheNghiep.getText());
        }

        if (noiLamViec.getText() == null) {
            ShowAlert.showAlertError("Nhập sai", "Nhập lại đê");
            return;
        } else {
            nhanKhau.setNoiLamViec(noiLamViec.getText());
        }

        if (ngayCap.getValue() == null) {
            ShowAlert.showAlertError("Nhập sai", "Nhập lại đê");
            return;
        } else {
            nhanKhau.setNgayCap(valueOf(ngayCap.getValue()));
        }

        if (noiCap.getText() == null) {
            ShowAlert.showAlertError("Nhập sai", "Nhập lại đê");
            return;
        } else {
            nhanKhau.setNoiCap(noiCap.getText());
        }

        if (diaChiThuongTru.getText() == null) {
            ShowAlert.showAlertError("Nhập sai", "Nhập lại đê");
            return;
        } else {
            nhanKhau.setDiaChiThuongTru(diaChiThuongTru.getText());
        }

        if (quanHeChuHo.getText() == null) {
            ShowAlert.showAlertError("Nhập sai", "Nhập lại đê");
            return;
        } else {
            nhanKhau.setQuanHeChuHo(quanHeChuHo.getText());
        }
        HoKhauStatic.themNhanKhau(nhanKhau, nhanKhau.getIdHoKhau());
        DBUtils.changeScene(getPreScene(), event);
    }
}
