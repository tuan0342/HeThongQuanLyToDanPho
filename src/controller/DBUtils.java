package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.HoKhau;
import model.HoKhauStatic;
import model.NhanKhau;
import model.NhanKhauStatic;

import javax.swing.plaf.TableHeaderUI;
import java.sql.*;
import java.time.LocalDate;

public class DBUtils {

    private static String dbURL = "jdbc:sqlserver://DESKTOP-BM4SH04:1433;databaseName=QuanLyToDanPho;encrypt=false;trustServerCertificate=true;";
    private static String user = "sa"; // user trên mỗi máy là khác nhau tùy cá nhân tự đặt
    private static String pass = "123";  // pass trên mỗi máy là khác nhau tùy cá nhân tự đặt
    private static Connection connection ;

    // connect database
    public static void dbConnect() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("SQL Server JDBC Driver ở đâu rồi?");
            e.printStackTrace();
        }

        System.out.println("\nKết nối thành công");
        System.out.println("--------------------------------------------------------------");

        try {
            connection = DriverManager.getConnection(dbURL, user, pass);
//            System.out.print("HONGHANH");
//            if (connection != null) System.out.print("NGHIEM PHONG");
        } catch (SQLException e) {
            System.out.println("Kết nối thất bại! Check output console" + e);
            e.printStackTrace();
        }
    }

    // disconnect database
    public static void dbDisconnect() throws SQLException, ClassNotFoundException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            System.out.println("Có lỗi xảy ra khi disconnect database: " + e);
            e.printStackTrace();
        }
    }

    // this is for insert/update/delete operation
    public static void dbExecuteQuery(String sqlStmt)throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        try {
            dbConnect();
            stmt = connection.createStatement();
            stmt.executeQuery(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Xảy ra vấn đề ở hàm dbExecuteQuery");
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
    }

    // this is for retriving the record from the database
    public static ResultSet dbExecute(String sqlQuery) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        ResultSet rs = null;
        dbConnect();
        if(connection != null) {
            try {
                stmt = connection.createStatement();
                rs = stmt.executeQuery(sqlQuery);
            } catch (SQLException e) {
                System.out.println("Có lỗi xảy ra ở hàm dbExecute (truy xuất dữ liệu)");
                throw e;
            } finally {

            }
        }
        return rs;
    }

    // Phương thức chuyển màn hình
    public static void changeScene(Scene scene, Event event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    public static ResultSet ThucThiLechSelect (String CauLech) {
        try {
            if(connection != null) {
                Statement stm = connection.createStatement();
                return stm.executeQuery(CauLech);
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("-Loi thu hien cau truy van SQL-");
            System.out.println("Cau lenh " + CauLech);
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static int ThucThiCauLenhUpdate (String CauLech) {
        try {
            if(connection != null) {
                Statement stm = connection.createStatement();
                return stm.executeUpdate(CauLech);
            } else {
                return 0;
            }

        } catch (SQLException e) {
            System.out.println("-Loi thu hien cau truy van SQL-");
            System.out.println("Cau lenh " + CauLech);
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public static HoKhau timTheoID (String id) throws SQLException {
        String cauLenh = "SELECT * FROM HoKhau WHERE IdHoKhau = " + id;
        ResultSet resultSet =  ThucThiLechSelect(cauLenh);
        if (resultSet != null){
            while (resultSet.next()) {
                String idHoKhau = resultSet.getString("IdHoKhau");
                String tenChuHo = resultSet.getString("TenChuHo");
                String diaChi = resultSet.getString("DiaChi");
                int soLuong = resultSet.getInt("SoLuongNhanKhau");
                NhanKhau chuHo = timChuHoTheoID(id);
                String hoTenChuHo = chuHo.getHoTen();
                HoKhau hoKhau = new HoKhau(idHoKhau, tenChuHo, diaChi, soLuong, chuHo);
                return hoKhau;
            }
        }
        return null;
    }

    public static NhanKhau timChuHoTheoID (String id) throws  SQLException {
        String cauLenh = "SELECT NK.* FROM HoKhau HK, NhanKhau NK\n" +
                "WHERE HK.IdHoKhau = NK.IdHoKhau AND NK.ChuHo = 1 AND HK.IdHoKhau = " + id;
        ResultSet resultSet = ThucThiLechSelect(cauLenh);
        if (resultSet != null) {
            while (resultSet.next()) {
                String idNhanKhau = resultSet.getString("IdNhanKhau");
                String idHoKhau = id;
                String hoTen = resultSet.getString("HoTen");
                String quanHeVoiChuHo = resultSet.getString("QuanHeVoiChuHo");
                int chuHo = resultSet.getInt("ChuHo");
                String biDanh = resultSet.getString("BiDanh");
                Date ngaySinh = resultSet.getDate("NgaySinh");
                String nguyenQuan = resultSet.getString("NguyenQuan");
                String danToc = resultSet.getString("DanToc");
                String ngheNghiep = resultSet.getString("NgheNghiep");
                String noiLamViec = resultSet.getString("NoiLamViec");
                Date TGDKTT = resultSet.getDate("TGDKTT");
                int soCCCD = resultSet.getInt("SoCCCD");
                Date ngayCap = resultSet.getDate("NgayCap");
                String noiCap = resultSet.getString("NoiCap");
                String diaChiThuongTru = resultSet.getString("DiaChiThuongTru");
                NhanKhau nhanKhau = new NhanKhau(idNhanKhau, idHoKhau, hoTen, biDanh, ngaySinh, nguyenQuan,
                        danToc, ngheNghiep, noiLamViec, soCCCD, ngayCap, noiCap, TGDKTT, diaChiThuongTru, quanHeVoiChuHo, chuHo);
                return nhanKhau;
            }
        }
        return null;
    }

    public static NhanKhau timNhanKhauTheoID (String id) throws  SQLException {
        String cauLenh = "SELECT * FROM NhanKhau NK\n" +
                "WHERE NK.IdNhanKhau = " + id;
        ResultSet resultSet = ThucThiLechSelect(cauLenh);
        if (resultSet != null) {
            while (resultSet.next()) {
                String idNhanKhau = resultSet.getString("IdNhanKhau");
                String idHoKhau = resultSet.getString("IdHoKhau");
                String hoTen = resultSet.getString("HoTen");
                String quanHeVoiChuHo = resultSet.getString("QuanHeVoiChuHo");
                int chuHo = resultSet.getInt("ChuHo");
                String biDanh = resultSet.getString("BiDanh");
                Date ngaySinh = resultSet.getDate("NgaySinh");
                String nguyenQuan = resultSet.getString("NguyenQuan");
                String danToc = resultSet.getString("DanToc");
                String ngheNghiep = resultSet.getString("NgheNghiep");
                String noiLamViec = resultSet.getString("NoiLamViec");
                Date TGDKTT = resultSet.getDate("TGDKTT");
                int soCCCD = resultSet.getInt("SoCCCD");
                Date ngayCap = resultSet.getDate("NgayCap");
                String noiCap = resultSet.getString("NoiCap");
                String diaChiThuongTru = resultSet.getString("DiaChiThuongTru");
                NhanKhau nhanKhau = new NhanKhau(idNhanKhau, idHoKhau, hoTen, biDanh, ngaySinh, nguyenQuan,
                        danToc, ngheNghiep, noiLamViec, soCCCD, ngayCap, noiCap, TGDKTT, diaChiThuongTru, quanHeVoiChuHo, chuHo);
                return nhanKhau;
            }
        }
        return null;
    }

    public static int themHoKhau (HoKhau hoKhau) {
        String cauLenh = "INSERT INTO HoKhau\n" +
                "VALUES (\n" +
                "\t'" + hoKhau.getIdHoKhau() + "',\n" +
                "\tN'" + hoKhau.getTenChuHo() + "',\n" +
                "\tN'" + hoKhau.getDiaChi() + "',\n" +
                "\t" + hoKhau.getSoLuongNhanKhau() + "\n" +
                ")";
        return ThucThiCauLenhUpdate(cauLenh);
    }

    public static int themNhanKhau (NhanKhau nhanKhau) {
        // Them phuong thuc update so nhan Khau trong ho khau nay
        String cauLenh = "INSERT INTO NhanKhau\n" +
                "VALUES (\n" +
                "\t'"+nhanKhau.getIdNhanKhau()+"',\n" +
                "\t'"+nhanKhau.getIdHoKhau()+"',\n" +
                "\tN'"+nhanKhau.getHoTen()+"',\n" +
                "\tN'"+nhanKhau.getQuanHeChuHo()+"',\n" +
                "\t"+nhanKhau.getChuHo()+",\n" +
                "\tN'"+nhanKhau.getBiDanh()+"',\n" +
                "\t"+nhanKhau.getNgaySinh()+",\n" +
                "\tN'"+nhanKhau.getNguyenQuan()+"',\n" +
                "\tN'"+nhanKhau.getDanToc()+"',\n" +
                "\tN'"+nhanKhau.getNgheNghiep()+"',\n" +
                "\tN'"+nhanKhau.getNoiLamViec()+"',\n" +
                "\t"+nhanKhau.getThoiGianDKThuongTru()+",\n" +
                "\t"+nhanKhau.getSoCCCD()+",\n" +
                "\t"+nhanKhau.getNgayCap()+",\n" +
                "\tN'"+nhanKhau.getNoiCap()+"',\n" +
                "\tN'"+nhanKhau.getDiaChiThuongTru()+"'\n" +
                ")\n";
        return ThucThiCauLenhUpdate(cauLenh);
    }

    public static void loadDsHoKhau () throws SQLException {
        String cauLenh = "SELECT * FROM HoKhau";
        ResultSet resultSet =  ThucThiLechSelect(cauLenh);
        if (resultSet != null){
            while (resultSet.next()) {
                String idHoKhau = resultSet.getString("IdHoKhau");
                String tenChuHo = resultSet.getString("TenChuHo");
                String diaChi = resultSet.getString("DiaChi");
                int soLuong = resultSet.getInt("SoLuongNhanKhau");
                NhanKhau chuHo = new NhanKhau();
                HoKhau hoKhau = new HoKhau(idHoKhau, tenChuHo, diaChi, soLuong, chuHo);
                HoKhauStatic.themHoKhau(hoKhau);
            }
        }
    }

    public static void loadDsNhanKhau () throws SQLException {
        String cauLenh = "SELECT * FROM NhanKhau NK\n";
        ResultSet resultSet = ThucThiLechSelect(cauLenh);
        if (resultSet != null) {
            while (resultSet.next()) {
                String idNhanKhau = resultSet.getString("IdNhanKhau");
                String idHoKhau = resultSet.getString("IdHoKhau");
                String hoTen = resultSet.getString("HoTen");
                String quanHeVoiChuHo = resultSet.getString("QuanHeVoiChuHo");
                int chuHo = resultSet.getInt("ChuHo");
                String biDanh = resultSet.getString("BiDanh");
                Date ngaySinh = resultSet.getDate("NgaySinh");
                String nguyenQuan = resultSet.getString("NguyenQuan");
                String danToc = resultSet.getString("DanToc");
                String ngheNghiep = resultSet.getString("NgheNghiep");
                String noiLamViec = resultSet.getString("NoiLamViec");
                Date TGDKTT = resultSet.getDate("TGDKTT");
                int soCCCD = resultSet.getInt("SoCCCD");
                Date ngayCap = resultSet.getDate("NgayCap");
                String noiCap = resultSet.getString("NoiCap");
                String diaChiThuongTru = resultSet.getString("DiaChiThuongTru");
                NhanKhau nhanKhau = new NhanKhau(idNhanKhau, idHoKhau, hoTen, biDanh, ngaySinh, nguyenQuan,
                        danToc, ngheNghiep, noiLamViec, soCCCD, ngayCap, noiCap, TGDKTT, diaChiThuongTru, quanHeVoiChuHo, chuHo);
                NhanKhauStatic.themNhanKhau(nhanKhau);
            }
        }
    }
}
