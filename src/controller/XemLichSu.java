package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.Event;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import model.LichSu;
import model.LichSuStatic;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class XemLichSu implements Initializable {
    private Scene preScene;
    public Scene getPreScene() {
        return preScene;
    }
    public void setPreScene(Scene preScene) {
        this.preScene = preScene;
    }

    @FXML
    public Button Back;
    @FXML
    public TableView dsLichSu;
    @FXML
    public TableColumn<LichSu, String> idLichSu;
    @FXML
    public TableColumn<LichSu, String> idHoKhau;
    @FXML
    public TableColumn<LichSu, String> noiDung;

    @FXML
    public TableColumn<LichSu, String> ngayThang;
    @FXML
    public TableColumn<LichSu, String> thaoTac;
    @FXML
    public TextField dienIdHoKhau;
    @FXML
    public Button Tim;
    @FXML
    public ComboBox<String> thaoTacCanTim;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idLichSu.setCellValueFactory(new PropertyValueFactory<LichSu, String>("idLichSu"));
        idHoKhau.setCellValueFactory(new PropertyValueFactory<LichSu, String>("idHoKhau"));
        noiDung.setCellValueFactory(new PropertyValueFactory<LichSu, String>("noiDungThayDoi"));
        noiDung.setCellFactory(tc -> {
            TableCell<LichSu, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(noiDung.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell ;
        });
        ngayThang.setCellValueFactory(new PropertyValueFactory<LichSu, String>("ngayThang"));
        thaoTac.setCellValueFactory(new PropertyValueFactory<LichSu, String>("thaoTac"));
        dsLichSu.setItems(LichSuStatic.getDsLichSu());
        ObservableList<String> danhSach = FXCollections.observableArrayList("Xóa",
                "Thêm Nhân Khẩu", "Thêm Hộ Khẩu", "Sửa Nhân Khẩu");
        thaoTacCanTim.setItems(danhSach);
    }

    public void Back (Event event) {
        DBUtils.changeScene(getPreScene(), event);
    }

    public void Tim (Event event) {
        String idHoKhau = dienIdHoKhau.getText();
        String thaoTac = thaoTacCanTim.getValue();
        ObservableList<LichSu> dsCon = LichSuStatic.getDsLichSu().
                filtered(node -> node.timTheoIdVaThaoTac(idHoKhau, thaoTac));
        if (!dsCon.isEmpty()) {
            dsLichSu.setItems(dsCon);
        } else {
            ShowAlert.showAlertError("Không tồn tại lịch sử cho hộ khẩu này", "Điền lại");
            dsLichSu.setItems(LichSuStatic.getDsLichSu());
        }
    }
}
