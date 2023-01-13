package model;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class KhoanThuSatic {
    private static ObservableList<KhoanThu> DanhSach = FXCollections.observableArrayList();
    public static ObservableList<KhoanThu> getDanhSach() {
        return DanhSach;
    }
    public static void setDanhSach(ObservableList<KhoanThu> danhSach) {
        DanhSach = danhSach;
    }
    public static void themKhoanThu (KhoanThu khoanThu) {
        DanhSach.add(khoanThu);
    }

    public static void suaKhoanThu (KhoanThu khoanThu) {
//        DanhSach.indexOf(khoanThu);
    }
}
