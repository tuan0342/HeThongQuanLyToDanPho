package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import model.Users;
import model.UsersDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Vector;


// Đây là lớp xử lý phương thức tại màn hình Login


public class LoginController implements Initializable {

    @FXML
    private Button button_login;

    @FXML
    private TextField tf_username;

    @FXML
    private PasswordField pwf_password;
    private Scene nextScene;
    private Scene curScene;
    public void setNextScene() {
        FXMLLoader fxmlLoader = new FXMLLoader(SelectManagementController.
                class.getResource("/view/fxml/select-to-manage.fxml"));
        try {
            Parent root = fxmlLoader.load();
            this.nextScene = new Scene(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCurScene(Scene scene) {
        this.curScene = scene;
    }

    public Scene getCurScene() {
        return curScene;
    }

    Vector<Users> vectorUsers = new Vector<Users>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UsersDAO.setCheck(0);
        try {
            DBUtils.dbConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // Lấy thông tin tất cả các user từ database
        try {
            vectorUsers = UsersDAO.getUserFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Sự kiện liên quan đến phim 'Enter'
        tf_username.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)) {
                pwf_password.requestFocus();
            }
        });
        pwf_password.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                button_login.requestFocus();
            }
        });

        // Sự kiện khi bấm "Login"
        button_login.setOnAction(event -> {

            String usernameTF = tf_username.getText();
            String passwordPWF = pwf_password.getText();
            System.out.println("username đã nhập trên màn hình: " + usernameTF + ", pass đã nhập trên màn hình: " + passwordPWF);

            if(usersCompare(usernameTF, passwordPWF, vectorUsers)) {
                SelectManagementController.setCurScene(this.nextScene);
                SelectManagementController.setPreScene(getCurScene());
                DBUtils.changeScene(nextScene, event);
                System.out.println("-----------------------------------------------");
            } else {
                String text = "Sai thông tin đăng nhập! Nhập lại";
                ShowAlert.showAlertError("Lỗi đăng nhập", text);
                tf_username.clear();
                pwf_password.clear();
            }
        });
        setNextScene();
    }

    // Hàm tạo cảnh báo alert khi đăng nhập thất bại

    // Hàm so sánh giữa thông tin đã nhập trên màn hình và thông tin trong database
    private boolean usersCompare(String usernameTF, String passPWF, Vector<Users> vectorUsers) {
        int lenVector = vectorUsers.size();
        for (int i = 0; i < lenVector; i++) {
            if (stringCompare(usernameTF, vectorUsers.get(i).getUsername()) ==  true &&
                stringCompare(passPWF, vectorUsers.get(i).getPassword()) == true) {
                System.out.println("Đăng nhập với tư cách là: " + vectorUsers.get(i).getChucVu());
                return true;
            }
        }
        return false;
    }

    // Hàm so sánh 2 chuỗi
    private boolean stringCompare(String str1, String str2) {
        int l1 = str1.trim().length();
        int l2 = str2.trim().length();
        if (l1 != l2) {
            return false;
        }
        for (int i = 0; i < l1; i++) {
            int str1_ch = (int) str1.charAt(i);
            int str2_ch = (int) str2.charAt(i);

            if (str1_ch - str2_ch != 0) {
                return false;
            }
        }
        return true;
    }
}
