package controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

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

    public static String dienLiDo () {
        Dialog<ButtonType> tb = new Dialog<ButtonType>();
        tb.setTitle("Lí do xóa nhân khẩu");
        ButtonType OK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType CANCEL = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);
        tb.getDialogPane().getButtonTypes().addAll(CANCEL);
        ComboBox<String> LiDo = new ComboBox<String>();
        Label label0 = new Label("Lí do xóa nhân khẩu");
        ObservableList<String> ds = FXCollections.observableArrayList("Chuyển đi", "Mất", "Khác");
        LiDo.setItems(ds);
        TextField chiTiet = new TextField();
        VBox khung = new VBox(8, label0, LiDo);
        Label label1 = new Label("");
        DatePicker datePicker = new DatePicker();
        datePicker.setVisible(false);
        datePicker.setPrefHeight(200);
        Label label2 = new Label("");
        TextField textField = new TextField();
        textField.setVisible(false);
        textField.setPrefHeight(200);
        khung.getChildren().addAll(label1, datePicker, label2, textField);
        LiDo.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            tb.getDialogPane().getButtonTypes().remove(OK);
            tb.getDialogPane().getButtonTypes().add(OK);
            datePicker.setVisible(true);
            textField.setVisible(true);
            if (newValue.compareTo("Chuyển đi") == 0) {
                label1.setText("Thời gian chuyển đi");
                label2.setText("Nơi chuyển đi");
            } else {
                if (newValue.compareTo("Mất") == 0) {
                    label1.setText("Thời gian mất");
                    label2.setText("Chi tiết");
                } else {
                    if (newValue.compareTo("Khác") == 0) {
                        label1.setText("Thời gian chuyển hộ");
                        label2.setText("Chi tiết");
                    }
                }
            }
        });
//        datePicker.valueProperty().addListener((observable, oldDate, newDate) -> {
//            if(!tb.getDialogPane().getButtonTypes().contains(tb.getDialogPane().getButtonTypes().add(OK))) {
//                tb.getDialogPane().getButtonTypes().add(OK);
//            }
//        });
        tb.setResizable(true);
        tb.getDialogPane().setPrefSize(400, 233);
        tb.getDialogPane().setContent(khung);
        Platform.runLater(() -> tb.setResizable(false));
        Optional<ButtonType> result = tb.showAndWait();
        if (result.get() == OK) {
            return "Lí do xóa: " + LiDo.getValue() + " ngày: " + datePicker.getValue() + " chi tiết: " + textField.getText() + "\n";
        }
        return null;
    }
}
