package model;

import controller.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class UsersDAO {

    // Lấy thông tin user trong database dưới dạng Vector
    private static int check  = 1;

    public static int getCheck() {
        return check;
    }

    public static void setCheck(int check) {
        UsersDAO.check = check;
    }

    public static Vector<Users> getUserFromDB() throws SQLException, ClassNotFoundException {
        String sql = "select * from [dbo].[Users]";

        try{
            ResultSet rs = DBUtils.dbExecute(sql);
            Vector<Users> vec = getVectorUser(rs);
            return vec;
        } catch (SQLException e) {
            System.out.println("Đã xảy ra lỗi trong quá trình lấy records Users từ DB: " + e);
            e.printStackTrace();
            throw e;
        }
    }

    private static Vector<Users> getVectorUser(ResultSet rs) {
        Vector<Users> vec = new Vector<Users>();
        System.out.println("Thông tin tất cả các user có trong database: ");
        if(rs != null) {
            try {
                while(rs.next()) {
                    System.out.println("abc");
                    Users users = new Users(rs.getString(1).trim(), rs.getString(2).trim(),
                            rs.getString(3).trim());
                    vec.add(users);
                    System.out.println("\t" + users + "\n");
                }
            } catch (SQLException e) {
                System.out.println("Đã xảy ra lỗi trong quá trình lấy dữ liệu Users dưới dạng vector từ DB: " + e);
                e.printStackTrace();
            }
        }
        return vec;
    }
}
