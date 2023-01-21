package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.HoKhau;
import model.NhanKhau;
import model.NhanKhauStatic;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ThayDoiChuHo {
    private static Scene preScene;
    public static Scene getPreScene() { return preScene; }
    public static void setPreScene(Scene preScene) {
        ThayDoiChuHo.preScene = preScene;
    }
    private TableView<HoKhau> dsHoKhau;
    public void setDsHoKhau (TableView<HoKhau> dsHoKhau) {
        this.dsHoKhau = dsHoKhau;
    }
    public void Back (Event event) { DBUtils.changeScene(getPreScene(), event);
        for (NhanKhau e: NhanKhauStatic.getDsNhanKhau()) {
            System.out.println(e.getHoTen() + " " + e.getQuanHeChuHo());
        }
        dsHoKhau.refresh();
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
    TableColumn<NhanKhau, Integer> soCCCD;
    @FXML
    TableColumn<NhanKhau, String> quanHeVoiChuHo;
    @FXML
    AnchorPane KhungLon;
    @FXML
    AnchorPane KhungBe;
    @FXML
    Button luuChuHoMoi;
    private HoKhau HoKhauCanThayDoi;
    private FilteredList<NhanKhau> dsNhanKhauCuaHoKhauNay;
    private int soLuong;
    public void setHoKhauCanThayDoi(HoKhau hoKhauCanThayDoi) {
        setDsNhanKhau();
        setButtonTruocKhiChonChuHo();
        hoanTatSuaDoi.setDisable(true);
        HoKhauCanThayDoi = hoKhauCanThayDoi;
        String idHoKhau = hoKhauCanThayDoi.getIdHoKhau();
        dsNhanKhauCuaHoKhauNay = NhanKhauStatic.getDsNhanKhau().filtered(node -> node.timTheoHoKhau(idHoKhau));
        soLuong = dsNhanKhauCuaHoKhauNay.size() - 1;
        dsNhanKhau.setItems(dsNhanKhauCuaHoKhauNay);
    }
    private NhanKhau chuHoMoi;
    public void setDsNhanKhau () {
        idNhanKhau.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("idNhanKhau"));
        hoTen.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>( "hoTen"));
        ngaySinh.setCellValueFactory(new PropertyValueFactory<NhanKhau, Date>("ngaySinh"));
        soCCCD.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("soCCCD"));
        quanHeVoiChuHo.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("quanHeChuHo"));
        dsNhanKhau.setOnMouseClicked(mouseEvent -> {
            NhanKhau duocChon = (NhanKhau) dsNhanKhau.getSelectionModel().getSelectedItem();
            if (duocChon != null) {
                luuChuHoMoi.setDisable(false);
            }
        });
    }
    public void setButtonTruocKhiChonChuHo () {
        KhungBe.setVisible(false);
        luuChuHoMoi.setDisable(true);
        fieldDienChuHo.setDisable(true);
    }
    @FXML
    TextField fieldDienChuHo;
    private String idChuHoMoi;
    private String hoTenChuHoMoi;
    public void luuChuHoMoi (Event event) {
        if (ShowAlert.showAlertYN("Lưu", "Chắc chưa?")){
            NhanKhau duocChon = (NhanKhau) dsNhanKhau.getSelectionModel().getSelectedItem();
            idChuHoMoi = duocChon.getIdNhanKhau();
            hoTenChuHoMoi = duocChon.getHoTen();
            quanHeMoi.put(idChuHoMoi, "Chủ hộ");
            fieldDienChuHo.setText(duocChon.getHoTen());
            KhungBe.setVisible(true);
            luuChuHoMoi.setVisible(false);
            setButtonSauKhiChonDuocChuHo();
            Back.setDisable(true);
        } else return;
    }
    @FXML
    TextField quanHeChuHoCu;
    @FXML
    TextField quanHeChuHoMoi;

    private void setButtonSauKhiChonDuocChuHo () {
        quanHeChuHoCu.setDisable(true);
        quanHeChuHoMoi.setDisable(true);
        dsNhanKhau.setOnMouseClicked(mouseEvent -> {
            NhanKhau duocChon = (NhanKhau) dsNhanKhau.getSelectionModel().getSelectedItem();
            quanHeChuHoCu.setText(duocChon.getQuanHeChuHo());
            if (duocChon.getIdNhanKhau().compareTo(idChuHoMoi) == 0) {
                quanHeChuHoMoi.setText("Chủ hộ");
                quanHeChuHoMoi.setDisable(true);
            } else {
                String quanHe = quanHeMoi.get(duocChon.getIdNhanKhau());
                if (quanHe == null) {
                    quanHeChuHoMoi.setText("");
                } else {
                    quanHeChuHoMoi.setText(quanHe);
                }
                quanHeChuHoMoi.setDisable(false);
            }
        });
    }

    @FXML
    public Button hoanTatSuaDoi;
    @FXML
    public Button luuThayDoi;
    private HashMap<String, String> quanHeMoi = new HashMap<String, String>();
    public void luuThayDoi (Event event) {
        if (quanHeChuHoMoi.getText().compareTo("Chủ hộ") == 0) {
            ShowAlert.showAlertError("Nhập lại", "Nhập lại quan hệ mới");
            return;
        }
        NhanKhau duocChon = (NhanKhau) dsNhanKhau.getSelectionModel().getSelectedItem();
        if (quanHeMoi.containsKey(duocChon.getIdNhanKhau())) {
            quanHeMoi.replace(duocChon.getIdNhanKhau(), quanHeChuHoMoi.getText());
        } else {
            quanHeMoi.put(duocChon.getIdNhanKhau(), quanHeChuHoMoi.getText());
        }
        soLuong -= 1;
        if (soLuong == 0) {
            hoanTatSuaDoi.setDisable(false);
        };
    }

    public void setHoanTatSuaDoi (Event event) {
        if (ShowAlert.showAlertYN("Hoàn tất sửa đổi?", "Đồng ý")) {
            HoKhauCanThayDoi.setTenChuHo(hoTenChuHoMoi);
            DBUtils.UpdateChuHoChoHoKhau(HoKhauCanThayDoi.getIdHoKhau(), hoTenChuHoMoi, idChuHoMoi);
            for (NhanKhau e: dsNhanKhauCuaHoKhauNay) {
                String id = e.getIdNhanKhau();
                String quanHe = quanHeMoi.get(id);
                e.setQuanHeChuHo(quanHe);
                if (quanHe.compareTo("Chủ hộ") == 0) e.setChuHo(1);
                else e.setChuHo(0);
                DBUtils.UpdateQuanHeVoiChuHo(id, quanHe);
            }
            Back.setDisable(false);
            dsNhanKhau.refresh();
        } else {
            return;
        }
    }
}
