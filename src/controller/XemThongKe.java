package controller;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.KhaiBaoTamTruTamVangStatic;
import model.ThongKeStatic;

import java.awt.*;
import java.net.URL;
import java.security.PublicKey;
import java.security.cert.TrustAnchor;
import java.util.ResourceBundle;

public class XemThongKe implements Initializable {
    //Lien quan den Scene truoc Scene sau
    private static Scene preScene;
    private static Scene curScene;

    public static Scene getPreScene() {
        return preScene;
    }

    public static void setPreScene(Scene preScene) {
        XemThongKe.preScene = preScene;
    }

    public static Scene getCurScene() {
        return curScene;
    }

    public static void setCurScene(Scene curScene) {
        XemThongKe.curScene = curScene;
    }


    //Button quay lai
    public Button Back;
    public void Back (Event event) {
        DBUtils.changeScene(preScene, event);
    }

    //Button lua chon bieu do

    public ChoiceBox<String> LuaChonBieuDo = new ChoiceBox();

    public PieChart BieuDoTheoGioiTinh;


    public BarChart BieuDoTheoDoTuoi;


    public LineChart BieuDoTheoThoiGian;
    public BarChart BieuDoTheoTamTruTamVang;





    public Label ThongBao;
    public Label TuNam;
    public Label DenNam;

    public TextField NamBatDau;
    public TextField NamKetThuc;



    public void setBieuDoBanDau() {
        BieuDoTheoGioiTinh.setVisible(false);
        BieuDoTheoDoTuoi.setVisible(false);
        BieuDoTheoThoiGian.setVisible(false);
        BieuDoTheoTamTruTamVang.setVisible(false);

        ThongBao.setVisible(false);
        TuNam.setVisible(false);
        NamBatDau.setVisible(false);
        DenNam.setVisible(false);
        NamKetThuc.setVisible(false);

    }


    public void LuaChonBieuDo(){

        try{
            if (LuaChonBieuDo.getValue() == null){
                ShowAlert.showAlertError("Bạn chưa chọn loại thống kê!", "Mời bạn chọn loại thống kê");
            } else {
                if (LuaChonBieuDo.getValue() == "THEO GIỚI TÍNH") {
                    BieuDoTheoGioiTinh.setVisible(true);
                    BieuDoTheoDoTuoi.setVisible(false);
                    BieuDoTheoThoiGian.setVisible(false);
                    BieuDoTheoTamTruTamVang.setVisible(false);
                    veBieuDoTheoGioiTinh();


                } else if (LuaChonBieuDo.getValue() == "THEO NHÓM TUỔI") {
                    BieuDoTheoGioiTinh.setVisible(false);
                    BieuDoTheoThoiGian.setVisible(false);
                    BieuDoTheoTamTruTamVang.setVisible(false);
                    BieuDoTheoDoTuoi.setVisible(true);
                    veBieuDoTheoDoTuoi();

                } else if (LuaChonBieuDo.getValue() == "THEO THỜI GIAN") {
                    BieuDoTheoGioiTinh.setVisible(false);
                    BieuDoTheoDoTuoi.setVisible(false);
                    BieuDoTheoTamTruTamVang.setVisible(false);
                    if(NamBatDau.getText() == "" || NamKetThuc.getText() == "") {
                        ShowAlert.showAlertError("Chưa nhập năm!", "Mời bạn nhập lại");
                    } else {

                        BieuDoTheoThoiGian.setVisible(true);
                        veBieuDoTheoThoiGian();
                        NamBatDau.clear();
                        NamKetThuc.clear();

                    }
                } else {
                    BieuDoTheoGioiTinh.setVisible(false);
                    BieuDoTheoDoTuoi.setVisible(false);
                    BieuDoTheoThoiGian.setVisible(false);
                    if(NamBatDau.getText() == "" || NamKetThuc.getText() == "") {
                        ShowAlert.showAlertError("Chưa nhập năm!", "Mời bạn nhập lại");
                    } else {
                        BieuDoTheoTamTruTamVang.setVisible(true);
                        veBieuDoTheoTamTruTamVang();
                        NamBatDau.clear();
                        NamKetThuc.clear();
                    }
                }

            }

        } catch (Exception e){
            System.out.println("Chua lua chon bieu do");

        }
    }

    //in bieu do
    public Button LuaChonButton;
    public void luaChon(Event event) throws Exception {
        LuaChonBieuDo();
    }


    //ve bieu do thong ke gioi tinh



