package model;

import controller.DBUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class ThongKeKhoanThuStatic {
    // lấy bảng thống kê
    public static ObservableList<ThongKeKhoanThu> getAllThongKe() {
        String sql = "SELECT distinct kt.TenKhoanThu, \n" +
                "(select sum(SoTien) from ChiTietKhoanThu where TrangThai = 1 and IdKhoanThu = kt.IdKhoanThu) as N'Tổng số tiền đã thu', \n" +
                "(select count(IdKhoanThu) from ChiTietKhoanThu where TrangThai = 1 and IdKhoanThu = kt.IdKhoanThu) as N'Số hộ đã thu', \n" +
                "(select sum(SoTien) from ChiTietKhoanThu where TrangThai = 0 and IdKhoanThu = kt.IdKhoanThu) as N'Tổng số tiền chưa thu',\n" +
                "(select count(IdKhoanThu) from ChiTietKhoanThu where TrangThai = 0 and IdKhoanThu = kt.IdKhoanThu) as N'Số hộ chưa thu',\n" +
                "(select count(IdKhoanThu) from ChiTietKhoanThu where IdKhoanThu = kt.IdKhoanThu) as N'Tổng số hộ phải nộp',\n" +
                "kt.SoTien as N'Số tiền/hộ'\n" +
                "FROM KhoanThu kt\n" +
                "left join ChiTietKhoanThu ctkt on ctkt.IdKhoanThu = kt.IdKhoanThu";
        ObservableList<ThongKeKhoanThu> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBUtils.dbExecute(sql);
            while(rs.next()) {
                String a = rs.getString(1);
                String b = rs.getString(2);
                String c = rs.getString(3);
                String d = rs.getString(4);
                String e = rs.getString(5);
                String f = rs.getString(6);
                String g = rs.getString(7);
                System.out.println(b + ", " + d);

                if (b == null) {
                    b = "0";
                }
                if (d == null) {
                    d = "0";
                }

                ThongKeKhoanThu chiTieu = new ThongKeKhoanThu(a, b, c, d, e, f, g);
                list.add(chiTieu);
            }

        } catch (Exception e) {
            System.out.println("Không lấy được dữ liệu");
        }
        return list;
    }
}
