package model;

import controller.DBUtils;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class ThongKeStatic {
    public static ObservableList<Integer> getDataTheoGioiTinh() {
        String sql = "select COUNT(GioiTinh) as SoLuong from NhanKhau group by GioiTinh; ";
        ObservableList<Integer> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBUtils.dbExecute(sql);
            while(rs.next()) {
                list.add(rs.getInt(1));

            }
        } catch (Exception e) {
            System.out.println("Không lấy được dữ liệu");
        }
        return list;
    }

    //lay du lieu theo do tuoi

    public  static ObservableList<Integer> getDatamamnon() {
        String sql = "select COUNT(IdNhanKhau) as mamnon from NhanKhau where YEAR(getdate()) - year(NhanKhau.NgaySinh) < 3 ";
        ObservableList<Integer> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBUtils.dbExecute(sql);
            while(rs.next()) {
                list.add(rs.getInt(1));

            }
        } catch (Exception e) {
            System.out.println("Không lấy được dữ liệu");
        }
        return list;

    }

    public  static ObservableList<Integer> getDatamaugiao() {
        String sql = "select COUNT(IdNhanKhau) as maugiao from NhanKhau where YEAR(getdate()) - year(NhanKhau.NgaySinh) >= 3 and YEAR(getdate()) - year(NhanKhau.NgaySinh) <= 5 ";
        ObservableList<Integer> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBUtils.dbExecute(sql);
            while(rs.next()) {
                list.add(rs.getInt(1));

            }
        } catch (Exception e) {
            System.out.println("Không lấy được dữ liệu");
        }
        return list;
    }

    public  static ObservableList<Integer> getDatacap1() {
        String sql = "select COUNT(IdNhanKhau) as cap1 from NhanKhau where YEAR(getdate()) - year(NhanKhau.NgaySinh) >= 6 and YEAR(getdate()) - year(NhanKhau.NgaySinh) <= 10";
        ObservableList<Integer> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBUtils.dbExecute(sql);
            while(rs.next()) {
                list.add(rs.getInt(1));

            }
        } catch (Exception e) {
            System.out.println("Không lấy được dữ liệu");
        }
        return list;
    }
    public  static ObservableList<Integer> getDatacap2() {
        String sql = "select COUNT(IdNhanKhau) as CAP2 from NhanKhau where YEAR(getdate()) - year(NhanKhau.NgaySinh) >= 11 and YEAR(getdate()) - year(NhanKhau.NgaySinh) <= 14";
        ObservableList<Integer> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBUtils.dbExecute(sql);
            while(rs.next()) {
                list.add(rs.getInt(1));

            }
        } catch (Exception e) {
            System.out.println("Không lấy được dữ liệu");
        }
        return list;
    }
    public  static ObservableList<Integer> getDatacap3() {
        String sql = "select COUNT(IdNhanKhau) as CAP3 from NhanKhau where YEAR(getdate()) - year(NhanKhau.NgaySinh) >= 15 and YEAR(getdate()) - year(NhanKhau.NgaySinh) <= 17";
        ObservableList<Integer> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBUtils.dbExecute(sql);
            while(rs.next()) {
                list.add(rs.getInt(1));

            }
        } catch (Exception e) {
            System.out.println("Không lấy được dữ liệu");
        }
        return list;
    }
    public  static ObservableList<Integer> getDataLaoDong() {
        String sql = "select COUNT(IdNhanKhau) as LAODONG from NhanKhau where YEAR(getdate()) - year(NhanKhau.NgaySinh) >= 18 and YEAR(getdate()) - year(NhanKhau.NgaySinh) <= 62";
        ObservableList<Integer> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBUtils.dbExecute(sql);
            while(rs.next()) {
                list.add(rs.getInt(1));

            }
        } catch (Exception e) {
            System.out.println("Không lấy được dữ liệu");
        }
        return list;
    }

    public  static ObservableList<Integer> getDataNghiHuu() {
        String sql = "select COUNT(IdNhanKhau) as NGHIHUU from NhanKhau where YEAR(getdate()) - year(NhanKhau.NgaySinh) >62";
        ObservableList<Integer> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBUtils.dbExecute(sql);
            while(rs.next()) {
                list.add(rs.getInt(1));

            }
        } catch (Exception e) {
            System.out.println("Không lấy được dữ liệu");
        }
        return list;
    }

    //lay du lieu cho bieu do thong ke theo thoi gian

    public static ObservableList<Integer> getDataTheoThoiGian(int nam) {

        ObservableList<Integer> list = FXCollections.observableArrayList();
        String sql = "select COUNT(IdNhanKhau) as soluong from NhanKhau Where Year(NhanKhau.NgaySinh) <= " + nam;
        System.out.println(sql);
        try {
            ResultSet rs = DBUtils.dbExecute(sql);
            while(rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (Exception e) {
            System.out.println("Không lấy được dữ liệu");
        }
        return list;
    }


    //lay du lieu cho bieu do thong ke theo tam tru tam vang

    //1.lay du lieu tam tru
    public static ObservableList<Integer> getDataTheoTamTru(int nam) {

        ObservableList<Integer> list = FXCollections.observableArrayList();
        String sql = "select count(IdKhaiBao) from QuanLyTamTruTamVang where QuanLyTamTruTamVang.Loai = N'Tạm Trú'  and year(QuanLyTamTruTamVang.NgayDangKy) <= " + nam
                + " and Year(QuanLyTamTruTamVang.NgayKetThuc) >= " + nam;
        System.out.println(sql);
        try {
            ResultSet rs = DBUtils.dbExecute(sql);
            while(rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (Exception e) {
            System.out.println("Không lấy được dữ liệu");
        }
        return list;
    }
    //2.lay du lieu tam vang
    public static ObservableList<Integer> getDataTheoTamVang(int nam) {

        ObservableList<Integer> list = FXCollections.observableArrayList();
        String sql = "select count(IdKhaiBao) from QuanLyTamTruTamVang where QuanLyTamTruTamVang.Loai = N'Tạm Vắng' and year(QuanLyTamTruTamVang.NgayDangKy) <= " + nam
                + " and Year(QuanLyTamTruTamVang.NgayKetThuc) >= " + nam;
        System.out.println(sql);
        try {
            ResultSet rs = DBUtils.dbExecute(sql);
            while(rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (Exception e) {
            System.out.println("Không lấy được dữ liệu");
        }
        return list;
    }














}
