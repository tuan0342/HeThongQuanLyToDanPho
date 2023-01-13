package controller;

import javafx.event.Event;
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
    public TextField idNhanKhau;
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
    private void showAlertErrorLogin(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Cánh báo");
        alert.setHeaderText("Nhập thông tin thất bại");
        alert.setContentText(text);

        ButtonType close = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(close);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == close) {
            alert.close();
        }
    }
    public void save (Event event) throws SQLException {
        NhanKhau nhanKhau = new NhanKhau();
        if (idNhanKhau.getText() == null || DBUtils.timNhanKhauTheoID(idNhanKhau.getText()) != null) {
            showAlertErrorLogin("Nhap sai");
            return;
        } else {
            nhanKhau.setIdNhanKhau(idNhanKhau.getText());
        }

        if (idHoKhau.getText() == null) {
            showAlertErrorLogin("Nhap thieu thong tin");
            return;
        } else {
            nhanKhau.setIdHoKhau(idHoKhau.getText());
        }

        if (biDanh.getText() == null) {
            showAlertErrorLogin("Nhap thieu thong tin");
            return;
        } else {
            nhanKhau.setBiDanh(biDanh.getText());
        }

        if (hoTen.getText() == null) {
            showAlertErrorLogin("Nhap thieu thong tin");
            return;
        } else {
            nhanKhau.setHoTen(hoTen.getText());
        }

        if (ngaySinh.getValue() == null) {
            showAlertErrorLogin("Nhap thieu thong tin");
            return;
        } else {
            nhanKhau.setNgaySinh(valueOf(ngaySinh.getValue()));
        }

        if (nguyenQuan.getText() == null) {
            showAlertErrorLogin("Nhap thieu thong tin");
            return;
        } else {
            nhanKhau.setNguyenQuan(nguyenQuan.getText());
        }
        if (danToc.getText() == null) {
            showAlertErrorLogin("Nhap thieu thong tin");
            return;
        } else {
            nhanKhau.setDanToc(danToc.getText());
        }

        if (ngheNghiep.getText() == null) {
            showAlertErrorLogin("Nhap thieu thong tin");
            return;
        } else {
            nhanKhau.setNgheNghiep(ngheNghiep.getText());
        }

        if (noiLamViec.getText() == null) {
            showAlertErrorLogin("Nhap thieu thong tin");
            return;
        } else {
            nhanKhau.setNoiLamViec(noiLamViec.getText());
        }

        if (ngayCap.getValue() == null) {
            showAlertErrorLogin("Nhap thieu thong tin");
            return;
        } else {
            nhanKhau.setNgayCap(valueOf(ngayCap.getValue()));
        }

        if (noiCap.getText() == null) {
            showAlertErrorLogin("Nhap thieu thong tin");
            return;
        } else {
            nhanKhau.setNoiCap(noiCap.getText());
        }

        if (diaChiThuongTru.getText() == null) {
            showAlertErrorLogin("Nhap thieu thong tin");
            return;
        } else {
            nhanKhau.setDiaChiThuongTru(diaChiThuongTru.getText());
        }

        if (quanHeChuHo.getText() == null) {
            showAlertErrorLogin("Nhap thieu thong tin");
            return;
        } else {
            nhanKhau.setQuanHeChuHo(quanHeChuHo.getText());
        }
        HoKhauStatic.themNhanKhau(nhanKhau, nhanKhau.getIdHoKhau());
        NhanKhauStatic.themNhanKhau(nhanKhau);
        DBUtils.changeScene(getPreScene(), event);
    }
}
