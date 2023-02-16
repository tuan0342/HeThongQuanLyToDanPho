package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.*;

import java.io.IOException;
import java.net.URL;
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
    public Button TimKiem;
    @FXML
    public RadioButton TimTheoID;
    public RadioButton TimTheoTenNhanKhau;
    public RadioButton TimTheoIDHoKhau;
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
    @FXML
    ObservableList<NhanKhau> danhSachNhanKhau = FXCollections.observableArrayList();
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

    public void setDsNhanKhau (ObservableList<NhanKhau> ds) {
        setThanhTimKiem();
        this.danhSachNhanKhau = ds;
        dsNhanKhau.setItems(danhSachNhanKhau);
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
        String timText = DienIdNhanKhau.getText();
        ObservableList<NhanKhau> dsCon = FXCollections.observableArrayList();
        if (timText == null) {
            ShowAlert.showAlertError("Tìm thất bại", "Nhập từ khóa cần tìm");
        } else {
            if (TimTheoID.isSelected()) {
                Predicate<NhanKhau> ketQuaTim = node->node.timTheoNhanKhau(timText);
                dsCon = danhSachNhanKhau.filtered(ketQuaTim);
                if (dsCon.isEmpty()) {
                    ShowAlert.showAlertError("Không tồn tại nhân khẩu", "Nhập lại");
                    dsNhanKhau.setItems(danhSachNhanKhau);
                }else {
                    dsNhanKhau.setItems(dsCon);
                }
            }else {
                if (TimTheoTenNhanKhau.isSelected()) {
                    Predicate<NhanKhau> ketQuaTim = node->node.timTheoTenNhanKhau(timText);
                    dsCon = dsNhanKhau.getItems().filtered(ketQuaTim);
                    if (dsCon.isEmpty()) {
                        ShowAlert.showAlertError("Không tồn tại nhân khẩu", "Nhập lại");
                        dsNhanKhau.setItems(danhSachNhanKhau);
                    }else {
                        dsNhanKhau.setItems(dsCon);
                    }
                } else {
                    Predicate<NhanKhau> ketQuaTim = node->node.timTheoHoKhau(timText);
                    dsCon = dsNhanKhau.getItems().filtered(ketQuaTim);
                    if (dsCon.isEmpty()) {
                        ShowAlert.showAlertError("Không tồn tại nhân khẩu", "Nhập lại");
                        dsNhanKhau.setItems(danhSachNhanKhau);
                    }else {
                        dsNhanKhau.setItems(dsCon);
                    }
                }
            }
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

    public void Xoa (Event event) throws IOException {
        if (dsNhanKhau.getSelectionModel().getSelectedItem() != null) {
            NhanKhau duocChon = (NhanKhau) dsNhanKhau.getSelectionModel().getSelectedItem();
            String lidoXoa = ShowAlert.dienLiDo();
            if (lidoXoa != null) {
                if (duocChon.getChuHo() != 1) {
                    HoKhau hoKhau = HoKhauStatic.getDsHoKhau().filtered(Node -> Node.timTheoHoKhau(duocChon.getIdHoKhau())).get(0);
                    int soLuong = hoKhau.getSoLuongNhanKhau();
                    soLuong -= 1;
                    hoKhau.setSoLuongNhanKhau(soLuong);
                    NhanKhauStatic.getDsNhanKhau().remove(duocChon);
                    String thaoTac = "Nhân khẩu: " + duocChon.getHoTen() + " có ID: " + duocChon.getIdNhanKhau();
                    LichSuStatic.taoLichSu(duocChon.getIdHoKhau(), "Xóa", thaoTac + " " + lidoXoa + duocChon);
                    DBUtils.XoaNhanKhau(duocChon.getIdNhanKhau());
                    DBUtils.thayDoiSoLuongHoKhau(duocChon.getIdHoKhau(), soLuong);
                } else {
                    if (duocChon.getChuHo() == 1) {
                        HoKhau hoKhau = HoKhauStatic.getDsHoKhau().filtered(Node -> Node.timTheoHoKhau(duocChon.getIdHoKhau())).get(0);
                        if (hoKhau.getSoLuongNhanKhau() > 1) {
                            //thay đổi chủ hộ
                            FXMLLoader fxmlLoader  = new FXMLLoader(ThayDoiChuHo.class.getResource("/view/fxml/ThayDoiChuHo.fxml"));
                            Parent root = fxmlLoader.load();
                            Scene scene = new Scene(root, 1000, 600);
                            controller.ThayDoiChuHo.setPreScene(getCurScene());
                            ThayDoiChuHo thayDoiChuHo = fxmlLoader.getController();
                            thayDoiChuHo.setHoKhauCanThayDoi(hoKhau);
                            NhanKhauStatic.getDsNhanKhau().remove(duocChon);
                            DBUtils.changeScene(scene, event);

                            // sau khi thay đổi
                            int soLuong = hoKhau.getSoLuongNhanKhau();
                            soLuong -= 1;
                            hoKhau.setSoLuongNhanKhau(soLuong);
                            String thaoTac = "Nhân khẩu: " + duocChon.getHoTen() + " có ID: " + duocChon.getIdNhanKhau() + " " + lidoXoa + duocChon;
                            LichSuStatic.taoLichSu(duocChon.getIdHoKhau(), "Xóa", thaoTac);
                            DBUtils.XoaNhanKhau(duocChon.getIdNhanKhau());
                            DBUtils.thayDoiSoLuongHoKhau(duocChon.getIdHoKhau(), soLuong);
                        } else {
                            if (hoKhau.getSoLuongNhanKhau() == 1) {
                                ShowAlert.showAlertYN("Xóa thất bại", "Hãy thêm nhân khẩu là người thừa kế hộ khẩu này");
                            }
                        }
                    }
                }
            }
        } else {
            ShowAlert.showAlertError("Xóa thất bại", "Chọn một nhân khẩu cần xóa");
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
    private HoKhau hoKhauHienTai;

    public HoKhau getHoKhauHienTai() {
        return hoKhauHienTai;
    }

    public void setHoKhauHienTai(HoKhau hoKhauHienTai) {
        this.hoKhauHienTai = hoKhauHienTai;
    }

    public void hienThiThongTinHoKhau (HoKhau hoKhau) {
//        dsNhanKhau.setItems();
        setHoKhauHienTai(hoKhau);
        danhSachNhanKhau = NhanKhauStatic.getDsNhanKhau().filtered(node -> node.timTheoHoKhau(hoKhau.getIdHoKhau()));
        dsNhanKhau.setItems(danhSachNhanKhau);
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

    public Button Refresh;
    public void refresh (Event event) {
        if (dsNhanKhau.getSelectionModel().getSelectedItem() != null) {
            hienThiThongTin((NhanKhau) dsNhanKhau.getSelectionModel().getSelectedItem());
        }
        if (getHoKhauHienTai() != null) {
            hienThiThongTinHoKhau(getHoKhauHienTai());
        }
    }
    public void setThanhTimKiem () {
        DienIdNhanKhau.setText(null);
        TimTheoID.setSelected(true);
    }

//    public String
}
