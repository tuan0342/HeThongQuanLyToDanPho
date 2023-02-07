package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.util.HashMap;

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

    public static void thayDoiNhanKhau (String idHoKhau, String idNhanKhau) {

    }
    public static ObservableList<NhanKhau> xuatDsNhanKhau (String idHoKhau) {
        ObservableList<NhanKhau> dsNhanKhau = FXCollections.observableArrayList();
        for (NhanKhau e: getDsNhanKhau()) {
            if (e.getIdHoKhau() == idHoKhau) dsNhanKhau.add(e);
        }
        return dsNhanKhau;
    }

    public static void suaChuHo (String idHoKhau, HashMap<String, String> quanHeChuHoCanSua) {
        //hashMap quanHeChuHoCanSua: key la idNhanKhau, value la quanHeMoi
        for (NhanKhau e: getDsNhanKhau()) {
            if (idHoKhau.compareTo(e.getIdHoKhau()) == 0) {
                String idNhanKhau = e.getIdNhanKhau();
                e.setQuanHeChuHo(quanHeChuHoCanSua.get(idNhanKhau));
                quanHeChuHoCanSua.remove(idNhanKhau);
                if (quanHeChuHoCanSua.isEmpty()) return;
            }
        }
    }

    public static void xoaNhanKhau (String idHoKhau) {
        dsNhanKhau.removeAll(xuatDsNhanKhau(idHoKhau));
    }
}
