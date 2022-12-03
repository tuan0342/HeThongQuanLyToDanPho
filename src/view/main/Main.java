package view.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    // #FED700 yellow
    // #807d6e grey
    //--module-path "C:\Program Files\Java\javafx-sdk-18.0.1\lib" --add-modules javafx.controls,javafx.fxml

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/fxml/logged-in.fxml"));
        primaryStage.setTitle("Log in!");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false); // cố định màn hình
        primaryStage.show();
    }
}
