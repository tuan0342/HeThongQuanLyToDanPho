package controller;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import model.*;
import org.w3c.dom.Text;

import java.awt.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class ThemChiTietKhoanThuController implements Initializable {

    public static Scene preScene;
    public static Scene getPreScene() {
        return preScene;
    }
    public static void setPreScene(Scene preScene) {
        ThemChiTietKhoanThuController.preScene = preScene;
    }

    @FXML
    private TextField tfIdKhoanThuAdd;
    @FXML
    private TextField tfIdHoKhauAdd;
    @FXML
    private TextField tfIdKhoanThuUpdate;
    @FXML
    private TextField tfIdHoKhauUpdate;
    @FXML
    private TextField tfSearch;
    @FXML
    private ChoiceBox choiceBoxTrangThai;
    @FXML
    Button btnCancel;
    @FXML
    Button btnUpdate;
    @FXML
    Button btnSearch;

    private String[] listChoiceBox = {"Đã đóng", "Chưa đóng"};


    // Bảng chi tiết khoản thu
    @FXML
    TableView<ChiTietKhoanThu> dsChiTietKhoanThu;
    @FXML
    public TableColumn<ChiTietKhoanThu, Integer> colIdKhoanThu;
    @FXML
    public TableColumn<ChiTietKhoanThu, String> colIdHK;
    @FXML
    public TableColumn<ChiTietKhoanThu, String> colTen;
    @FXML
    public TableColumn<ChiTietKhoanThu, Integer> colSoTien;
    @FXML
    public TableColumn<ChiTietKhoanThu, Integer> colTT;
    ObservableList<ChiTietKhoanThu> listChiTietKhoanThu = null;


    // Các thành phần trong bảng hộ khẩu
    @FXML
    TableView<HoKhau> dsHoKhau;
    @FXML
    public TableColumn<HoKhau, String> colIdHoKhau;
    @FXML
    public TableColumn<HoKhau, String> colTenChuHo;
    @FXML
    public TableColumn<HoKhau, String> colDiaChi;
    @FXML
    public TableColumn<HoKhau, Integer> colSLNK;
    ObservableList<HoKhau> listHoKhau = null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        choiceBoxTrangThai.getItems().addAll(listChoiceBox);
        choiceBoxTrangThai.setValue("Chưa đóng");

        // Gõ enter sẽ chuyển sang nhập trường dữ liệu tiếp theo
        tfIdKhoanThuAdd.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)) {
                tfIdHoKhauAdd.requestFocus();
            }
        });

        // bảng chi tiết khoản thu
        setCellTableChiTietKhoanThu();
        loadDataFromDBCTKT();

        // bảng hộ khẩu
        setCellTableKHoKhau();
        loadDataFromDBHoKhau();

        // sự kiện khi click vào 1 hàng trong bảng chi tiết khoản thu
        dsChiTietKhoanThu.setRowFactory(tv -> {
            TableRow<ChiTietKhoanThu> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    btnCancel.setDisable(false);
                    btnUpdate.setDisable(false);
                    ChiTietKhoanThu clickedRow = row.getItem();
                    tfIdKhoanThuUpdate.setText(String.valueOf(clickedRow.getIdKhoanThu()));
                    tfIdHoKhauUpdate.setText(clickedRow.getIdHoKhau());
                    printRow(clickedRow);
                }
            });
            return row ;
        });

    }

    // hiển thị thông tin của 1 hàng khi click vào hàng đó
    private void printRow(ChiTietKhoanThu clickedRow) {
        System.out.println(clickedRow);
    }

    // cập nhật bảng chi tiết khoản thu (đã đóng hay chưa)
    @FXML
    public void update(Event event) throws SQLException, ClassNotFoundException {
        int tt = convertTrangThai();
        ChiTietKhoanThuStatic.updateChiTietKhoanThu(Integer.valueOf(tfIdKhoanThuUpdate.getText()),
                tfIdHoKhauUpdate.getText(), tt);
        setCellTableChiTietKhoanThu();
        loadDataFromDBCTKT();
    }

    // convert dữ liệu ("Đã đóng" về 1 và ngược lại)
    private int convertTrangThai() {
        String text = (String) choiceBoxTrangThai.getValue();
        if (text.equals("Đã đóng")) return 1;
        return 0;
    }

    @FXML
    public void cancel (Event event) {
        btnCancel.setDisable(true);
        btnUpdate.setDisable(true);
        tfIdKhoanThuUpdate.clear();
        tfIdHoKhauUpdate.clear();
        choiceBoxTrangThai.setValue("Chưa đóng");
    }

    @FXML
    public void add (Event event) throws SQLException, ClassNotFoundException {
        if (checkIsEmpty() == 0) {

            String text = "Nhập đầy đủ thông tin";
            String title = "Lỗi";
            String header = null;
            showAlertError(text, title, header);
        } else {

            ChiTietKhoanThuStatic.insertChiTietKhoanThu(Integer.valueOf(tfIdKhoanThuAdd.getText()), tfIdHoKhauAdd.getText());
            System.out.println("Thêm khoản thu mới thành công!");
            clearTextAdd();
            setCellTableChiTietKhoanThu();
            loadDataFromDBCTKT();
        }
    }

    @FXML
    public void search (Event event) {
        setCellTableChiTietKhoanThu();
        String idKTSearch = tfSearch.getText();
        if (idKTSearch.isEmpty()) {
            showAlertError("Nhập thông tin", "Cảnh báo", null);
        }
        else if (!idKTSearch.equals("*")) {
            loadSearchDataFromDBCTKT(Integer.valueOf(tfSearch.getText()));
        } else {
            loadDataFromDBCTKT();
        }
        tfSearch.clear();
    }

    // load dữ liệu có điều kiện (idKhoanThu) vào bảng Chi tiết khoản thu
    private void loadSearchDataFromDBCTKT(int idKhoanThu) {
        listChiTietKhoanThu = ChiTietKhoanThuStatic.getPartRecords(idKhoanThu);
        dsChiTietKhoanThu.setItems(listChiTietKhoanThu);
    }

    // load dữ liệu vào bảng Chi tiết khoản thu
    private void loadDataFromDBCTKT() {
        listChiTietKhoanThu = ChiTietKhoanThuStatic.getAlLRecords();
        dsChiTietKhoanThu.setItems(listChiTietKhoanThu);
    }

    // set cột chi tiết khoản thu
    private void setCellTableChiTietKhoanThu() {
        colIdKhoanThu.setCellValueFactory(new PropertyValueFactory<>("idKhoanThu"));
        colIdHK.setCellValueFactory(new PropertyValueFactory<>("idHoKhau"));
        colTen.setCellValueFactory(new PropertyValueFactory<>("tenChuHo"));
        colSoTien.setCellValueFactory(new PropertyValueFactory<>("soTien"));
        colTT.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
    }

    // load dữ liệu vào bảng Hộ khẩu
    private void loadDataFromDBHoKhau() {
        listHoKhau = HoKhauStatic.getAlLRecords();
        dsHoKhau.setItems(listHoKhau);
    }

    // set cột hộ khẩu
    private void setCellTableKHoKhau() {
        colIdHoKhau.setCellValueFactory(new PropertyValueFactory<>("idHoKhau"));
        colTenChuHo.setCellValueFactory(new PropertyValueFactory<>("tenChuHo"));
        colDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        colSLNK.setCellValueFactory(new PropertyValueFactory<>("soLuongNhanKhau"));
    }

    // clear text trên textfield
    private void clearTextAdd() {
        tfIdHoKhauAdd.clear();
        tfIdKhoanThuAdd.clear();
    }

    // kiểm tra các dữ liệu được nhập đủ chưa
    private int checkIsEmpty() {
        if(tfIdHoKhauAdd.getText().isEmpty() || tfIdKhoanThuAdd.getText().isEmpty()) {
            return 0;
        }
        return 1;
    }

    // quay về màn hình trước
    @FXML
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
