package model;

import controller.DBUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class KhaiBaoTamTruTamVangStatic {
    private static ObservableList<KhaiBaoTamTruTamVang> dsKhaiBaoTamTruTamVang = FXCollections.observableArrayList();


    public static void themKhaiBaoTamTruTamVang (KhaiBaoTamTruTamVang khaiBaoTamTruTamVang) {
        KhaiBaoTamTruTamVangStatic.dsKhaiBaoTamTruTamVang.add(khaiBaoTamTruTamVang);
    }
    public static ObservableList<KhaiBaoTamTruTamVang> getDsKhaiBaoTamTruTamVang() {
        return dsKhaiBaoTamTruTamVang;
    }


    public static ObservableList<KhaiBaoTamTruTamVang> getAlLRecords() {
        String sql = "select * from [dbo].[QuanLyTamTruTamVang]";
        ObservableList<KhaiBaoTamTruTamVang> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBUtils.dbExecute(sql);
            while(rs.next()) {
                KhaiBaoTamTruTamVang kbtttv = new KhaiBaoTamTruTamVang(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getDate(4),rs.getString(5) , rs.getString(6), rs.getString(7), rs.getDate(8), rs.getDate(9), rs.getString(10), rs.getString(11), rs.getString(12));
                list.add(kbtttv);
            }

        } catch (Exception e) {
            System.out.println("Không lấy được dữ liệu");
        }
        return list;
    }

    //lay cac ban ghi co dieu kien
    public static ObservableList<KhaiBaoTamTruTamVang> getPartRecords(String searchText) {
        String sql = new String();
        try
        {
             Integer.parseInt(searchText);
             sql = "select * from [dbo].[QuanLyTamTruTamVang] where IdKhaiBao = " +Integer.valueOf(searchText);
             System.out.println(sql);

        } catch (Exception e){
            if (searchText == "Tạm Trú" || searchText == "Tạm Vắng") {
                sql = "select * from [dbo].[QuanLyTamTruTamVang] where Loai = " + "N'" + searchText + "'";
                System.out.println(sql);
            }

            sql = "select * from [dbo].[QuanLyTamTruTamVang] where HoTen = " + "N'"+ searchText +"'";
            System.out.println(sql);
        }

        ObservableList<KhaiBaoTamTruTamVang> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBUtils.dbExecute(sql);
            while(rs.next()) {
                KhaiBaoTamTruTamVang kbtttv = new KhaiBaoTamTruTamVang(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getDate(4),rs.getString(5) , rs.getString(6), rs.getString(7), rs.getDate(8), rs.getDate(9), rs.getString(10), rs.getString(11), rs.getString(12));
                list.add(kbtttv);
            }

        } catch (Exception e) {
            System.out.println("Không lấy được dữ liệu");
        }
        return list;
    }


}
