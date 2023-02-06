package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import static java.sql.Date.valueOf;

public class QuanLyTamTruTamVang implements Initializable {
    //Lien quan den Scene truoc Scene sau
    private static Scene preScene;
    private static Scene curScene;

    public static Scene getPreScene() {
        return preScene;
    }

    public static void setPreScene(Scene preScene) {
        QuanLyTamTruTamVang.preScene = preScene;
    }

    public static Scene getCurScene() {
        return curScene;
    }

    public static void setCurScene(Scene curScene) {
        QuanLyTamTruTamVang.curScene = curScene;
    }

    //Button quay lai
    public Button Back;
    public void Back (Event event) {
        DBUtils.changeScene(preScene, event);
    }




    //Lay Du Lieu Tam Tru Tam Vang
    public ChoiceBox LoaiChoiceBox;
    private String[] listLoaiChoiceBox = {"Tạm Trú", "Tạm Vắng"};

    public TextField HoTenTextField;
    public DatePicker NgaySinhDatePicker;

    public ChoiceBox GioiTinhChoiceBox;
    private String[] listGioiTinhChoiceBox = {"Nam", "Nữ"};

    public TextField QuocTichTextField;
    public TextField SoCCCDTextField;
    public DatePicker NgayDangKyDatePicker;
    public DatePicker NgayKetThucDatePicker;
    public TextField DiaChiThuongTruTextField;
    public TextField DiaChiTamTruTamVangField;
    public TextField LyDoTextField;





    // luu va huy bo

    public Button SaveButton;
    public Button ResetButton;
    //lưu khai báo tạm trú tạm vắng
    public void save (Event event) throws SQLException {
        KhaiBaoTamTruTamVang khaiBaoTamTruTamVang = new KhaiBaoTamTruTamVang();
        int count = 0;
        if (LoaiChoiceBox.getValue() == null) {
            count++;
            ShowAlert.showAlertError("Chưa chọn Loại Khai Báo","Mời Bạn Chọn Lại");
        } else {
            khaiBaoTamTruTamVang.setLoai((String) LoaiChoiceBox.getValue());
        }

        if (HoTenTextField.getText().length() == 0) {
            count++;
            ShowAlert.showAlertError("Chưa Nhập Họ Tên!", "Mời Bạn Nhập lại");

        } else {
            khaiBaoTamTruTamVang.setHoTen(HoTenTextField.getText());
        }

        if (NgaySinhDatePicker.getValue() == null) {
            count++;
            ShowAlert.showAlertError("Chưa chọn ngày sinh!", "Mời bạn chọn lại");
        } else {
            khaiBaoTamTruTamVang.setNgaySinh(valueOf(NgaySinhDatePicker.getValue()));
        }

        if (GioiTinhChoiceBox.getValue() == null) {
            count++;
            ShowAlert.showAlertError("Chưa chọn giới tính!", "Mời bạn chọn lại");
        } else {
            khaiBaoTamTruTamVang.setGioiTinh((String)GioiTinhChoiceBox.getValue() );
        }

        if (QuocTichTextField.getText().length() == 0) {
            count++;
            ShowAlert.showAlertError("Chưa nhập quốc tịch!","Moi bạn nhập lại");
        } else {
            khaiBaoTamTruTamVang.setQuocTich(QuocTichTextField.getText());
        }

        khaiBaoTamTruTamVang.setSoCCCD(SoCCCDTextField.getText());

       if (NgayDangKyDatePicker.getValue() == null) {
           count++;
           ShowAlert.showAlertError("Bạn chưa chọn ngày đăng ký!","Mời bạn chọn lại");
       } else {
           khaiBaoTamTruTamVang.setNgayDangKy(valueOf(NgayDangKyDatePicker.getValue()));
       }

      if (NgayKetThucDatePicker.getValue() == null) {
          count++;
          ShowAlert.showAlertError("Bạn chưa chọn ngày kết thúc!","Mời bạn chọn lại");
      } else {
          khaiBaoTamTruTamVang.setNgayKetThuc(valueOf(NgayKetThucDatePicker.getValue()));
      }

      if (DiaChiThuongTruTextField.getText().length() == 0) {
          count++;
          ShowAlert.showAlertError("Bạn chưa nhập địa chỉ thường trú!","Moi bạn nhập lại");
      } else {
          khaiBaoTamTruTamVang.setDiaChiThuongTru(DiaChiThuongTruTextField.getText());
      }
      if (DiaChiTamTruTamVangField.getText().length() == 0) {
          count++;
          ShowAlert.showAlertError("Bạn chưa nhập địa chỉ tạm trú tạm vắng!","Mời bạn nhập lại");
      } else {
          khaiBaoTamTruTamVang.setDiaChiTamTruTamVang(DiaChiTamTruTamVangField.getText());
      }

      khaiBaoTamTruTamVang.setLyDo(LyDoTextField.getText());

      if(count == 0) {
          ShowAlert.showAlertYN("Lưu lại", "Bạn đã kiểm tra kĩ mọi thông tin và muốn lưu lại?");
          KhaiBaoTamTruTamVangStatic.themKhaiBaoTamTruTamVang(khaiBaoTamTruTamVang);
          DBUtils.themKhaiBaoTamTruTamVang(khaiBaoTamTruTamVang);
          dsKhaiBaoTamTruTamVang.getItems().clear();
          setTable();
          clearData();
      }
    }





