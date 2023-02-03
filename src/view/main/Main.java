package view.main;

import controller.DBUtils;
import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {

    // #FED700 yellow
    // #807d6e grey
    //--module-path "C:\Program Files\Java\javafx-sdk-18.0.1\lib" --add-modules javafx.controls,javafx.fxml
    // Quản lý tổ dân phố
    // Hạnh vippro    #EDDBC7: màu nền

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/view/fxml/logged-in.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Log in!"); // set title
        Scene scene = new Scene(root, 1000, 600);

        LoginController controller = fxmlLoader.getController();
        controller.setCurScene(scene);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false); // cố định màn hình
        primaryStage.show();
    }
} 
