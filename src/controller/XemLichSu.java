package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.Event;
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
    public TextField dienIdHoKhau;
    @FXML
    public Button Tim;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idLichSu.setCellValueFactory(new PropertyValueFactory<LichSu, String>("idLichSu"));
        idHoKhau.setCellValueFactory(new PropertyValueFactory<LichSu, String>("idHoKhau"));
        noiDung.setCellValueFactory(new PropertyValueFactory<LichSu, String>("noiDungThayDoi"));
        ngayThang.setCellValueFactory(new PropertyValueFactory<LichSu, String>("ngayThang"));
        dsLichSu.setItems(LichSuStatic.getDsLichSu());
    }

    public void Back (Event event) {
        DBUtils.changeScene(getPreScene(), event);
    }

    public void Tim (Event event) {
        if(dienIdHoKhau.getText() != null) {
            String idHoKhau = dienIdHoKhau.getText();
            ObservableList<LichSu> dsCon = LichSuStatic.getDsLichSu().filtered(node -> node.timTheoIdHoKhau(idHoKhau));
            if (!dsCon.isEmpty()) {
                dsLichSu.setItems(dsCon);
            } else {
                ShowAlert.showAlertError("Không tồn tại lịch sử cho hộ khẩu này", "Điền lại");
                dsLichSu.setItems(LichSuStatic.getDsLichSu());
            }
        } else {
            ShowAlert.showAlertError("Đéo điền gì thì tìm cái gì", "Điền lại");
            dsLichSu.setItems(LichSuStatic.getDsLichSu());
        }
    }
}
