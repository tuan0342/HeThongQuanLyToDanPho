package controller;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import model.DongGop;
import model.DongGopStatic;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class QuanLyKhoanDongGop implements Initializable {
    //Lien quan den Scene truoc Scene sau
    private static Scene preScene;
    private static Scene curScene;

    public static Scene getPreScene() {
        return preScene;
    }

    public static void setPreScene(Scene preScene) {
        QuanLyKhoanDongGop.preScene = preScene;
    }

    public static Scene getCurScene() {
        return curScene;
    }

    public static void setCurScene(Scene curScene) {
        QuanLyKhoanDongGop.curScene = curScene;
    }

    //Button quay lai
    @FXML
    public Button Back;
    @FXML
    public Button CapNhatDGQLKT;
    @FXML
    public Button btnLoad;
    @FXML
    public TextField idDG;
    @FXML
    public TextField tenDG;
    @FXML
    public TextField ngayBDDG;
    @FXML
    public TextField ngayKTDG;

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
        setCellTable();  // set cột cho bảng
        loadDataFromDB(); // load dữ liệu vào bảng

        btnLoad.setOnAction(event -> {
            setCellTable();  // set cột cho bảng
            loadDataFromDB(); // load dữ liệu vào bảng
        });

        dsDongGop.setRowFactory(tv -> {
            TableRow<DongGop> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    CapNhatDGQLKT.setDisable(false);
                    tenDG.setDisable(false);
                    ngayBDDG.setDisable(false);
                    ngayKTDG.setDisable(false);

                    DongGop clickedRow = row.getItem();
                    idDG.setText(String.valueOf(clickedRow.getIdKhoanDG()));
                    tenDG.setText(clickedRow.getTenKhoanDG());
                    ngayBDDG.setText(clickedRow.getNgayBD());
                    ngayKTDG.setText(clickedRow.getNgayKT());

                }
            });
            return row ;
        });
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

    public void Back (Event event) {
        DBUtils.changeScene(preScene, event);
    }

    // thêm khoản đóng góp
    @FXML
    public void ThemKhoanDongGop (Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ThemKhoanDongGop.class.getResource("/view/fxml/Them_Khoan_Dong_Gop.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 695, 600);
        controller.ThemKhoanDongGop.setPreScene(getCurScene());
        DBUtils.changeScene(scene, event);
    }

    // update khoản thu
    @FXML
    public void updateDG (Event event) throws SQLException, ClassNotFoundException {
        DongGopStatic.updateDG(Integer.valueOf(idDG.getText()), tenDG.getText(), ngayBDDG.getText(),
                ngayKTDG.getText());

        tenDG.setDisable(true);
        ngayBDDG.setDisable(true);
        ngayKTDG.setDisable(true);
        CapNhatDGQLKT.setDisable(true);

        idDG.clear();
        tenDG.clear();
        ngayBDDG.clear();
        ngayKTDG.clear();

        setCellTable();  // set cột cho bảng
        loadDataFromDB(); // load dữ liệu vào bảng
    }

    // chuyển sang màn hình thêm chi tiết đóng góp
    @FXML
    public void themChiTietDongGop (Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ThemChiTietDongGopController.class.getResource("/view/fxml/ThemChiTietDongGop.fxml"));
        AnchorPane Them = fxmlLoader.load();
        controller.ThemChiTietDongGopController.setPreScene(this.curScene);
        Scene scene = new Scene(Them, 937, 600);
        scene.setFill(Color.GRAY);
        DBUtils.changeScene(scene, event);
    }

    // chuyển sang màn hình thống kê
    @FXML
    public void thongKeKhoanDG (Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ThongKeDongGopController.class.getResource("/view/fxml/ThongKeDongGop.fxml"));
        AnchorPane Them = fxmlLoader.load();
        controller.ThongKeDongGopController.setPreScene(this.curScene);
        Scene scene = new Scene(Them, 900, 600);
        scene.setFill(Color.GRAY);
        DBUtils.changeScene(scene, event);
    }

}
