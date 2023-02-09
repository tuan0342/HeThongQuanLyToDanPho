package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

import java.sql.*;


public class DBUtils {
    private static String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyToDanPho;encrypt=true;trustServerCertificate=true;";
   // private static String dbURL = "jdbc:sqlserver://DESKTOP-BM4SH04:1433;databaseName=QuanLyToDanPho;encrypt=true;trustServerCertificate=true;";
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
            stmt.executeUpdate(sqlStmt);
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
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            dbConnect();
            //stmt = connection.createStatement();
            //rs = stmt.executeQuery(sqlQuery);
            ps = connection.prepareStatement(sqlQuery);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("Có lỗi xảy ra ở hàm dbExecute (truy xuất dữ liệu)");
            throw e;
        } finally {

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


    public static void themHoKhau (HoKhau hoKhau) {
        String cauLenh = "INSERT INTO HoKhau\n" +
                "VALUES (\n" +
                "\t'" + hoKhau.getIdHoKhau() + "',\n" +
                "\tN'" + hoKhau.getTenChuHo() + "',\n" +
                "\tN'" + hoKhau.getDiaChi() + "',\n" +
                "\t" + hoKhau.getSoLuongNhanKhau() + "\n" +
                ")";
        ThucThiCauLenhUpdate(cauLenh);
    }

    public static void themNhanKhau (NhanKhau nhanKhau) {
        // Them phuong thuc update so nhan Khau trong ho khau nay
        String cauLenh = "INSERT INTO NhanKhau\n" +
                "VALUES (\n" +
                "\t'"+nhanKhau.getIdNhanKhau()+"',\n" +
                "\t'"+nhanKhau.getIdHoKhau()+"',\n" +
                "\tN'"+nhanKhau.getHoTen()+"',\n" +
                "\tN'"+nhanKhau.getGioiTinhBool()+"',\n" +
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
        ThucThiCauLenhUpdate(cauLenh);
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
                HoKhau hoKhau = new HoKhau(idHoKhau, tenChuHo, diaChi, soLuong);
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
                boolean gioiTinh = resultSet.getBoolean("GioiTinh");
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
                NhanKhau nhanKhau = new NhanKhau(idNhanKhau, idHoKhau, hoTen, gioiTinh, biDanh, ngaySinh, nguyenQuan,
                        danToc, ngheNghiep, noiLamViec, soCCCD, ngayCap, noiCap, TGDKTT, diaChiThuongTru, quanHeVoiChuHo, chuHo);
                NhanKhauStatic.themNhanKhau(nhanKhau);
            }
        }
    }

    public static void UpdateChuHoChoHoKhau (String idHoKhau, String tenChuHo, String idChuHo) {
        String cauLenh1 = "UPDATE HoKhau\n" +
                "SET TenChuHo = N'" + tenChuHo + "' \n" +
                "WHERE IdHoKhau = '" + idHoKhau + "'\n";
        ThucThiCauLenhUpdate(cauLenh1);
        String cauLenh2 = "UPDATE NhanKhau\n" +
                "SET QuanHeVoiChuHo = N'', ChuHo = 0 \n" +
                "WHERE IdHoKhau = '" + idHoKhau + "'\n";
        ThucThiCauLenhUpdate(cauLenh2);
        String cauLenh3 = "UPDATE NhanKhau\n" +
                "SET QuanHeVoiChuHo = N'Chủ hộ', ChuHo = 1 \n" +
                "WHERE IdNhanKhau = '" + idChuHo + "'\n";
        ThucThiCauLenhUpdate(cauLenh3);
    }

    public static void UpdateQuanHeVoiChuHo (String idNhanKhau, String quanHe) {
        String cauLenh3 = "UPDATE NhanKhau\n" +
                "SET QuanHeVoiChuHo = N'" + quanHe + "'\n" +
                "WHERE IdNhanKhau = '" + idNhanKhau + "'\n";
        ThucThiCauLenhUpdate(cauLenh3);
    }
    //Load ds khai bao tam tru tam vang
    public static void loadDsTamTruTamVang() throws SQLException {
        String cauLenh = "SELECT * FROM QuanLyTamTruTamVang";
        ResultSet resultSet =  ThucThiLechSelect(cauLenh);
        if (resultSet != null){
            while (resultSet.next()) {
                Integer IdKhaiBao = resultSet.getInt("IdKhaiBao");
                String Loai = resultSet.getString("Loai");
                String HoTen = resultSet.getString("HoTen");
                Date NgaySinh = resultSet.getDate("NgaySinh");
                String GioiTinh = resultSet.getString("GioiTinh");
                String QuocTich =resultSet.getString("QuocTich");
                String SoCCCD = resultSet.getString("SoCCCD");

                Date NgayDangKy = resultSet.getDate("NgayDangKy");
                Date NgayKetThuc = resultSet.getDate("NgayKetThuc");
                String DiaChiThuongTru = resultSet.getString("DiaChiThuongTru");
                String DiaChiTamTruTamVang = resultSet.getString("DiaChiTamTruTamVang");
                String LyDo = resultSet.getString("LyDo");
                KhaiBaoTamTruTamVang khaiBaoTamTruTamVang = new KhaiBaoTamTruTamVang(IdKhaiBao, Loai, HoTen, NgaySinh, GioiTinh, QuocTich, SoCCCD, NgayDangKy,NgayKetThuc,DiaChiThuongTru, DiaChiTamTruTamVang,LyDo);
                KhaiBaoTamTruTamVangStatic.themKhaiBaoTamTruTamVang(khaiBaoTamTruTamVang);
            }
        }
    }

    // them khai bao tam tru tam vang
    public static void themKhaiBaoTamTruTamVang(KhaiBaoTamTruTamVang khaiBaoTamTruTamVang) {
        // Them phuong thuc update so nhan Khau trong ho khau nay
        String cauLenh = "INSERT INTO QuanLyTamTruTamVang\n" +
                "VALUES (\n" +
//                "\t'"+khaiBaoTamTruTamVang.getIdKhaiBao()+"',\n" +
                "\tN'"+khaiBaoTamTruTamVang.getLoai()+"',\n" +
                "\tN'"+khaiBaoTamTruTamVang.getHoTen()+"',\n" +
                "\t'"+khaiBaoTamTruTamVang.getNgaySinh()+"',\n" +
                "\tN'"+khaiBaoTamTruTamVang.getGioiTinh()+"',\n" +
                "\tN'"+khaiBaoTamTruTamVang.getQuocTich()+"',\n" +
                "\tN'"+khaiBaoTamTruTamVang.getSoCCCD()+"',\n" +
                "\t'"+khaiBaoTamTruTamVang.getNgayDangKy()+"',\n" +
                "\t'"+khaiBaoTamTruTamVang.getNgayKetThuc()+"',\n" +
                "\tN'"+khaiBaoTamTruTamVang.getDiaChiThuongTru()+"',\n" +
                "\tN'"+khaiBaoTamTruTamVang.getDiaChiTamTruTamVang()+"',\n" +
                "\tN'"+khaiBaoTamTruTamVang.getLyDo()+"');";
        ThucThiCauLenhUpdate(cauLenh);
    }

    public static void XoaHoKhau (String idHoKhau) {
        String cauLenh1 = "DELETE FROM NhanKhau\n" +
                "WHERE IdHoKhau = '"+ idHoKhau +"'";
        String cauLenh2 = "DELETE FROM HoKhau\n" +
                "WHERE IdHoKhau = '"+ idHoKhau +"'";
        String cauLenh3 = "DELETE FROM ChiTietKhoanThu\n" +
                "WHERE IdHoKhau = '"+ idHoKhau +"'";
        String cauLenh4 = "DELETE FROM ChiTietDongGop\n" +
                "WHERE IdHoKhau = '"+ idHoKhau +"'";
        ThucThiCauLenhUpdate(cauLenh1);
        ThucThiCauLenhUpdate(cauLenh2);
        ThucThiCauLenhUpdate(cauLenh3);
        ThucThiCauLenhUpdate(cauLenh4);
    }
    // load khai bao tam tru tam vang co dk

    public static void updateHoKhauChoNhanKhau (String idNhanKhau, String idHoKhau, int chuHo, String quanHeVoiChuHo) {
        String cauLenh1 = "UPDATE NhanKhau\n" +
                "SET IdHoKhau = '"+idHoKhau+"', ChuHo = "+chuHo+", QuanHeVoiChuHo = N'"+quanHeVoiChuHo+"'\n" +
                "WHERE IdNhanKhau = '"+idNhanKhau+"'";
        ThucThiCauLenhUpdate(cauLenh1);
    }

    public static void loadDsLichSu () throws SQLException {
        String cauLenh = "SELECT * FROM LichSu\n";
        ResultSet resultSet = ThucThiLechSelect(cauLenh);
        if (resultSet != null) {
            while (resultSet.next()) {
                String idLichSu = resultSet.getString("idLichSu");
                String idHoKhau = resultSet.getString("idHoKhau");
                String noiDung = resultSet.getString("noiDung");
                String ngayThang = resultSet.getString("ngayThang");
                LichSu lichSu = new LichSu(idLichSu, noiDung, idHoKhau, ngayThang);
                LichSuStatic.getDsLichSu().add(lichSu);
            }
        }
    }

    public static void themLichSu (LichSu lichSu) {
        String cauLenh = "INSERT INTO LichSu\n" +
                "VALUES (\n" +
                "\t'"+lichSu.getIdLichSu()+"',\n" +
                "\t'"+lichSu.getIdHoKhau()+"',\n" +
                "\tN'"+lichSu.getNoiDungThayDoi()+"',\n" +
                "\t'"+lichSu.getNgayThang()+"'\n" +
                ")";
        ThucThiCauLenhUpdate(cauLenh);
    }
}
