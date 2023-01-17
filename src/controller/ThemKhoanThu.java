package controller;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import model.HoKhau;
import model.HoKhauStatic;
import model.KhoanThu;
import model.KhoanThuSatic;

import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class ThemKhoanThu implements Initializable {
    public static Scene preScene;
    public static Scene getPreScene() {
        return preScene;
    }
    public static void setPreScene(Scene preScene) {
        ThemKhoanThu.preScene = preScene;
    }
    @FXML
    public Button Add;
    @FXML
    public Button Back;
    @FXML
    public TextField tftenKhoanThu;
    @FXML
    public TextField tfngayBD;
    @FXML
    public TextField tfngayKT;
    @FXML
    public TextField tfsoTien;

    // Bảng khoản thu
    @FXML
    TableView<KhoanThu> dsKhoanThu;
    @FXML
    public TableColumn<KhoanThu, Integer> colIdKhoanThu;
    @FXML
    public TableColumn<KhoanThu, String> colTenKhoanThu;
    @FXML
    public TableColumn<KhoanThu, String> colNgayBD;
    @FXML
    public TableColumn<KhoanThu, String> colNgayKT;
    @FXML
    public TableColumn<KhoanThu, Integer> colSoTien;
    ObservableList<KhoanThu> listKhoanThu = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Gõ enter sẽ chuyển sang nhập trường dữ liệu tiếp theo
        tftenKhoanThu.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)) {
                tfngayBD.requestFocus();
            }
        });
        tfngayBD.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)) {
                tfngayKT.requestFocus();
            }
        });
        tfngayKT.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)) {
                tfsoTien.requestFocus();
            }
        });

        setCellTableKhoanThu();
        loadDataFromDBKhoanThu();
    }

    // load dữ liệu vào bảng Khoản Thu
    private void loadDataFromDBKhoanThu() {
        listKhoanThu = KhoanThuSatic.getAlLRecords();
        dsKhoanThu.setItems(listKhoanThu);
    }

    // set cột khoản thu
    private void setCellTableKhoanThu() {
        colIdKhoanThu.setCellValueFactory(new PropertyValueFactory<>("idKhoanThu"));
        colTenKhoanThu.setCellValueFactory(new PropertyValueFactory<>("tenKhoanThu"));
        colNgayBD.setCellValueFactory(new PropertyValueFactory<>("ngayBD"));
        colNgayKT.setCellValueFactory(new PropertyValueFactory<>("ngayKT"));
        colSoTien.setCellValueFactory(new PropertyValueFactory<>("soTien"));
    }


    // kiểm tra các dữ liệu được nhập đủ chưa
    private int checkIsEmpty() {
        if(tfsoTien.getText().isEmpty() || tftenKhoanThu.getText().isEmpty() || tfngayKT.getText().isEmpty() ||
            tfngayBD.getText().isEmpty()) {
            return 0;
        }
        return 1;
    }

    // clear text trên textfield
    private void clearText() {
        tftenKhoanThu.clear();
        tfngayBD.clear();
        tfngayKT.clear();
        tfsoTien.clear();
    }

    @FXML
    public void add (Event event) throws SQLException, ClassNotFoundException {
        if (checkIsEmpty() == 0) {

            String text = "Nhập đầy đủ thông tin";
            String title = "Lỗi";
            String header = null;
            showAlertError(text, title, header);
        } else {

            if(KhoanThuSatic.isValidFormat("dd/MM/yyyy", tfngayBD.getText(), Locale.ENGLISH) == false) {

                String text = "Sai định dạng dd/MM/yyyy";
                showAlertError(text, "Lỗi", null);
            } else if (KhoanThuSatic.isValidFormat("dd/MM/yyyy", tfngayKT.getText(), Locale.ENGLISH) == false) {

                String text = "Sai định dạng dd/MM/yyyy";
                showAlertError(text, "Lỗi", null);
            } else {

                KhoanThuSatic.insertKhoanThu(tftenKhoanThu.getText(), KhoanThuSatic.formatDate(tfngayBD.getText()),
                        KhoanThuSatic.formatDate(tfngayKT.getText()), Integer.valueOf(tfsoTien.getText()));
                System.out.println("Thêm khoản thu mới thành công!");
                clearText();
                setCellTableKhoanThu();
                loadDataFromDBKhoanThu();
            }
        }
    }

    public void back (Event event) {
        DBUtils.changeScene(getPreScene(), event);
    }

    private void showAlertError(String text, String title, String header) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);

        ButtonType close = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(close);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == close) {
            alert.close();
        }
    }


}
