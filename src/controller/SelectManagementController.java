package controller;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;


// Đây là lớp xử lý các sự kiện tại màn hình lựa chọn chức năng


public class SelectManagementController implements Initializable {

    @FXML
    private static Scene preScene;
    private static Scene curScene;

    public static void setPreScene(Scene preScene) {
        SelectManagementController.preScene = preScene;
    }

    public static void setCurScene(Scene curScene) {
        SelectManagementController.curScene = curScene;
    }
    public Scene QuanLyHoKhau;
    public Scene QuanLyNhanKhau;
    private QuanLyNhanKhau quanLyNhanKhau;
    private QuanLyHoKhau quanLyHoKhau;
    public Scene QuanLyKhoanThu;
    public Scene QuanLyKhoanDongGop;
    public Scene QuanLyTamTruTamVang;
    public Scene XemThongKe;

    public Button QuanLyHoKhauButton;
    public Button QuanLyNhanKhauButton;
    public Button QuanLyKhoanThuButton;
    public Button QuanLyKhoanDongGopButton;
    @FXML
    public Button QuanLyTamTruTamVangButton;

    @FXML
    public Button XemLichSu;
    @FXML
    public Button XemThongKeButton;


    public Button Back;

    private void setMenu () {

        FXMLLoader QLHK = new FXMLLoader(QuanLyHoKhau.class.getResource("/view/fxml/QuanLyHoKhau.fxml"));
        try {
            this.QuanLyHoKhau = new Scene(QLHK.load());
            this.quanLyHoKhau = QLHK.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FXMLLoader QLNK = new FXMLLoader(QuanLyNhanKhau.class.getResource("/view/fxml/Quan_Ly_Nhan_Khau.fxml"));
        try {
            this.QuanLyNhanKhau = new Scene(QLNK.load());
            this.quanLyNhanKhau = QLNK.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FXMLLoader QLKT = new FXMLLoader(QuanLyKhoanThu.class.getResource("/view/fxml/Quan_Ly_Khoan_Thu.fxml"));
        try {
            this.QuanLyKhoanThu = new Scene(QLKT.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FXMLLoader QLKDG = new FXMLLoader(QuanLyKhoanDongGop.class.getResource("/view/fxml/Quan_Ly_Khoan_Dong_Gop.fxml"));
        try {
            this.QuanLyKhoanDongGop = new Scene(QLKDG.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FXMLLoader QLTTTV = new FXMLLoader(QuanLyTamTruTamVang.class.getResource("/view/fxml/QuanLyTamTruTamVang.fxml"));
        try {
            this.QuanLyTamTruTamVang = new Scene(QLTTTV.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FXMLLoader XTK = new FXMLLoader(XemThongKe.class.getResource("/view/fxml/ThongKe.fxml"));
        try {
            this.XemThongKe = new Scene(XTK.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }




    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            NhanKhauStatic.setDsNhanKhau(FXCollections.observableArrayList());
            DBUtils.loadDsNhanKhau();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            HoKhauStatic.setDsHoKhau(FXCollections.observableArrayList());
            DBUtils.loadDsHoKhau();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            LichSuStatic.setDsLichSu(FXCollections.observableArrayList());
            DBUtils.loadDsLichSu();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (UsersDAO.getCheck() == 1) {
            QuanLyHoKhauButton.setDisable(false);
            QuanLyNhanKhauButton.setDisable(false);
            QuanLyKhoanThuButton.setDisable(false);
            QuanLyKhoanDongGopButton.setDisable(false);
            QuanLyKhoanDongGopButton.setDisable(false);
        } else {
            QuanLyHoKhauButton.setDisable(true);
            QuanLyNhanKhauButton.setDisable(true);
            XemLichSu.setDisable(true);
            XemThongKeButton.setDisable(true);
            QuanLyTamTruTamVangButton.setDisable(true);
        }

        setMenu();
    }

    public void chonQuanLyHoKhauButton (Event event) {
        controller.QuanLyHoKhau.setCurScene(this.QuanLyHoKhau);
        controller.QuanLyHoKhau.setPreScene(curScene);
        quanLyHoKhau.dsHoKhau.setItems(HoKhauStatic.getDsHoKhau());
        quanLyHoKhau.setTimKiem();
        DBUtils.changeScene(QuanLyHoKhau, event);
    }

    public void chonQuanLyNhanKhauButton (Event event) {
        controller.QuanLyNhanKhau.setCurScene(this.QuanLyNhanKhau);
        controller.QuanLyNhanKhau.setPreScene(curScene);
        quanLyNhanKhau.dsNhanKhau.refresh();
        quanLyNhanKhau.setDsNhanKhau(NhanKhauStatic.getDsNhanKhau());
        DBUtils.changeScene(QuanLyNhanKhau, event);
    }

    public void chonQuanLyKhoanThuButton (Event event) {
        controller.QuanLyKhoanThu.setCurScene(this.QuanLyKhoanThu);
        controller.QuanLyKhoanThu.setPreScene(curScene);
        DBUtils.changeScene(QuanLyKhoanThu, event);
    }

    public void chonQuanLyKhoanDongGopButton (Event event) {
        controller.QuanLyKhoanDongGop.setCurScene(this.QuanLyKhoanDongGop);
        controller.QuanLyKhoanDongGop.setPreScene(curScene);
        DBUtils.changeScene(QuanLyKhoanDongGop, event);
    }
    public void chonQuanLyTamTruTamVangButton (Event event) {
        controller.QuanLyTamTruTamVang.setCurScene(this.QuanLyTamTruTamVang);
        controller.QuanLyTamTruTamVang.setPreScene(curScene);
        DBUtils.changeScene(QuanLyTamTruTamVang, event);

    }

    public void chonXemThongKeButton (Event event) {
        controller.XemThongKe.setCurScene(this.XemThongKe);
        controller.XemThongKe.setPreScene(curScene);
        DBUtils.changeScene(XemThongKe, event);

    }

    public void Back (Event event) {
        DBUtils.changeScene(preScene, event);
    }

    public void chonXemLichSu (Event event) throws IOException {
        FXMLLoader XemLichSu = new FXMLLoader(XemLichSu.class.getResource("/view/fxml/XemLichSu.fxml"));
        Scene scene = new Scene(XemLichSu.load());
        XemLichSu xemLichSu = XemLichSu.getController();
        xemLichSu.setPreScene(this.curScene);
        DBUtils.changeScene(scene, event);
    }
}
