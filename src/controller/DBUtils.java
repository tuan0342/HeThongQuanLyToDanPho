package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

public class DBUtils {

    private static String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhanKhau;encrypt=true;trustServerCertificate=true;";
    private static String user = "sa"; // user trên mỗi máy là khác nhau tùy cá nhân tự đặt
    private static String pass = "123";  // pass trên mỗi máy là khác nhau tùy cá nhân tự đặt
    private static Connection connection = null;

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
        try {
            dbConnect();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sqlQuery);
        } catch (SQLException e) {
            System.out.println("Có lỗi xảy ra ở hàm dbExecute (truy xuất dữ liệu)");
            throw e;
        } finally {

        }
        return rs;
    }

    // Phương thức chuyển màn hình
    public static void changeScene(ActionEvent event, String fxmlFile, String title) {
        Parent root = null;

        try {
            root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 1000, 600));
        stage.setResizable(false);
        stage.show();
    }
}
