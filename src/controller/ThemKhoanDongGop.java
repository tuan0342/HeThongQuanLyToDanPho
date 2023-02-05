package controller;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import model.DongGop;
import model.DongGopStatic;
import model.KhoanThuSatic;

import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;


public class ThemKhoanDongGop implements Initializable {
    public static Scene preScene;
    public static Scene getPreScene() {
        return preScene;
    }
    public static void setPreScene(Scene preScene) {
        ThemKhoanDongGop.preScene = preScene;
    }

    @FXML
    public Button Back;
    @FXML
    public Button Add;

    @FXML
    public TextField tftenDG;
    @FXML
    public TextField tfngayBDDG;
    @FXML
    public TextField tfngayKTDG;

    // Bảng đóng góp
    @FXML
    TableView<DongGop> dsDongGop;
    @FXML
    public TableColumn<DongGop, Integer> colIdDG;
    @FXML
    public TableColumn<DongGop, String> colTenDG;
    @FXML
    public TableColumn<DongGop, String> colNgayBDDG;
    @FXML
    public TableColumn<DongGop, String> colNgayKTDG;

    ObservableList<DongGop> listDongGop = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Gõ enter sẽ chuyển sang nhập trường dữ liệu tiếp theo
        tftenDG.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)) {
                tfngayBDDG.requestFocus();
            }
        });
        tfngayBDDG.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)) {
                tfngayKTDG.requestFocus();
            }
        });

        setCellTable();  // set cột cho bảng
        loadDataFromDB(); // load dữ liệu vào bảng
    }


    @FXML
    public void back (Event event) {
        DBUtils.changeScene(getPreScene(), event);
    }

    @FXML
    public void add (Event event) throws SQLException, ClassNotFoundException {

        if (checkIsEmpty() == 0) {

            String text = "Nhập đầy đủ thông tin";
            String title = "Lỗi";
            String header = null;
            ShowAlert.showAlertError(header, text);
        } else {

            if(KhoanThuSatic.isValidFormat("dd/MM/yyyy", tfngayBDDG.getText(), Locale.ENGLISH) == false) {

                String text = "Sai định dạng dd/MM/yyyy";
                showAlertError(text, "Lỗi", null);
            } else if (KhoanThuSatic.isValidFormat("dd/MM/yyyy", tfngayKTDG.getText(), Locale.ENGLISH) == false) {

                String text = "Sai định dạng dd/MM/yyyy";
                showAlertError(text, "Lỗi", null);
            } else {

                DongGopStatic.insertDongGop(tftenDG.getText(), KhoanThuSatic.formatDate(tfngayBDDG.getText()),
                        KhoanThuSatic.formatDate(tfngayKTDG.getText()));
                System.out.println("Thêm khoản thu mới thành công!");
                clearText();
                loadDataFromDB();
                setCellTable();
            }
        }
    }

    private void loadDataFromDB() {
        listDongGop = DongGopStatic.getAlLRecords();
        dsDongGop.setItems(listDongGop);
    }

    private void setCellTable() {
        colIdDG.setCellValueFactory(new PropertyValueFactory<>("idKhoanDG"));
        colTenDG.setCellValueFactory(new PropertyValueFactory<>("tenKhoanDG"));
        colNgayBDDG.setCellValueFactory(new PropertyValueFactory<>("ngayBD"));
        colNgayKTDG.setCellValueFactory(new PropertyValueFactory<>("ngayKT"));
    }


    // kiểm tra các dữ liệu được nhập đủ chưa
    private int checkIsEmpty() {
        if(tftenDG.getText().isEmpty() || tfngayBDDG.getText().isEmpty() || tfngayKTDG.getText().isEmpty()) {
            return 0;
        }
        return 1;
    }

    // clear text trên textfield
    private void clearText() {
        tftenDG.clear();
        tfngayBDDG.clear();
        tfngayKTDG.clear();
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
