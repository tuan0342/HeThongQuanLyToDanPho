package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class NhanKhauStatic {
    private static ObservableList<NhanKhau> dsNhanKhau = FXCollections.observableArrayList();
    public static ObservableList<NhanKhau> getDsNhanKhau() {
        return dsNhanKhau;
    }
    public static void setDsNhanKhau(ObservableList<NhanKhau> dsNhanKhau) {
        NhanKhauStatic.dsNhanKhau = dsNhanKhau;
    }
    public static void themNhanKhau (NhanKhau nhanKhau) {
        getDsNhanKhau().add(nhanKhau);
    }
}