    public void veBieuDoTheoGioiTinh() {
        BieuDoTheoGioiTinh.getData().clear();
        Integer NuValue = ThongKeStatic.getDataTheoGioiTinh().get(0);
        Integer NamValue = ThongKeStatic.getDataTheoGioiTinh().get(1);
        float TiLeNu = (float)NuValue/(NuValue + NamValue)*100;
        float TiLeNam = 100 - TiLeNu;
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList(new PieChart.Data ("Nam", TiLeNam),new PieChart.Data ("Nữ", TiLeNu) );
        BieuDoTheoGioiTinh.getData().addAll(list);
        BieuDoTheoGioiTinh.setTitle("BIỂU ĐỒ THỐNG KÊ NHÂN KHẨU THEO GIỚI TÍNH");
        BieuDoTheoGioiTinh.setLegendVisible(true);
        BieuDoTheoGioiTinh.setClockwise(true);
        BieuDoTheoGioiTinh.setLabelLineLength(40);
        BieuDoTheoGioiTinh.setLabelsVisible(true);
//        BieuDoTheoGioiTinh.setLegendSide(Side.RIGHT);

        list.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), " ", data.pieValueProperty(), " %"
                        )
                )
        );
    }


    //vẽ biểu đồ thống ke theo do tuoi
    public void veBieuDoTheoDoTuoi() {
        BieuDoTheoDoTuoi.getData().clear();
        BieuDoTheoDoTuoi.getXAxis().setLabel("NHÓM TUỔI");
        BieuDoTheoDoTuoi.getYAxis().setLabel("SỐ NGƯỜI");

        //lay du lieu vao bieu do

        Integer MamNonvalue = ThongKeStatic.getDatamamnon().get(0);
        Integer MauGiaovalue = ThongKeStatic.getDatamaugiao().get(0);
        Integer Cap1value = ThongKeStatic.getDatacap1().get(0);
        Integer Cap2value = ThongKeStatic.getDatacap2().get(0);
        Integer Cap3value = ThongKeStatic.getDatacap3().get(0);
        Integer LaoDongvalue = ThongKeStatic.getDataLaoDong().get(0);
        Integer NghiHuuvalue = ThongKeStatic.getDataNghiHuu().get(0);

        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.getData().add(new XYChart.Data("Mầm non", 100));
        dataSeries.getData().add(new XYChart.Data("Mẫu giáo", 200));
        dataSeries.getData().add(new XYChart.Data("Cấp một", 300));
        dataSeries.getData().add(new XYChart.Data("Cấp hai", 400));
        dataSeries.getData().add(new XYChart.Data("Cấp ba", 500));
        dataSeries.getData().add(new XYChart.Data("Lao động", 200));
        dataSeries.getData().add(new XYChart.Data("Nghỉ hưu", 100));

        BieuDoTheoDoTuoi.getData().add(dataSeries);
        BieuDoTheoDoTuoi.setTitle("THỐNG KÊ THEO ĐỘ TUỔI NHÂN KHẨU");
        BieuDoTheoDoTuoi.setLegendVisible(false);
        BieuDoTheoDoTuoi.getXAxis().setAnimated(false);
    }

    // VE BIEU DO THEO THOI GIAN

    public void veBieuDoTheoThoiGian() {

        //lay du lieu tu database
        BieuDoTheoThoiGian.getData().clear();
        BieuDoTheoThoiGian.getXAxis().setLabel("NĂM");
        BieuDoTheoThoiGian.getYAxis().setLabel("SỐ NGƯỜI");

        XYChart.Series dataSeries = new XYChart.Series();

        for (int i = Integer.parseInt(NamBatDau.getText()); i <= Integer.parseInt(NamKetThuc.getText()); i++) {
            dataSeries.getData().add( new XYChart.Data(String.valueOf(i), ThongKeStatic.getDataTheoThoiGian(i).get(0)));
        }

        BieuDoTheoThoiGian.getData().add(dataSeries);
        BieuDoTheoThoiGian.setTitle("THỐNG KÊ NHÂN KHẨU THEO THỜI GIAN");
        BieuDoTheoThoiGian.setCreateSymbols(true);
        BieuDoTheoThoiGian.setLegendVisible(false);
        BieuDoTheoThoiGian.getXAxis().setAnimated(false);

    }


    // ve bieu do theo tam tru tam vang

    public void veBieuDoTheoTamTruTamVang() {

        BieuDoTheoTamTruTamVang.getData().clear();

        BieuDoTheoTamTruTamVang.getYAxis().setLabel("SỐ NGƯỜI");
        BieuDoTheoTamTruTamVang.getXAxis().setLabel("NĂM");


        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("TẠM TRÚ");
        for (int i = Integer.parseInt(NamBatDau.getText()); i <= Integer.parseInt(NamKetThuc.getText()); i++) {
            dataSeries1.getData().add( new XYChart.Data(String.valueOf(i), ThongKeStatic.getDataTheoTamTru(i).get(0)));
        }


        XYChart.Series dataSeries2 = new XYChart.Series();
        dataSeries2.setName("TẠM VẮNG");
        for (int i = Integer.parseInt(NamBatDau.getText()); i <= Integer.parseInt(NamKetThuc.getText()); i++) {
            dataSeries2.getData().add( new XYChart.Data(String.valueOf(i), ThongKeStatic.getDataTheoTamVang(i).get(0)));
        }
        BieuDoTheoTamTruTamVang.getData().addAll(dataSeries1,dataSeries2);
        BieuDoTheoTamTruTamVang.setTitle("THỐNG KÊ NHÂN KHẨU THEO TẠM TRÚ TẠM VẮNG");
        BieuDoTheoTamTruTamVang.getXAxis().setAnimated(false);
    }



    public void initialize(URL url, ResourceBundle resourceBundle) {
//        ChoiceBox<String> box = new ChoiceBox<String>();
//        box.getItems().add("1");
//        box.getItems().add("2");
//        box.getItems().add("3");

        LuaChonBieuDo.getSelectionModel()
                .selectedItemProperty()
                .addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    if (newValue.compareTo("THEO GIỚI TÍNH") == 0 || newValue.compareTo("THEO NHÓM TUỔI") == 0) {
                        ThongBao.setVisible(false);
                        TuNam.setVisible(false);
                        DenNam.setVisible(false);
                        NamBatDau.setVisible(false);
                        NamKetThuc.setVisible(false);
                    } else {
                        ThongBao.setVisible(true);
                        TuNam.setVisible(true);
                        DenNam.setVisible(true);
                        NamBatDau.setVisible(true);
                        NamKetThuc.setVisible(true);

                    }
                });
        LuaChonBieuDo.getItems().addAll("THEO GIỚI TÍNH", "THEO NHÓM TUỔI", "THEO THỜI GIAN", "THEO TẠM TRÚ TẠM VẮNG");
        setBieuDoBanDau();


    }
}
