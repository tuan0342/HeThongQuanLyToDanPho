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
import model.ThongKeDongGop;
import model.ThongKeDongGopStatic;

import java.net.URL;
import java.util.ResourceBundle;

public class ThongKeDongGopController implements Initializable {

    public static Scene preScene;
    public static Scene getPreScene() {
        return preScene;
    }
    public static void setPreScene(Scene preScene) {
        ThongKeDongGopController.preScene = preScene;
    }

    @FXML
    TableView<ThongKeDongGop> dsThongKeKhoanDG;

    @FXML
    Button btnBack;

    @FXML
    public TableColumn<ThongKeDongGop, String> colTenKhoanDG;
    @FXML
    public TableColumn<ThongKeDongGop, String> colSoHo;
    @FXML
    public TableColumn<ThongKeDongGop, String> colSoTien;


    ObservableList<ThongKeDongGop> listThongKe = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellTable();
        loadDataFromDB();
    }

    // load dữ liệu vào bảng Chi tiết khoản thu
    private void loadDataFromDB() {
        listThongKe = ThongKeDongGopStatic.getAllThongKe();
        dsThongKeKhoanDG.setItems(listThongKe);
    }

    // set cột chi tiết khoản thu
    private void setCellTable() {
        colTenKhoanDG.setCellValueFactory(new PropertyValueFactory<>("tenKhoanDG"));
        colSoHo.setCellValueFactory(new PropertyValueFactory<>("tongSoHo"));
        colSoTien.setCellValueFactory(new PropertyValueFactory<>("tongSoTien"));
    }

    @FXML
    public void backT (Event event) {
        DBUtils.changeScene(getPreScene(), event);
    }
}
