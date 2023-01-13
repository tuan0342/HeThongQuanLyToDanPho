package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.HoKhau;
import model.HoKhauStatic;
import model.NhanKhau;
import model.NhanKhauStatic;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ThemHoKhau implements Initializable {
    //Lien quan den Scene truoc Scene sau
    private static Scene preScene;
    private static Scene curScene;
    public static Scene getPreScene() {
        return preScene;
    }
    public static void setPreScene(Scene preScene) {
        ThemHoKhau.preScene = preScene;
    }
    public static Scene getCurScene() { return curScene; }
    public static void setCurScene(Scene curScene) {
        ThemHoKhau.curScene = curScene;
    }
    @FXML
    public Button Back;
    public void back (Event event) {
        DBUtils.changeScene(getPreScene(), event);
    }

    @FXML
    public Button Luu;
    //Cac thu khac co trong scene nay
    @FXML
    public TextField idHoKhau;
    @FXML
    public TextField diaChi;
    @FXML
    public Button taoChuHo;
    @FXML
    public Button themNhanKhau;
    @FXML
    public Label chuHoLabel;
    public TextField dienChuHo;
    public Label soLuongLabel;
    public TextField dienSoLuong;
    @FXML
    public TableView dsNhanKhau;
    @FXML
    public TableColumn<NhanKhau, String> idNhanKhau;
    @FXML
    public TableColumn<NhanKhau, String> hoTen;
    @FXML
    public TableColumn<NhanKhau, String> quanHeVoiChuHo;

    //Sau khi tao xong chu ho va ho khau
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setButtonExtra(false);
        setTableView();
    }

    private void showAlertErrorLogin(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Cánh báo");
        alert.setHeaderText("Nhập thông tin thất bại");
        alert.setContentText(text);

        ButtonType close = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(close);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == close) {
            alert.close();
        }
    }

    public void setTableView () {
        idNhanKhau.setCellValueFactory(new PropertyValueFactory<NhanKhau, String> ("idNhanKhau"));
        hoTen.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("hoTen"));
        quanHeVoiChuHo.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("quanHeChuHo"));
    }

    public void setButtonExtra (boolean key) {
        chuHoLabel.setVisible(key);
        dienChuHo.setVisible(key);
        soLuongLabel.setVisible(key);
        dienSoLuong.setVisible(key);
        themNhanKhau.setVisible(key);
        Luu.setVisible(key);
    }

    private HoKhau hoKhau = new HoKhau();
    public ObservableList<NhanKhau> dsNhanKhauCuaSceneNay = FXCollections.observableArrayList();
    public ObservableList<NhanKhau> getDsNhanKhauCuaSceneNay() {
        return dsNhanKhauCuaSceneNay;
    }
    public void setDsNhanKhauCuaSceneNay(ObservableList<NhanKhau> dsNhanKhauCuaSceneNay) {
        this.dsNhanKhauCuaSceneNay = dsNhanKhauCuaSceneNay;
    }

    private static int soLuong = 1;

    public void xongMenu (int i, NhanKhau nhanKhau) {
        getDsNhanKhauCuaSceneNay().add(nhanKhau);
        if (i == 1) {
            // set Nhan Khau la chu ho
            nhanKhau.setChuHo(1);
            soLuong = 1;
            dienChuHo.setText(nhanKhau.getHoTen());
            dienChuHo.setDisable(true);
            setButtonExtra(true);
            idHoKhau.setDisable(true);
            diaChi.setDisable(true);
            taoChuHo.setDisable(true);
            dienSoLuong.setText(soLuong + "");
            dienSoLuong.setDisable(true);
            hoKhau.setChuHo(nhanKhau); //Sau khi nhap xong chu ho nay la khong duoc set chu ho khac nua
            Luu.setVisible(true);
        }
        else if (i == 2) {
            soLuong += 1;
            dienSoLuong.setText(soLuong + "");
        }
        dsNhanKhau.setItems(getDsNhanKhauCuaSceneNay());
    }
    public void setTaoChuHo (Event event) throws IOException {
        if (idHoKhau.getText() == "" || diaChi.getText() == ""){
            showAlertErrorLogin("Nhập đầy đủ thông tin");
            return;
        }
        HoKhauStatic.setMenu(1);
        String idText = idHoKhau.getText();
        String diaChiText = diaChi.getText();
        FXMLLoader fxmlLoader = new FXMLLoader(ThemNhanKhau.class.getResource("/view/fxml/Them_Nhan_Khau.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ThemNhanKhau controller = fxmlLoader.getController();
        controller.idHoKhau.setText(idText);
        controller.idHoKhau.setDisable(true);
        controller.diaChiThuongTru.setText(diaChiText);
        controller.diaChiThuongTru.setDisable(true);
        controller.quanHeChuHo.setText("Chu ho");
        controller.quanHeChuHo.setDisable(true);
        DBUtils.changeScene(scene, event);
        ThemNhanKhau.setPreScene(getCurScene());
    }

    public void setThemNhanKhau (Event event) throws IOException {
        HoKhauStatic.setMenu(2);
        String idText = idHoKhau.getText();
        String diaChiText = diaChi.getText();
        FXMLLoader fxmlLoader = new FXMLLoader(ThemNhanKhau.class.getResource("/view/fxml/Them_Nhan_Khau.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ThemNhanKhau controller = fxmlLoader.getController();
        controller.idHoKhau.setText(idText);
        controller.idHoKhau.setDisable(true);
        controller.diaChiThuongTru.setText(diaChiText);
        controller.diaChiThuongTru.setDisable(true);
        DBUtils.changeScene(scene, event);
        ThemNhanKhau.setPreScene(getCurScene());
    }

    public void Luu (Event event) {
        hoKhau.setIdHoKhau(idHoKhau.getText());
        hoKhau.setDiaChi(diaChi.getText());
        hoKhau.setSoLuongNhanKhau(soLuong);
        if (DBUtils.themHoKhau(hoKhau) != 0) {
            HoKhauStatic.themHoKhau(hoKhau);
        }
        for (NhanKhau e: dsNhanKhauCuaSceneNay) {
            if (DBUtils.themNhanKhau(e)!= 0){
                NhanKhauStatic.themNhanKhau(e);
            }
        }
        back(event);
    }
}
