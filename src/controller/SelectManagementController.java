package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.UsersDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


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
    public Scene QuanLyKhoanThu;
    public Scene QuanLyKhoanDongGop;

    public Button QuanLyHoKhauButton;
    public Button QuanLyNhanKhauButton;
    public Button QuanLyKhoanThuButton;
    public Button QuanLyKhoanDongGopButton;

    public Button Back;

    private void setMenu () {

        FXMLLoader QLHK = new FXMLLoader(QuanLyHoKhau.class.getResource("/view/fxml/QuanLyHoKhau.fxml"));
        try {
            this.QuanLyHoKhau = new Scene(QLHK.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FXMLLoader QLNK = new FXMLLoader(QuanLyNhanKhau.class.getResource("/view/fxml/Quan_Ly_Nhan_Khau.fxml"));
        try {
            this.QuanLyNhanKhau = new Scene(QLNK.load());
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


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (UsersDAO.getCheck() == 1) {
            QuanLyHoKhauButton.setDisable(false);
            QuanLyNhanKhauButton.setDisable(false);
            QuanLyKhoanThuButton.setDisable(false);
            QuanLyKhoanDongGopButton.setDisable(false);
        } else {
            QuanLyHoKhauButton.setDisable(true);
            QuanLyNhanKhauButton.setDisable(true);
        }

        setMenu();
    }

    public void chonQuanLyHoKhauButton (Event event) {
        controller.QuanLyHoKhau.setCurScene(this.QuanLyHoKhau);
        controller.QuanLyHoKhau.setPreScene(curScene);
        DBUtils.changeScene(QuanLyHoKhau, event);
    }

    public void chonQuanLyNhanKhauButton (Event event) {
        controller.QuanLyNhanKhau.setCurScene(this.QuanLyNhanKhau);
        controller.QuanLyNhanKhau.setPreScene(curScene);
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

    public void Back (Event event) {
        DBUtils.changeScene(preScene, event);
    }
}
