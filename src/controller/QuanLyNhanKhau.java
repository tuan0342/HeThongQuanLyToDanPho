package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class QuanLyNhanKhau implements Initializable {
    //Lien quan den Scene truoc Scene sau
    private static Scene preScene;
    private static Scene curScene;
    public static Scene getPreScene() {
        return preScene;
    }
    public static void setPreScene(Scene preScene) {
        QuanLyNhanKhau.preScene = preScene;
    }
    public static Scene getCurScene() {
        return curScene;
    }
    public static void setCurScene(Scene curScene) {
        QuanLyNhanKhau.curScene = curScene;
    }
    //Button quay lai
    public Button Back;
    public void Back (Event event) {
        DBUtils.changeScene(preScene, event);
        khungThongTinHoKhau.setVisible(false);
        dsNhanKhau.setItems(NhanKhauStatic.getDsNhanKhau());
    }
    @FXML
    public Button suaThongTin;
    @FXML
    public Button Xoa;
    public Button Tim;
    public TextField DienIdNhanKhau;
    public Text hoTen1;
    public Text gioiTinh2;
    public Text biDanh3;
    public Text ngaySinh4;
    public Text noiSinh5;
    public Text queQuan6;
    public Text danToc7;
    public Text tonGiao8;
    public Text CCCD9;
    public Text idNhanKhau10;
    public Text idHoKhau11;
    public Text diaChiThuongTru12;
    public Text ngheNghiep13;
    public Text noiLamViec14;
    public Text quanHeVoiChuHo15;
    public AnchorPane khungThongTin;



    @FXML
    TableView dsNhanKhau;
    @FXML
    TableColumn <NhanKhau, String> idHoKhau;
    @FXML
    TableColumn <NhanKhau, String> idNhanKhau;
    @FXML
    TableColumn <NhanKhau, String> hoTen;
    public void setdsNhanKhau () {
        idHoKhau.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("idHoKhau"));
        idNhanKhau.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("idNhanKhau"));
        hoTen.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>( "hoTen"));
        dsNhanKhau.setItems(NhanKhauStatic.getDsNhanKhau());
        dsNhanKhau.setOnMouseClicked(mouseEvent -> {
            NhanKhau duocChon = (NhanKhau) dsNhanKhau.getSelectionModel().getSelectedItem();
            if (duocChon != null) {
                hienThiThongTin(duocChon);
            }
        });
    }

    public void hienThiThongTin (NhanKhau nhanKhau) {
        hoTen1.setText(nhanKhau.getHoTen());
        gioiTinh2.setText(nhanKhau.getGioiTinh());
        biDanh3.setText(nhanKhau.getBiDanh());
        ngaySinh4.setText(nhanKhau.getNgaySinh().toString());
        noiSinh5.setText(nhanKhau.getNguyenQuan());
        queQuan6.setText(nhanKhau.getNguyenQuan());
        danToc7.setText(nhanKhau.getDanToc());
        tonGiao8.setText(" ");
        CCCD9.setText(nhanKhau.getSoCCCD() + "");
        idNhanKhau10.setText(nhanKhau.getIdNhanKhau());
        idHoKhau11.setText(nhanKhau.getIdHoKhau());
        diaChiThuongTru12.setText(nhanKhau.getDiaChiThuongTru());
        ngheNghiep13.setText(nhanKhau.getNgheNghiep());
        noiLamViec14.setText(nhanKhau.getNoiLamViec());
        quanHeVoiChuHo15.setText(nhanKhau.getQuanHeChuHo());
    }

    public void Tim (Event event) {
        if (DienIdNhanKhau.getText() != null) {
            String idNhanKhau = DienIdNhanKhau.getText();
            if (!NhanKhauStatic.getDsNhanKhau().filtered(Node->Node.timTheoNhanKhau(idNhanKhau)).isEmpty()) {
                NhanKhau nhanKhau = NhanKhauStatic.getDsNhanKhau().filtered(Node->Node.timTheoNhanKhau(idNhanKhau)).get(0);
                hienThiThongTin(nhanKhau);
            } else {
                ShowAlert.showAlertError("Không tồn tại nhân khẩu", "Không tồn tại nhân khẩu này");
            }
        } else {
            ShowAlert.showAlertError("Thất bại", "Nhập id nhân khẩu cần tìm");
        }
    }

    public void SuaThongTin (Event event) throws IOException {
        if (dsNhanKhau.getSelectionModel().getSelectedItem() != null) {
            NhanKhau duocChon = (NhanKhau) dsNhanKhau.getSelectionModel().getSelectedItem();
            FXMLLoader fxmlLoader = new FXMLLoader(ThemNhanKhau.class.getResource("/view/fxml/Them_Nhan_Khau.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            ThemNhanKhau controller = fxmlLoader.getController();
            ThemNhanKhau.setPreScene(getCurScene());
            controller.setMenu(3);
            controller.setNhanKhauCanSua(duocChon);
            DBUtils.changeScene(scene, event);
        }
    }

    public void Xoa (Event event) {
        if (dsNhanKhau.getSelectionModel().getSelectedItem() != null) {
            NhanKhau duocChon = (NhanKhau) dsNhanKhau.getSelectionModel().getSelectedItem();
            if (duocChon.getChuHo() == 1) {
                ShowAlert.showAlertError("Không thể xóa chủ hộ", "Đừng cố làm gì có được gì đâu :v");
            } else {
                if (ShowAlert.showAlertYN("Chắc chưa??", "Xóa là đéo khôi phục được đâu nhé :v")) {
                    HoKhau hoKhau = HoKhauStatic.getDsHoKhau().filtered(Node -> Node.timTheoHoKhau(duocChon.getIdHoKhau())).get(0);
                    int soLuong = hoKhau.getSoLuongNhanKhau();
                    soLuong -= 1;
                    hoKhau.setSoLuongNhanKhau(soLuong);
                    NhanKhauStatic.getDsNhanKhau().remove(duocChon);
                    String thaoTac = "Xóa nhân khẩu có ID nhân khẩu: " + duocChon.getIdNhanKhau();
                    LichSuStatic.taoLichSu(duocChon.getIdHoKhau(), "Xóa", thaoTac);
                }
            }
        } else {
            ShowAlert.showAlertError("Không thể xóa", "Có chọn cái đ gì đâu mà xóa :v, ăn nói xà lơ");
        }
    }

    //Phaanf này dành cho Hộ Khẩu
    @FXML
    public AnchorPane khungThongTinHoKhau;
    public Text idHoKhau1;
    public Text tenChuHo2;
    public Text diaChi3;
    public Text soLuongNhanKhau4;
    public Button themNhanKhau;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setdsNhanKhau();
        khungThongTinHoKhau.setVisible(false);
    }

    public void hienThiThongTinHoKhau (HoKhau hoKhau) {
        dsNhanKhau.setItems(NhanKhauStatic.getDsNhanKhau().filtered(node -> node.timTheoHoKhau(hoKhau.getIdHoKhau())));
        khungThongTinHoKhau.setVisible(true);
        idHoKhau1.setText(hoKhau.getIdHoKhau());
        tenChuHo2.setText(hoKhau.getTenChuHo());
        diaChi3.setText(hoKhau.getDiaChi());
        soLuongNhanKhau4.setText(hoKhau.getSoLuongNhanKhau()+"");
    }

    public void ThemNhanKhau (Event event) throws IOException {
        NhanKhau nhanKhau = (NhanKhau) dsNhanKhau.getItems().get(0);
        String idHoKhau = nhanKhau.getIdHoKhau();
        String diaChiThuongTru = nhanKhau.getDiaChiThuongTru();
        FXMLLoader fxmlLoader = new FXMLLoader(ThemNhanKhau.class.getResource("/view/fxml/Them_Nhan_Khau.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ThemNhanKhau controller = fxmlLoader.getController();
        controller.idHoKhau.setText(idHoKhau);
        controller.idHoKhau.setDisable(true);
        controller.diaChiThuongTru.setText(diaChiThuongTru);
        controller.diaChiThuongTru.setDisable(true);
        controller.setMenu(4);
        ThemNhanKhau.setPreScene(getCurScene());
        DBUtils.changeScene(scene, event);
    }

//    public String
}
