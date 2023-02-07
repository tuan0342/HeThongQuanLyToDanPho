package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.NhanKhau;
import model.NhanKhauStatic;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class TachHo implements Initializable {
    private static Scene preScene;
    public static Scene getPreScene() { return preScene; }
    public static void setPreScene(Scene preScene) {
        TachHo.preScene = preScene;
    }
    @FXML
    public Button Back;
    public void Back (Event event) {
        DBUtils.changeScene(getPreScene(), event);
    }

    public TableView<NhanKhau> dsNhanKhauCuaHoKhauNay;
    private ObservableList<NhanKhau> dsNhanKhauCanTach = FXCollections.observableArrayList();
    private ObservableList<String> dsIdNhanKhau = FXCollections.observableArrayList();
    public TableColumn<NhanKhau, CheckBox> chonHayKhong;
    public TableColumn<NhanKhau, String> idNhanKhau;
    public TableColumn<NhanKhau, String> hoTen;
    public TableColumn<NhanKhau, Date> ngaySinh;
    public TableColumn<NhanKhau, String> quanHeCu;
    public TableColumn<NhanKhau, TextField> quanHeMoi;

    public TextField idHoKhauMoi;

    public ChoiceBox<String> idChuHoMoi;
    public Button luu;
    public Button hoanThanh;
    public Label tenChuHoMoi;
    private NhanKhau duocChon = null;

    public void setDsNhanKhau (String idHoKhau) {
        dsNhanKhauCanTach = NhanKhauStatic.getDsNhanKhau().filtered(node -> node.timTheoHoKhauNhungNhanKhauKhongPhaiChuHo(idHoKhau));
        for (NhanKhau e: dsNhanKhauCanTach) {
            e.canThiMoiGoi();
            dsIdNhanKhau.add(e.getIdNhanKhau());
        }
        idChuHoMoi.setItems(dsIdNhanKhau);
        chonHayKhong.setCellValueFactory(new PropertyValueFactory<NhanKhau, CheckBox>("chonHayKhong"));
        hoTen.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("hoTen"));
        ngaySinh.setCellValueFactory(new PropertyValueFactory<NhanKhau, Date>("ngaySinh"));
        idNhanKhau.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("idNhanKhau"));
        quanHeMoi.setCellValueFactory(new PropertyValueFactory<NhanKhau, TextField>("thuCanDien"));
        quanHeCu.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("quanHeChuHo"));
        dsNhanKhauCuaHoKhauNay.setItems(dsNhanKhauCanTach);
        hoanThanh.setDisable(true);
    }

    private void DuocChon (boolean check) {
        if (!check) {
            duocChon.getChonHayKhong().setDisable(false);
            duocChon.getThuCanDien().setDisable(false);
            duocChon.getChonHayKhong().setSelected(false);
            duocChon.getThuCanDien().setText("");
        } else {
            duocChon.getChonHayKhong().setDisable(true);
            duocChon.getThuCanDien().setDisable(true);
            duocChon.getChonHayKhong().setSelected(true);
            duocChon.getThuCanDien().setText("Chủ hộ");
        }
    }
    public void luu (Event event) {
        if (idChuHoMoi.getValue() != null) {
            if (duocChon != null) {
                DuocChon(false);
            }
            String id = idChuHoMoi.getValue();
            duocChon = dsNhanKhauCanTach.filtered(node->node.timTheoNhanKhau(id)).get(0);
            DuocChon(true);
            tenChuHoMoi.setText(duocChon.getHoTen());
        } else {
            ShowAlert.showAlertError("Chọn chủ hộ đê", "Rồi muốn làm gì thì làm");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