    // huy bo du lieu
    public void clearData(){
        LoaiChoiceBox.getSelectionModel().clearSelection();
        HoTenTextField.clear();
        NgaySinhDatePicker.getEditor().clear();
        GioiTinhChoiceBox.getSelectionModel().clearSelection();
        QuocTichTextField.clear();
        SoCCCDTextField.clear();
        NgayDangKyDatePicker.getEditor().clear();
        NgayKetThucDatePicker.getEditor().clear();
        DiaChiThuongTruTextField.clear();
        DiaChiTamTruTamVangField.clear();
        LyDoTextField.clear();

    }

    public void reset (Event event) throws Exception {
        clearData();
        dsKhaiBaoTamTruTamVang.getItems().clear();
        setTable();
    }









    //tim kiem nhan khau
    public TextField SearchTextField;
    public Button SearchButton;

    public void search(Event event) throws Exception{

        if (SearchTextField.getText().length() == 0) {
            ShowAlert.showAlertError("Mời bạn nhập  id hoặc họ tên!", "Mời bạn nhập lại");
        } else if(!SearchTextField.getText().equals("*")) {
            loadSearchDataFromDBQLTTTV();
        }else {
            setTable();
        }

    }
    //load du lieu co dk vao bang

    private void loadSearchDataFromDBQLTTTV() {
        ObservableList<KhaiBaoTamTruTamVang> listKhaiBaoTamTruTamVangCoDK = FXCollections.observableArrayList();
        listKhaiBaoTamTruTamVangCoDK = KhaiBaoTamTruTamVangStatic.getPartRecords(SearchTextField.getText());
        dsKhaiBaoTamTruTamVang.setItems(listKhaiBaoTamTruTamVangCoDK);
    }











    // Hiển thị thông tin nhân khẩu tạm trú tạm vắng

    @FXML
    public TableView dsKhaiBaoTamTruTamVang;
    public TableColumn<KhaiBaoTamTruTamVang, Integer> IdKhaiBao;
    public TableColumn<KhaiBaoTamTruTamVang, String> Loai;
    public TableColumn<KhaiBaoTamTruTamVang, String> HoTen;
//    public TableColumn<KhaiBaoTamTruTamVang, Date> NgaySinh;
//    public TableColumn<KhaiBaoTamTruTamVang, String> GioiTinh;
//    public TableColumn<KhaiBaoTamTruTamVang, String> QuocTich;
//    public TableColumn<KhaiBaoTamTruTamVang, String> SoCCCD;
    public TableColumn<KhaiBaoTamTruTamVang, Date> NgayDangKy;
    public TableColumn<KhaiBaoTamTruTamVang, Date> NgayKetThuc;
//    public TableColumn<KhaiBaoTamTruTamVang, String> DiaChiThuongTru;
    public TableColumn<KhaiBaoTamTruTamVang, String> DiaChiTamTruTamVang;
    public TableColumn<KhaiBaoTamTruTamVang, String> LyDo;

    public void setTable() {
        try {
            DBUtils.loadDsTamTruTamVang();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        IdKhaiBao.setCellValueFactory(new PropertyValueFactory<KhaiBaoTamTruTamVang, Integer>("IdKhaiBao"));
        Loai.setCellValueFactory(new PropertyValueFactory<KhaiBaoTamTruTamVang, String>("Loai"));
        HoTen.setCellValueFactory(new PropertyValueFactory<KhaiBaoTamTruTamVang, String>("HoTen"));
        NgayDangKy.setCellValueFactory(new PropertyValueFactory<KhaiBaoTamTruTamVang, Date>("NgayDangKy"));
        NgayKetThuc.setCellValueFactory(new PropertyValueFactory<KhaiBaoTamTruTamVang, Date>("NgayKetThuc"));
        DiaChiTamTruTamVang.setCellValueFactory(new PropertyValueFactory<KhaiBaoTamTruTamVang, String>("DiaChiTamTruTamVang"));
        LyDo.setCellValueFactory(new PropertyValueFactory<KhaiBaoTamTruTamVang, String>("LyDo"));
        dsKhaiBaoTamTruTamVang.setItems(KhaiBaoTamTruTamVangStatic.getDsKhaiBaoTamTruTamVang());
        for (KhaiBaoTamTruTamVang e: KhaiBaoTamTruTamVangStatic.getDsKhaiBaoTamTruTamVang()) {
            System.out.println(e.toString());
        }
        if (KhaiBaoTamTruTamVangStatic.getDsKhaiBaoTamTruTamVang().isEmpty()) {
            System.out.println("EMPTY");
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTable();
        LoaiChoiceBox.getItems().addAll(listLoaiChoiceBox);
        GioiTinhChoiceBox.getItems().addAll(listGioiTinhChoiceBox);

    }

    //


    
}
