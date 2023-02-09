package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import model.*;
import org.w3c.dom.Text;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;
import static java.sql.Date.valueOf;

public class ThemNhanKhau implements Initializable {
    private static Scene preScene;
    public static Scene getPreScene() {
        return preScene;
    }
    public static void setPreScene(Scene preScene) {
        ThemNhanKhau.preScene = preScene;
    }

    private int menu;

    public int getMenu() {
        return menu;
    }

    public void setMenu(int menu) {
        this.menu = menu;
    }

    public Button Back;
    public void back (Event event) {
        DBUtils.changeScene(getPreScene(), event);
    }

    public Button Save;
    @FXML
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

    @FXML
    public ComboBox<String> gioiTinh;
    private ObservableList<String> list = FXCollections.observableArrayList("Nam", "Nữ");

    private NhanKhau nhanKhauCanSua;

    public NhanKhau getNhanKhauCanSua() {
        return nhanKhauCanSua;
    }

    public void setNhanKhauCanSua(NhanKhau nhanKhauCanSua) {
        this.nhanKhauCanSua = nhanKhauCanSua;
        suaNhanKhau();
    }

    public void suaNhanKhau () {
        idHoKhau.setText(nhanKhauCanSua.getIdHoKhau());
        idHoKhau.setDisable(true);

        hoTen.setText(nhanKhauCanSua.getHoTen());
        hoTen.setDisable(true);

        biDanh.setText(nhanKhauCanSua.getBiDanh());
        biDanh.setDisable(true);

        ;
        ngaySinh.setValue(LocalDate.parse(nhanKhauCanSua.getNgaySinh().toString()));
//        ngaySinh.setDisable(true);

        nguyenQuan.setText(nhanKhauCanSua.getNguyenQuan());
        nguyenQuan.setDisable(true);

        danToc.setText(nhanKhauCanSua.getDanToc());
//        danToc.setDisable(true);

        ngheNghiep.setText(nhanKhauCanSua.getNgheNghiep());
//        ngheNghiep.setDisable(true);

        noiLamViec.setText(nhanKhauCanSua.getNoiLamViec());
//        noiLamViec.setDisable(true);

        soCCCD.setText(nhanKhauCanSua.getSoCCCD()+"");
//        soCCCD.setDisable(true);

        ngayCap.setValue(LocalDate.parse(nhanKhauCanSua.getNgayCap().toString()));
//        hoTen.setDisable(true);

        noiCap.setText(nhanKhauCanSua.getNoiCap());
//        noiCap.setDisable(true);

        diaChiThuongTru.setText(nhanKhauCanSua.getDiaChiThuongTru());
        diaChiThuongTru.setDisable(true);

        if (nhanKhauCanSua.getThoiGianDKThuongTru() != null) {
            TGDKTT.setValue(LocalDate.parse(nhanKhauCanSua.getThoiGianDKThuongTru().toString()));
        }
//        TGDKTT.setDisable(true);

        gioiTinh.setValue(nhanKhauCanSua.getGioiTinh());
        gioiTinh.setDisable(true);

        quanHeChuHo.setText(nhanKhauCanSua.getQuanHeChuHo());
        if (nhanKhauCanSua.getChuHo() == 1) {
            quanHeChuHo.setDisable(true);
        } else {
            quanHeChuHo.setDisable(false);
        }
    }

//    public void themNhanKhau (HoKhau hoKhau)
    public void save (Event event) throws SQLException {
        //Chưa thêm phần nhập căn cước công dân
        NhanKhau nhanKhau = new NhanKhau();
        if (idHoKhau.getText() == null) {
            ShowAlert.showAlertError("Nhập sai", "Nhập lại đê");
            return;
        } else {
            nhanKhau.setIdHoKhau(idHoKhau.getText());
        }

        if (biDanh.getText() == null) {
            ShowAlert.showAlertError("Nhập sai", "Nhập lại đê");
            return;
        } else {
            nhanKhau.setBiDanh(biDanh.getText());
        }

        if (hoTen.getText() == null) {
            ShowAlert.showAlertError("Nhập sai", "Nhập lại đê");
            return;
        } else {
            nhanKhau.setHoTen(hoTen.getText());
        }

        if (ngaySinh.getValue() == null) {
            ShowAlert.showAlertError("Nhập sai", "Nhập lại đê");
            return;
        } else {
            nhanKhau.setNgaySinh(valueOf(ngaySinh.getValue()));
        }

        if (nguyenQuan.getText() == null) {
            ShowAlert.showAlertError("Nhập sai", "Nhập lại đê");
            return;
        } else {
            nhanKhau.setNguyenQuan(nguyenQuan.getText());
        }
        if (danToc.getText() == null) {
            ShowAlert.showAlertError("Nhập sai", "Nhập lại đê");
            return;
        } else {
            nhanKhau.setDanToc(danToc.getText());
        }

        if (ngheNghiep.getText() == null) {
            ShowAlert.showAlertError("Nhập sai", "Nhập lại đê");
            return;
        } else {
            nhanKhau.setNgheNghiep(ngheNghiep.getText());
        }

        if (noiLamViec.getText() == null) {
            ShowAlert.showAlertError("Nhập sai", "Nhập lại đê");
            return;
        } else {
            nhanKhau.setNoiLamViec(noiLamViec.getText());
        }

        if (ngayCap.getValue() == null) {
            ShowAlert.showAlertError("Nhập sai", "Nhập lại đê");
            return;
        } else {
            nhanKhau.setNgayCap(valueOf(ngayCap.getValue()));
        }

        if (noiCap.getText() == null) {
            ShowAlert.showAlertError("Nhập sai", "Nhập lại đê");
            return;
        } else {
            nhanKhau.setNoiCap(noiCap.getText());
        }

        if (diaChiThuongTru.getText() == null) {
            ShowAlert.showAlertError("Nhập sai", "Nhập lại đê");
            return;
        } else {
            nhanKhau.setDiaChiThuongTru(diaChiThuongTru.getText());
        }

        if (quanHeChuHo.getText() == null) {
            ShowAlert.showAlertError("Nhập sai", "Nhập lại đê");
            return;
        } else {
            nhanKhau.setQuanHeChuHo(quanHeChuHo.getText());
        }

        if (gioiTinh.getValue() == null) {
            ShowAlert.showAlertError("Nhập sai", "Chọn giới tính");
            return;
        } else {
            if (gioiTinh.getValue().compareTo("Nam") == 0) {
                nhanKhau.setGioiTinh(true);
            } else {
                if (gioiTinh.getValue().compareTo("Nữ") == 0) {
                    nhanKhau.setGioiTinh(false);
                }
            }
        }
        if (getMenu() == 3) {
            nhanKhauCanSua.setNgaySinh(nhanKhau.getNgaySinh());
            nhanKhauCanSua.setNgheNghiep(nhanKhau.getNgheNghiep());
            nhanKhauCanSua.setNoiLamViec(nhanKhau.getNoiLamViec());
            nhanKhauCanSua.setDanToc(nhanKhau.getDanToc());
            nhanKhauCanSua.setSoCCCD(nhanKhau.getSoCCCD());
            nhanKhauCanSua.setNoiCap(nhanKhau.getNoiCap());
            nhanKhauCanSua.setNgayCap(nhanKhau.getNgayCap());
            nhanKhauCanSua.setQuanHeChuHo(nhanKhau.getQuanHeChuHo());
            nhanKhauCanSua.setThoiGianDKThuongTru(nhanKhau.getThoiGianDKThuongTru());
            String noiDung = "Sửa nhân khẩu với idNhanKhau: " + nhanKhauCanSua.getIdNhanKhau();
            LichSuStatic.taoLichSu(nhanKhauCanSua.getIdHoKhau(), "Sửa", noiDung);
        } else {
            nhanKhau.setIdNhanKhau(SinhNgauNhien.sinhIdNhanKhau(nhanKhau.getHoTen(), nhanKhau.getDiaChiThuongTru()));
            if (getMenu() == 4) {
                NhanKhauStatic.themNhanKhau(nhanKhau);
                HoKhau hoKhau = HoKhauStatic.getDsHoKhau().filtered(node -> node.timTheoHoKhau(nhanKhau.getIdHoKhau())).get(0);
                int soLuong = hoKhau.getSoLuongNhanKhau()+1;
                hoKhau.setSoLuongNhanKhau(soLuong);
                String noiDung = "Thêm nhân khẩu idNhanKhau: " + nhanKhau.getIdNhanKhau();
                LichSuStatic.taoLichSu(nhanKhau.getIdHoKhau(), "Thêm Nhân Khẩu", noiDung);
            }else {
                HoKhauStatic.themNhanKhau(nhanKhau);
            }
        }
        DBUtils.changeScene(getPreScene(), event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gioiTinh.setItems(list);
    }
//    @Override
//    public String  () {
//        String str = "Thêm nhân khẩu mới "
//    }
}
