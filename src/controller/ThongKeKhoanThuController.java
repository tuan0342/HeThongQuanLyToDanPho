package controller;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ChiTietKhoanThuStatic;
import model.KhoanThu;
import model.ThongKeKhoanThu;
import model.ThongKeKhoanThuStatic;

import java.net.URL;
import java.util.ResourceBundle;

public class ThongKeKhoanThuController implements Initializable {

    public static Scene preScene;
    public static Scene getPreScene() {
        return preScene;
    }
    public static void setPreScene(Scene preScene) {
        ThongKeKhoanThuController.preScene = preScene;
    }

    @FXML
    Button back;

    @FXML
    TableView<ThongKeKhoanThu> dsThongKeKhoanThu;

    @FXML
    public TableColumn<ThongKeKhoanThu, String> colTenKhoanThu;
    @FXML
    public TableColumn<ThongKeKhoanThu, String> colTienDaThu;
    @FXML
    public TableColumn<ThongKeKhoanThu, String> colHoDaThu;
    @FXML
    public TableColumn<ThongKeKhoanThu, String> colTienChuaThu;
    @FXML
    public TableColumn<ThongKeKhoanThu, String> colHoChuaThu;
    @FXML
    public TableColumn<ThongKeKhoanThu, String> colTongHo;
    @FXML
    public TableColumn<ThongKeKhoanThu, String> colTien1Ho;

    ObservableList<ThongKeKhoanThu> listThongKe = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellTable();
        loadDataFromDB();
    }

    // load dữ liệu vào bảng Chi tiết khoản thu
    private void loadDataFromDB() {
        listThongKe = ThongKeKhoanThuStatic.getAllThongKe();
        dsThongKeKhoanThu.setItems(listThongKe);
    }

    // set cột chi tiết khoản thu
    private void setCellTable() {
        colTenKhoanThu.setCellValueFactory(new PropertyValueFactory<>("tenKT"));
        colTienDaThu.setCellValueFactory(new PropertyValueFactory<>("tienDaThu"));
        colHoDaThu.setCellValueFactory(new PropertyValueFactory<>("soHoDaThu"));
        colTienChuaThu.setCellValueFactory(new PropertyValueFactory<>("tienChuaThu"));
        colHoChuaThu.setCellValueFactory(new PropertyValueFactory<>("soHoChuaThu"));
        colTongHo.setCellValueFactory(new PropertyValueFactory<>("tongHo"));
        colTien1Ho.setCellValueFactory(new PropertyValueFactory<>("soTien1Ho"));
    }

    @FXML
    public void back (Event event) {
        DBUtils.changeScene(getPreScene(), event);
    }

}
