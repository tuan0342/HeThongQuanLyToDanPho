package model;

import controller.DBUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HoKhauDAO {

    // Thêm dữ liệu HoKhau vào database
    public static void insertHoKhau() throws SQLException, ClassNotFoundException {

    }
    // -------------------------------

    // Xóa dữ liệu HoKhau khỏi database
    public static void deleteHoKhau() throws SQLException, ClassNotFoundException {

    }
    // -------------------------------


    // Lấy thông tin tất cả dữ liệu của HoKhau trong database dưới dạng ObservableList
    public static ObservableList<HoKhau> getAllRecord() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM ....";

        try {
            ResultSet rs = DBUtils.dbExecute(sql);
            ObservableList<HoKhau> hoKhauList = getHoKhau(rs);
            return hoKhauList;
        }
        catch (SQLException e) {
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }

    private static ObservableList<HoKhau>getHoKhau(ResultSet resultSet) throws SQLException,ClassNotFoundException {
        try{
            ArrayList<HoKhau> hoKhauArrayList = new ArrayList<HoKhau>();
            while(resultSet.next()) {
                HoKhau hoKhau = new HoKhau();
                // sử dụng các phương thức set trong HoKhau để set thông tin

                hoKhauArrayList.add(hoKhau);
            }
            ObservableList<HoKhau> hoKhauList = FXCollections.observableArrayList(hoKhauArrayList);
            return hoKhauList;
        }
        catch (SQLException e) {
            System.out.println("Error occured while fetching the data from DB " + e);
            e.printStackTrace();
            throw e;
        }
    }
    // -------------------------------
}
