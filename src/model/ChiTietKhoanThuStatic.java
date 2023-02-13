package model;

import controller.DBUtils;
import controller.ShowAlert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChiTietKhoanThuStatic {

    // Update dữ liệu bảng chi tiết khoản thu
    public static void updateChiTietKhoanThu(int idKhoanThu, String idHoKhau, int tt) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE ChiTietKhoanThu\n" +
                "SET TrangThai = " + tt + "\n" +
                "WHERE IdKhoanThu =" + idKhoanThu + "AND IdHoKhau = '" + idHoKhau+ "';";
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
    public static void insertChiTietKhoanThu(int idKhoanThu, String idHoKhau) throws SQLException, ClassNotFoundException {
        String sql = "insert into ChiTietKhoanThu values(\n" +
                idKhoanThu + "," + idHoKhau + ", \n" +
                "(select SoTien from KhoanThu where IdKhoanThu =" + idKhoanThu + ") * (select SoLuongNhanKhau from HoKhau where IdHoKhau = '" + idHoKhau + "'),\n" +
                "0)";
        try {
            DBUtils.dbExecuteQuery(sql);
            System.out.println("Thêm dữ liệu thành công");
            System.out.println("--------------------------------------------------------------");
        } catch (SQLException e) {
            ShowAlert.showAlertError(null, "Nhập sai ID. Nhập lại!");
        }
    }

    // lất tất cả bản ghi trong bảng ChiTietKhoanThu
    public static ObservableList<ChiTietKhoanThu> getAlLRecords() {
        String sql = "select ct.IdKhoanThu, ct.IdHoKhau, hk.TenChuHo, ct.SoTien, ct.TrangThai\n" +
                "from [dbo].[ChiTietKhoanThu] ct\n" +
                "left join HoKhau hk on hk.IdHoKhau = ct.IdHoKhau";
        ObservableList<ChiTietKhoanThu> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBUtils.dbExecute(sql);
            while(rs.next()) {
                ChiTietKhoanThu ctkt = new ChiTietKhoanThu(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4), rs.getInt(5));
                list.add(ctkt);
            }

        } catch (Exception e) {
            System.out.println("Không lấy được dữ liệu");
        }
        return list;
    }

    // lấy một phần bản ghi trong bảng ChiTietKhoanThu (phục vụ cho hàm tìm kiếm)
    public static ObservableList<ChiTietKhoanThu> getPartRecords(int idKhoanThu) {
        String sql = "select ct.IdKhoanThu, ct.IdHoKhau, hk.TenChuHo, ct.SoTien, ct.TrangThai \n" +
                "from [dbo].[ChiTietKhoanThu] ct \n" +
                "left join HoKhau hk on hk.IdHoKhau = ct.IdHoKhau\n" +
                "where ct.IdKhoanThu = " + idKhoanThu;
        ObservableList<ChiTietKhoanThu> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBUtils.dbExecute(sql);
            while(rs.next()) {
                ChiTietKhoanThu ctkt = new ChiTietKhoanThu(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4), rs.getInt(5));
                list.add(ctkt);
            }

        } catch (Exception e) {
            System.out.println("Không lấy được dữ liệu");
        }
        return list;
    }
}
