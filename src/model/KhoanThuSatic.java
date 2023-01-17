package model;

import controller.DBUtils;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class KhoanThuSatic {

    private static SimpleDateFormat inSDF = new SimpleDateFormat("dd/mm/yyyy");
    private static SimpleDateFormat outSDF = new SimpleDateFormat("yyyy-mm-dd");


    // Thêm dữ liệu mới vào bảng khoản thu
    public static void insertKhoanThu(String tenKhoanThu, String ngayBD, String ngayKT, int soTien) throws SQLException, ClassNotFoundException {
        String sql = "insert into KhoanThu values(N'" + tenKhoanThu + "', '" + ngayBD + "', '" +
                ngayKT + "', " + soTien + ")";

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
    public static void updateKhoanThu(int idKhoanThu, String tenKhoanThu, String ngayBD, String ngayKT, int soTien) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE KhoanThu\n" +
                "SET TenKhoanThu = N'" + tenKhoanThu + "', NgayBatDau = '" + ngayBD +
                "', NgayKetThuc = '"+ ngayKT +"', SoTien = " + soTien +
                "WHERE IdKhoanThu = " + idKhoanThu + ";";

        try {
            DBUtils.dbExecuteQuery(sql);
            System.out.println("Update khoản thu thành công");
            System.out.println("--------------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println("Xảy ra ngoại lệ trong khi đang thêm dữ liệu vào database: " + e);
            e.printStackTrace();
            throw e;
        }
    }

    // lấy tất cả bản ghi trong bảng KhoanThu
    public static ObservableList<KhoanThu> getAlLRecords() {
        String sql = "select * from [dbo].[KhoanThu]";
        ObservableList<KhoanThu> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBUtils.dbExecute(sql);
            while(rs.next()) {
                KhoanThu chiTieu = new KhoanThu(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5));
                list.add(chiTieu);
            }

        } catch (Exception e) {
            System.out.println("Không lấy được dữ liệu");
        }
        return list;
    }


    // convert từ dạng "dd/mm/yyyy" về dạng "yyyy-mm-dd"
    public static String formatDate(String inDate) {
        String outDate = "";
        if (inDate != null) {
            try {
                Date date = inSDF.parse(inDate);
                outDate = outSDF.format(date);
            } catch (ParseException ex) {
            }
        }
        return outDate;
    }

    // Kiểm tra định dạng ngày tháng năm
    public static boolean isValidFormat(String format, String value, Locale locale) {
        LocalDateTime ldt = null;
        DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(format, locale);

        try {
            ldt = LocalDateTime.parse(value, fomatter);
            String result = ldt.format(fomatter);
            return result.equals(value);
        } catch (DateTimeParseException e) {
            try {
                LocalDate ld = LocalDate.parse(value, fomatter);
                String result = ld.format(fomatter);
                return result.equals(value);
            } catch (DateTimeParseException exp) {
                try {
                    LocalTime lt = LocalTime.parse(value, fomatter);
                    String result = lt.format(fomatter);
                    return result.equals(value);
                } catch (DateTimeParseException e2) {
                    // Debugging purposes
                    //e2.printStackTrace();
                }
            }
        }
        return false;
    }



}
