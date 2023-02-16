package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.HoKhau;
import model.LichSuStatic;
import model.NhanKhau;
import model.NhanKhauStatic;
import org.w3c.dom.Text;

import java.util.*;

public class ThayDoiChuHo {
    private static Scene preScene;
    public static Scene getPreScene() { return preScene; }
    public static void setPreScene(Scene preScene) {
        ThayDoiChuHo.preScene = preScene;
    }
    private TableView<HoKhau> dsHoKhau;
    public void Back (Event event) { DBUtils.changeScene(getPreScene(), event);
        for (NhanKhau e: NhanKhauStatic.getDsNhanKhau()) {
            System.out.println(e.getHoTen() + " " + e.getQuanHeChuHo());
        }
    }
    private String idHoKhau;
    public String getIdHoKhau() {
        return idHoKhau;
    }
    public void setIdHoKhau(String idHoKhau) {
        this.idHoKhau = idHoKhau;
    }
    @FXML
    public Button Back;
    @FXML
    TableView dsNhanKhau;
    @FXML
    TableColumn<NhanKhau, String> idNhanKhau;
    @FXML
    TableColumn<NhanKhau, String> hoTen;
    @FXML
    TableColumn<NhanKhau, Date> ngaySinh;
    @FXML
    TableColumn<NhanKhau, String> quanHeVoiChuHoCu;
    @FXML
    TableColumn<NhanKhau, TextField> quanHeVoiChuHoMoi;
    @FXML
    ComboBox<String> dsIdNhanKhau;
    ObservableList<String> dsId = FXCollections.observableArrayList();
    @FXML
    Button Luu;
    @FXML
    Button hoanTatSuaDoi;
    @FXML
    Label tenChuHoMoi;
    private HoKhau HoKhauCanThayDoi;
    private FilteredList<NhanKhau> dsNhanKhauCuaHoKhauNay;
    private int soLuong;
    private NhanKhau chuHoCu;
    private NhanKhau chuHoMoi = null;
    public void setHoKhauCanThayDoi(HoKhau hoKhauCanThayDoi) {
        setDsNhanKhau();
        HoKhauCanThayDoi = hoKhauCanThayDoi;
        String idHoKhau = hoKhauCanThayDoi.getIdHoKhau();
        dsNhanKhauCuaHoKhauNay = NhanKhauStatic.getDsNhanKhau().filtered(node -> node.timTheoHoKhau(idHoKhau));
        chuHoCu = dsNhanKhauCuaHoKhauNay.filtered(node->node.timChuHo()).get(0);
        soLuong = dsNhanKhauCuaHoKhauNay.size();
        dsNhanKhau.setItems(dsNhanKhauCuaHoKhauNay);
        for (NhanKhau e: dsNhanKhauCuaHoKhauNay) {
            e.canThiMoiGoi();
            if (e.getChuHo() != 1) {
                dsId.add(e.getIdNhanKhau());
            }
        }
        dsIdNhanKhau.setItems(dsId);
        hoanTatSuaDoi.setDisable(true);
    }

    public void setDsNhanKhau () {
        idNhanKhau.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("idNhanKhau"));
        hoTen.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>( "hoTen"));
        ngaySinh.setCellValueFactory(new PropertyValueFactory<NhanKhau, Date>("ngaySinh"));
        quanHeVoiChuHoCu.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("quanHeChuHo"));
        quanHeVoiChuHoMoi.setCellValueFactory(new PropertyValueFactory<NhanKhau, TextField>("thuCanDien"));
    }
    ;
    public void Luu () {
        if (dsIdNhanKhau.getValue() != null) {
            if (chuHoMoi != null) {
                chuHoMoi.getThuCanDien().setText(null);
                chuHoMoi.getThuCanDien().setDisable(false);
            }
            String idChuHoMoi = dsIdNhanKhau.getValue();
            chuHoMoi = dsNhanKhauCuaHoKhauNay.filtered(node->node.timTheoNhanKhau(idChuHoMoi)).get(0);
            chuHoMoi.getThuCanDien().setText("Chủ hộ");
            chuHoMoi.getThuCanDien().setDisable(true);
            tenChuHoMoi.setText(chuHoMoi.getHoTen());
            hoanTatSuaDoi.setDisable(false);
        } else {
            ShowAlert.showAlertError("Thất bại", "Chọn một chủ hộ mới");
        }
    }


    public void setHoanTatSuaDoi (Event event) {
        if (ShowAlert.showAlertYN("Hoàn tất sửa đổi", "Hãy đảm bảo điên đẩy đủ thông tin")) {
            String hoTenChuHoMoi = chuHoMoi.getHoTen();
            String idChuHoMoi = chuHoMoi.getIdNhanKhau();
            HoKhauCanThayDoi.setTenChuHo(hoTenChuHoMoi);
            DBUtils.UpdateChuHoChoHoKhau(HoKhauCanThayDoi.getIdHoKhau(), hoTenChuHoMoi, idChuHoMoi);
            for (NhanKhau e: dsNhanKhauCuaHoKhauNay) {
                String id = e.getIdNhanKhau();
                String quanHe = e.getThuCanDien().getText();
                e.setQuanHeChuHo(quanHe);
                e.setChuHo(0);
                DBUtils.UpdateQuanHeVoiChuHo(id, quanHe);
            }
            chuHoMoi.setChuHo(1);
            dsNhanKhau.refresh();
            String noiDung = "Thay đổi chủ hộ thành nhân khẩu: " + chuHoMoi.getHoTen() + " có ID " + chuHoMoi.getIdNhanKhau();
            LichSuStatic.taoLichSu(HoKhauCanThayDoi.getIdHoKhau(), "Thay Đổi Chủ Hộ", noiDung);
            DBUtils.changeScene(getPreScene(), event);
        }
    }
}
