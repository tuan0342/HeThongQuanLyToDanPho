package model;

import controller.DBUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DongGopStatic {

    // lấy tất cả bản ghi trong bảng DongGop
    public static ObservableList<DongGop> getAlLRecords() {
        String sql = "select * from [dbo].[DongGop]";
        ObservableList<DongGop> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBUtils.dbExecute(sql);
            while(rs.next()) {
                DongGop chiTieu = new DongGop(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4));
                list.add(chiTieu);
            }

        } catch (Exception e) {
            System.out.println("Không lấy được dữ liệu");
        }
        return list;
    }

    // Thêm dữ liệu mới vào bảng khoản thu
    public static void insertDongGop(String tenDG, String ngayBD, String ngayKT) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO DongGop VALUES (N'" + tenDG + "', '" +
                    ngayBD +"', '" + ngayKT +"');";

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

    // update khoản thu
    public static void updateDG(int IdDG, String tenKhoanDG, String ngayBD, String ngayKT) throws SQLException, ClassNotFoundException {
        String sql = "update DongGop\n" +
                "set TenKhoanDongGop = N'" + tenKhoanDG + "', NgayBatDau = '" + ngayBD + "', NgayKetThuc = '" +
                ngayKT +"'\n" +
                "where IdDongGop = " + IdDG;

        try {
            DBUtils.dbExecuteQuery(sql);
            System.out.println("Update đóng góp thành công");
            System.out.println("--------------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println("Xảy ra ngoại lệ trong khi đang thêm dữ liệu vào database: " + e);
            e.printStackTrace();
            throw e;
        }
    }


}
