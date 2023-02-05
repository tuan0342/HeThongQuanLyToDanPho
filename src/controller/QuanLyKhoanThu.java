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
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.ChiTietKhoanThu;
import model.KhoanThu;
import model.KhoanThuSatic;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class QuanLyKhoanThu implements Initializable {
    //Lien quan den Scene truoc Scene sau
    private static Scene preScene;
    private static Scene curScene;

    public static Scene getPreScene() {
        return preScene;
    }

    public static void setPreScene(Scene preScene) {
        QuanLyKhoanThu.preScene = preScene;
    }

    public static Scene getCurScene() {
        return curScene;
    }

    public static void setCurScene(Scene curScene) {
        QuanLyKhoanThu.curScene = curScene;
    }

    //Button quay lai
    public Button Back;
    public void Back (Event event) {
        DBUtils.changeScene(preScene, event);
    }

    //Cac thanh phan trong Scene nay
    @FXML
    public HBox Khung;
    @FXML
    public AnchorPane CacButton;
    @FXML
    public AnchorPane KhungThongTin;
    public Button ThemKTQLKT;
    public Button ThayDoiKhoanThu;

    @FXML
    public Button btnLoad;

    @FXML
    public Button Them;
    @FXML
    public Button Return;

    @FXML
    private TextField tfIdKT;
    @FXML
    private TextField tfTenKT;
    @FXML
    private TextField tfNgayBD;
    @FXML
    private TextField tfNgayKT;
    @FXML
    private TextField tfSoTien;
    @FXML
    private Button CapNhatKTQLKT;

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
        setCellTable();  // set cột cho bảng
        loadDataFromDB(); // load dữ liệu vào bảng

        btnLoad.setOnAction(event -> {
            setCellTable();  // set cột cho bảng
            loadDataFromDB(); // load dữ liệu vào bảng
        });

        dsKhoanThu.setRowFactory(tv -> {
            TableRow<KhoanThu> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    CapNhatKTQLKT.setDisable(false);
                    tfTenKT.setDisable(false);
                    tfNgayBD.setDisable(false);
                    tfNgayKT.setDisable(false);
                    tfSoTien.setDisable(false);

                    KhoanThu clickedRow = row.getItem();
                    tfIdKT.setText(String.valueOf(clickedRow.getIdKhoanThu()));
                    tfTenKT.setText(clickedRow.getTenKhoanThu());
                    tfNgayBD.setText(clickedRow.getNgayBD());
                    tfNgayKT.setText(clickedRow.getNgayKT());
                    tfSoTien.setText(String.valueOf(clickedRow.getSoTien()));

                }
            });
            return row ;
        });
    }


    private void loadDataFromDB() {
        listKhoanThu = KhoanThuSatic.getAlLRecords();
        dsKhoanThu.setItems(listKhoanThu);
    }

    private void setCellTable() {
        colIdKhoanThu.setCellValueFactory(new PropertyValueFactory<>("idKhoanThu"));
        colTenKhoanThu.setCellValueFactory(new PropertyValueFactory<>("tenKhoanThu"));
        colNgayBD.setCellValueFactory(new PropertyValueFactory<>("ngayBD"));
        colNgayKT.setCellValueFactory(new PropertyValueFactory<>("ngayKT"));
        colSoTien.setCellValueFactory(new PropertyValueFactory<>("soTien"));
    }

    // chuyển sang màn hình thêm khoản thu
    public void themKhoanThu (Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ThemKhoanThu.class.getResource("/view/fxml/Them_Khoan_Thu.fxml"));
        AnchorPane Them = fxmlLoader.load();
        //Them.setTranslateX(300);
        //Them.setTranslateY(0);
        controller.ThemKhoanThu.setPreScene(this.curScene);
        Scene scene = new Scene(Them, 695, 600);
        scene.setFill(Color.GRAY);
        DBUtils.changeScene(scene, event);
    }

    // chuyển sang màn hình thêm chi tiết khoản thu
    public void themChiTietKhoanThu (Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ThemChiTietKhoanThuController.class.getResource("/view/fxml/ThemChiTietKhoanThu.fxml"));
        AnchorPane Them = fxmlLoader.load();
        //Them.setTranslateX(300);
        //Them.setTranslateY(0);
        controller.ThemChiTietKhoanThuController.setPreScene(this.curScene);
        Scene scene = new Scene(Them, 937, 600);
        scene.setFill(Color.GRAY);
        DBUtils.changeScene(scene, event);
    }

    // chuyển sang màn hình thống kê
    public void thongKeKhoanThu (Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ThongKeKhoanThuController.class.getResource("/view/fxml/ThongKeKhoanThu.fxml"));
        AnchorPane Them = fxmlLoader.load();
        //Them.setTranslateX(300);
        //Them.setTranslateY(0);
        controller.ThongKeKhoanThuController.setPreScene(this.curScene);
        Scene scene = new Scene(Them, 900, 600);
        scene.setFill(Color.GRAY);
        DBUtils.changeScene(scene, event);
    }

    // update khoản thu
    @FXML
    public void updateKhoanThu (Event event) throws SQLException, ClassNotFoundException {
        KhoanThuSatic.updateKhoanThu(Integer.valueOf(tfIdKT.getText()), tfTenKT.getText(), tfNgayBD.getText(),
                tfNgayKT.getText(), Integer.valueOf(tfSoTien.getText()));

        tfTenKT.setDisable(true);
        tfNgayBD.setDisable(true);
        tfNgayKT.setDisable(true);
        tfSoTien.setDisable(true);
        CapNhatKTQLKT.setDisable(true);
    }
}
