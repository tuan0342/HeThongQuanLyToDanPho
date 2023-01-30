package controller;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import model.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ThemChiTietDongGopController implements Initializable {

    public static Scene preScene;
    public static Scene getPreScene() {
        return preScene;
    }
    public static void setPreScene(Scene preScene) {
        ThemChiTietDongGopController.preScene = preScene;
    }

    @FXML
    private TextField tfIdDGAdd;
    @FXML
    private TextField tfIdHoKhauAdd;
    @FXML
    private TextField tfSoTienAdd;

    @FXML
    private TextField tfIdDGUpdate;
    @FXML
    private TextField tfIdHoKhauUpdate;
    @FXML
    private TextField tfSoTienUpdate;

    @FXML
    private TextField tfSearch;

    @FXML
    Button btnCancel;
    @FXML
    Button btnUpdate;
    @FXML
    Button btnSearch;


    // Bảng chi tiết khoản thu
    @FXML
    TableView<ChiTietDongGop> dsChiTietDG;
    @FXML
    public TableColumn<ChiTietDongGop, Integer> colIdDG;
    @FXML
    public TableColumn<ChiTietDongGop, String> colIdHK;
    @FXML
    public TableColumn<ChiTietDongGop, String> colTen;
    @FXML
    public TableColumn<ChiTietDongGop, Integer> colSoTien;

    ObservableList<ChiTietDongGop> listChiTietDG = null;

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

        // Gõ enter sẽ chuyển sang nhập trường dữ liệu tiếp theo
        tfIdDGAdd.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)) {
                tfIdHoKhauAdd.requestFocus();
            }
        });
        tfIdHoKhauAdd.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)) {
                tfSoTienAdd.requestFocus();
            }
        });

        // bảng chi tiết khoản thu
        setCellTableChiTietKhoanThu();
        loadDataFromDBCTKT();

        // bảng hộ khẩu
        setCellTableKHoKhau();
        loadDataFromDBHoKhau();

        // sự kiện khi click vào 1 hàng trong bảng chi tiết khoản thu
        dsChiTietDG.setRowFactory(tv -> {
            TableRow<ChiTietDongGop> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    btnCancel.setDisable(false);
                    btnUpdate.setDisable(false);
                    tfSoTienUpdate.setDisable(false);
                    ChiTietDongGop clickedRow = row.getItem();
                    tfIdDGUpdate.setText(String.valueOf(clickedRow.getIdDongGop()));
                    tfIdHoKhauUpdate.setText(clickedRow.getIdHoKhau());
                    tfSoTienUpdate.setText(String.valueOf(clickedRow.getSoTien()));
                    printRow(clickedRow);
                }
            });
            return row ;
        });
    }

    // hiển thị thông tin của 1 hàng khi click vào hàng đó
    private void printRow(ChiTietDongGop clickedRow) {
        System.out.println(clickedRow);
    }

    // thêm chi tiết khoản thu
    @FXML
    public void add (Event event) throws SQLException, ClassNotFoundException {
        if (checkIsEmpty() == 0) {
            String text = "Nhập đầy đủ thông tin";
            String title = "Lỗi";
            String header = null;
            showAlertError(text, title, header);
        } else {
            ChiTietDongGopStatic.insertChiTietDG(Integer.valueOf(tfIdDGAdd.getText()), tfIdHoKhauAdd.getText(), tfSoTienAdd.getText());
            System.out.println("Thêm khoản thu mới thành công!");
            clearTextAdd();
            setCellTableChiTietKhoanThu();
            loadDataFromDBCTKT();
        }
    }

    // quay về màn hình trước
    @FXML
    public void back (Event event) {
        DBUtils.changeScene(getPreScene(), event);
    }

    // cập nhật bảng chi tiết khoản thu (đã đóng hay chưa)
    @FXML
    public void update(Event event) throws SQLException, ClassNotFoundException {
        ChiTietDongGopStatic.updateChiTietDG(Integer.valueOf(tfIdDGUpdate.getText()),
                tfIdHoKhauUpdate.getText(), tfSoTienUpdate.getText());
        setCellTableChiTietKhoanThu();
        loadDataFromDBCTKT();
    }

    // sự kiện khi nhấn cancel
    @FXML
    public void cancel (Event event) {
        btnCancel.setDisable(true);
        btnUpdate.setDisable(true);
        tfIdDGUpdate.clear();
        tfIdHoKhauUpdate.clear();
    }

    // tìm kiếm theo id khoản đóng góp
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

    // load dữ liệu có điều kiện (idDG) vào bảng Chi tiết đóng góp
    private void loadSearchDataFromDBCTKT(int idDG) {
        listChiTietDG = ChiTietDongGopStatic.getPartRecords(idDG);
        dsChiTietDG.setItems(listChiTietDG);
    }

    // load dữ liệu vào bảng Chi tiết đóng góp
    private void loadDataFromDBCTKT() {
        listChiTietDG = ChiTietDongGopStatic.getAlLRecords();
        dsChiTietDG.setItems(listChiTietDG);
    }

    // set cột chi tiết khoản thu
    private void setCellTableChiTietKhoanThu() {
        colIdDG.setCellValueFactory(new PropertyValueFactory<>("idDongGop"));
        colIdHK.setCellValueFactory(new PropertyValueFactory<>("idHoKhau"));
        colTen.setCellValueFactory(new PropertyValueFactory<>("tenChuHo"));
        colSoTien.setCellValueFactory(new PropertyValueFactory<>("soTien"));
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
        tfIdDGAdd.clear();
        tfIdHoKhauAdd.clear();
        tfSoTienAdd.clear();
    }

    // kiểm tra các dữ liệu được nhập đủ chưa
    private int checkIsEmpty() {
        if(tfIdDGAdd.getText().isEmpty() || tfIdHoKhauAdd.getText().isEmpty()) {
            return 0;
        }
        return 1;
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
