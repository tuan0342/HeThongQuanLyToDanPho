package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

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
    private HoKhau hoKhauCanTach;
    public void setDsNhanKhau (String idHoKhau, String queQuan, HoKhau hoKhauCanTach) {
        dsNhanKhauCanTach = NhanKhauStatic.getDsNhanKhau().filtered(node -> node.timTheoHoKhauNhungNhanKhauKhongPhaiChuHo(idHoKhau));
        for (NhanKhau e: dsNhanKhauCanTach) {
            e.canThiMoiGoi();
            dsIdNhanKhau.add(e.getIdNhanKhau());
        }
        this.hoKhauCanTach = hoKhauCanTach;
        idChuHoMoi.setItems(dsIdNhanKhau);
        chonHayKhong.setCellValueFactory(new PropertyValueFactory<NhanKhau, CheckBox>("chonHayKhong"));
        hoTen.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("hoTen"));
        ngaySinh.setCellValueFactory(new PropertyValueFactory<NhanKhau, Date>("ngaySinh"));
        idNhanKhau.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("idNhanKhau"));
        quanHeMoi.setCellValueFactory(new PropertyValueFactory<NhanKhau, TextField>("thuCanDien"));
        quanHeCu.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("quanHeChuHo"));
        dsNhanKhauCuaHoKhauNay.setItems(dsNhanKhauCanTach);
        idHoKhauMoi.setText(SinhNgauNhien.sinhIdHoKhau(queQuan));
        idHoKhauMoi.setDisable(true);
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
            hoanThanh.setDisable(false);
        } else {
            ShowAlert.showAlertError("Lưu thất bại", "Chưa chọn chủ hộ cho nhân khẩu mới");
        }
    }

    public void HoanThanh (Event event) {
        if (ShowAlert.showAlertYN("Xác nhận lưu", "Thao tác này không thể quay lại")) {
            String idHoKhau = idHoKhauMoi.getText();
            String idHoKhauCu = duocChon.getIdHoKhau();
            String tenChuHo = tenChuHoMoi.getText();
            String diaChi = duocChon.getDiaChiThuongTru();
            HoKhau hoKhau = new HoKhau();
            hoKhau.setTenChuHo(tenChuHo);
            hoKhau.setIdHoKhau(idHoKhau);
            hoKhau.setDiaChi(diaChi);
            hoKhau.setTenChuHo(tenChuHo);
            duocChon.setChuHo(1);
            int soLuong = 0;
            DBUtils.themHoKhau(hoKhau);
            for (NhanKhau e: dsNhanKhauCanTach) {
                if (e.getChonHayKhong().isSelected()) {
                    e.setQuanHeChuHo(e.getThuCanDien().getText());
                    e.setIdHoKhau(idHoKhau);
                    soLuong += 1;
                    DBUtils.updateHoKhauChoNhanKhau(e.getIdNhanKhau(), idHoKhau, e.getChuHo(), e.getQuanHeChuHo());
                }
            }
            hoKhau.setSoLuongNhanKhau(soLuong);
            int soLuongNhanKhauMoi = this.hoKhauCanTach.getSoLuongNhanKhau() - soLuong;
            hoKhauCanTach.setSoLuongNhanKhau(soLuongNhanKhauMoi);
            String str1 = "UPDATE HoKhau\n" +
                    "SET SoLuongNhanKhau = "+soLuong+"\n" +
                    "WHERE IdHoKhau = '"+idHoKhau+"'";
            String str2 = "UPDATE HoKhau\n" +
                    "SET SoLuongNhanKhau = "+soLuongNhanKhauMoi+"\n" +
                    "WHERE IdHoKhau = '"+this.hoKhauCanTach.getIdHoKhau()+"'";
            HoKhauStatic.themHoKhau(hoKhau);
            DBUtils.ThucThiCauLenhUpdate(str1);
            DBUtils.ThucThiCauLenhUpdate(str2);
            String noiDung = "Tách hộ có ID: " + hoKhauCanTach.getIdHoKhau() + ". ID hộ khẩu mới: " + hoKhau.getIdHoKhau() + " chủ hộ là: " + hoKhau.getTenChuHo();
            LichSuStatic.taoLichSu(hoKhauCanTach.getIdHoKhau(), "Tách Hộ", noiDung);
            DBUtils.changeScene(getPreScene(), event);
        } else {
            return;
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
