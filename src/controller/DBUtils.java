package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class DBUtils {

    private static String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=MoneyMeals;encrypt=true;trustServerCertificate=true;";
    private static String user = "sa"; // user trên mỗi máy là khác nhau tùy cá nhân tự đặt
    private static String pass = "123";  // pass trên mỗi máy là khác nhau tùy cá nhân tự đặt
    private static Connection connection = null;

    // connect database
    public static void dbConnect() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your SQL Server JDBC Driver?");
            e.printStackTrace();
            throw e;
        }
        System.out.println("\nJDBC Driver has been registered (Kết nối thành công)\n");
        try {
            connection = DriverManager.getConnection(dbURL, user, pass);
        } catch(SQLException e) {
            System.out.println("Connection failed! Check output console " + e);
            throw e;
        }
    }

    // disconnect database
    public static void dbDisconnect() throws SQLException{
        try {
            if(connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // this if for insert/ delete/ update operation (thao tác thêm/ xóa/ cập nhật cơ sở dữ liệu)
    public static void dbExcecuteQuery(String sqlStmt) throws SQLException, ClassNotFoundException{
        Statement stmt = null;
        try{
            dbConnect();
            stmt = connection.createStatement();
            stmt.executeUpdate(sqlStmt);
        }
        catch (SQLException e) {
            System.out.println("Problem occured at dbExecuteQuery operation " + e);
            throw e;
        }
        finally {
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
    }

    // this is for retriving the records from the database (lấy dữ liệu trong cơ sở dữ liệu)
    public static ResultSet dbExecute(String sqlQuery) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        ResultSet rs = null;

        try {
            dbConnect();
            stmt = connection.createStatement();
            rs= stmt.executeQuery(sqlQuery);
        }
        catch (SQLException e) {
            System.out.println("Error occured in dbExecute operation " + e);
            throw e;
        }
        finally {

        }
        return rs;
    }

    // Phương thức chuyển màn hình
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username) {
        Parent root = null;

        if (username != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                //SelectManagementController selectManagementController = loader.getController();
            } catch(IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 1000, 600));
        stage.setResizable(false);
        stage.show();
    }

    // Sư kiện khi bấm "Log in" (kiểm tra mật khẩu, username xem đúng ko rồi mới chuyển màn hình)
    public static void loginUser(ActionEvent event, String username, String password) {
        //Connection connection = null;
        //PreparedStatement preparedStatement = null;
        //ResultSet resultSet = null;

        try {
            changeScene(event, "/view/fxml/select-to-manage.fxml", "Wellcome", username);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
