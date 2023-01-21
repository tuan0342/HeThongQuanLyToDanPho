package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class ShowAlert {
    public static void showAlertError (String header, String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Cánh báo");
        alert.setHeaderText(header);
        alert.setContentText(text);

        ButtonType close = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(close);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == close) {
            alert.close();
        }
    }

    public static boolean showAlertYN (String header, String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Cánh báo");
        alert.setHeaderText(header);
        alert.setContentText(text);

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(yes, no);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == yes) {
            alert.close();
            return true;
        } else if (result.get() == no) {
            alert.close();
            return false;
        }
        alert.close();
        return false;
    }
}
