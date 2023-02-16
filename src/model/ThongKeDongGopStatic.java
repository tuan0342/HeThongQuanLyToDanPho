package model;

import controller.DBUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class ThongKeDongGopStatic {

    // lấy bảng thống kê
    public static ObservableList<ThongKeDongGop> getAllThongKe() {
        String sql = "select distinct dg.TenKhoanDongGop,\n" +
                "(select count(IdKhoanDongGop) from ChiTietDongGop where IdKhoanDongGop = dg.IdDongGop) as N'Số hộ đóng góp',\n" +
                "(select sum(SoTien) from ChiTietDongGop where IdKhoanDongGop = dg.IdDongGop) as N'Tổng số tiền đã thu'\n" +
                "from DongGop dg\n" +
                "left join ChiTietDongGop ct on ct.IdKhoanDongGop = dg.IdDongGop";
        ObservableList<ThongKeDongGop> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBUtils.dbExecute(sql);
            while(rs.next()) {
                String a = rs.getString(1);
                String b = rs.getString(2);
                String c = rs.getString(3);

                if (c == null) {
                    c = "0";
                }

                ThongKeDongGop chiTieu = new ThongKeDongGop(a, b, c);
                list.add(chiTieu);
            }

        } catch (Exception e) {
            System.out.println("Không lấy được dữ liệu");
        }
        return list;
    }
}
