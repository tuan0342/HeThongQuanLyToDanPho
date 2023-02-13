package model;

import controller.DBUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChiTietDongGopStatic {
    // Update dữ liệu bảng chi tiết đóng góp
    public static void updateChiTietDG(int idKhoanDG, String idHoKhau, String soTien) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE ChiTietDongGop\n" +
                    "SET SoTien = " + soTien +"\n" +
                    "WHERE IdKhoanDongGop = " + idKhoanDG + "AND IdHoKhau ='" + idHoKhau + "'";
        try {
            DBUtils.dbExecuteQuery(sql);
            System.out.println("Update chi tiết khoản thu thành công");
            System.out.println("--------------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println("Xảy ra ngoại lệ trong khi đang thêm dữ liệu vào database: " + e);
            e.printStackTrace();
            throw e;
        }
    }

    // Thêm dữ liệu mới vào bảng chi tiết khoản thu
    public static void insertChiTietDG(int idKhoanDG, String idHoKhau, String soTien) throws SQLException, ClassNotFoundException {
        String sql = "insert into ChiTietDongGop values(" + idKhoanDG +",'" +  idHoKhau + "'," + soTien +")";
        try {
            DBUtils.dbExecuteQuery(sql);
            System.out.println("Thêm dữ liệu thành công");
            System.out.println("--------------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println("Xảy ra ngoại lệ trong khi đang thêm dữ liệu vào database: " + e);
            e.printStackTrace();
            throw e;
        }
    }

    // lất tất cả bản ghi trong bảng ChiTietKhoanThu
    public static ObservableList<ChiTietDongGop> getAlLRecords() {
        String sql = "select ct.IdKhoanDongGop, ct.IdHoKhau, hk.TenChuHo, ct.SoTien\n" +
                "from ChiTietDongGop ct\n" +
                "left join HoKhau hk on ct.IdHoKhau = hk.IdHoKhau";
        ObservableList<ChiTietDongGop> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBUtils.dbExecute(sql);
            while(rs.next()) {
                ChiTietDongGop ctkt = new ChiTietDongGop(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4));
                list.add(ctkt);
            }

        } catch (Exception e) {
            System.out.println("Không lấy được dữ liệu");
        }
        return list;
    }

    // lấy một phần bản ghi trong bảng ChiTietKhoanThu (phục vụ cho hàm search)
    public static ObservableList<ChiTietDongGop> getPartRecords(int idKhoanDG) {
        String sql = "select ct.IdKhoanDongGop, ct.IdHoKhau, hk.TenChuHo, ct.SoTien\n" +
                "from ChiTietDongGop ct\n" +
                "left join HoKhau hk on ct.IdHoKhau = hk.IdHoKhau\n" +
                "where ct.IdKhoanDongGop = " + idKhoanDG;
        ObservableList<ChiTietDongGop> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBUtils.dbExecute(sql);
            while(rs.next()) {
                ChiTietDongGop ctkt = new ChiTietDongGop(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4));
                list.add(ctkt);
            }

        } catch (Exception e) {
            System.out.println("Không lấy được dữ liệu");
        }
        return list;
    }
}
