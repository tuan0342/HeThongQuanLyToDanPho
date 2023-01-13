package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.KhoanThu;
import model.KhoanThuSatic;

import static java.lang.Integer.parseInt;
import static java.sql.Date.valueOf;

public class ThemKhoanThu {
    public static Scene preScene;
    public static Scene getPreScene() {
        return preScene;
    }
    public static void setPreScene(Scene preScene) {
        ThemKhoanThu.preScene = preScene;
    }
    @FXML
    public Button Them;
    @FXML
    public Button Back;
    @FXML
    public TextField idKhoanThu;
    @FXML
    public TextField tenKhoanThu;
    @FXML
    public TextField soTien;
    @FXML
    public DatePicker ngayNop;
    private int chucNang;
    private int getMenu() {
        return chucNang;
    }
    private void setMenu(int chucNang) {
        this.chucNang = chucNang;
    }
    public void them (Event event) {
        KhoanThu khoanThu = new KhoanThu();
        khoanThu.setIdKhoanThu(idKhoanThu.getText());
        khoanThu.setTenKhoanThu(tenKhoanThu.getText());
        khoanThu.setNgay(valueOf(ngayNop.getValue()));
        khoanThu.setSoTien(parseInt(soTien.getText()));
        KhoanThuSatic.themKhoanThu(khoanThu);
        DBUtils.changeScene(getPreScene(), event);
    }

    public void back (Event event) {
        DBUtils.changeScene(getPreScene(), event);
    }
}
