package model;

import controller.DBUtils;
import controller.ThemHoKhau;
import controller.ThemNhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HoKhauStatic {
//    private
    private static ObservableList<HoKhau> dsHoKhau = FXCollections.observableArrayList();
    private static ThemHoKhau themHoKhau;
    private static ThemNhanKhau themNhanKhau;
    public static ObservableList<HoKhau> getDsHoKhau() {
        return dsHoKhau;
    }

    public static ThemHoKhau getThemHoKhau() {
        return themHoKhau;
    }
    public static void setThemHoKhau(ThemHoKhau themHoKhau) {
        HoKhauStatic.themHoKhau = themHoKhau;
    }
    public static void setThemNhanKhau(ThemNhanKhau themNhanKhau) {
        HoKhauStatic.themNhanKhau = themNhanKhau;
    }
    public static void themHoKhau (HoKhau hoKhau) {
        HoKhauStatic.dsHoKhau.add(hoKhau);
    }

    private static int menu;
        /*
            1. Hộ khẩu gọi thêm nhân khâu là chủ hộ
            2. Hộ khẩu gọi thêm nhân khẩu không là chủ hộ
            2. Hộ khẩu gọi sửa hộ khẩu
            3. Nhân khẩu gọi thêm nhân khẩu
            4. Nhân khẩu gọi sửa nhân khẩu
         */

    public static int getMenu() {
        return menu;
    }
    public static void setMenu(int menu) {
        HoKhauStatic.menu = menu;
    }
    public static void themNhanKhau (NhanKhau nhanKhau, String idHoKhau) {
        if (getThemHoKhau() == null) {
            return;
        }
        if (getMenu() == 1) {
            //Ho Khau goi them nhan khau la chu ho
            getThemHoKhau().xongMenu(1, nhanKhau);

        } else if (getMenu() == 2) {
            //Nhan Khau goi them nhan khau khong la chu ho
            getThemHoKhau().xongMenu(2, nhanKhau);
        }
    }

    public static void suaNhanKhau () {
    }

    public static ObservableList<HoKhau> getAlLRecords() {
        String sql = "select * from [dbo].[HoKhau]";
        ObservableList<HoKhau> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBUtils.dbExecute(sql);
            while(rs.next()) {
                HoKhau hk = new HoKhau(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4));
                list.add(hk);
            }

        } catch (Exception e) {
            System.out.println("Không lấy được dữ liệu");
        }
        return list;
    }
}
