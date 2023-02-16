package model;

import controller.DBUtils;
import controller.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LichSuStatic {
    private static ObservableList<LichSu> dsLichSu = FXCollections.observableArrayList();

    public static ObservableList<LichSu> getDsLichSu() {
        return dsLichSu;
    }

    public static void setDsLichSu(ObservableList<LichSu> dsLichSu) {
        LichSuStatic.dsLichSu = dsLichSu;
    }

    public static void taoLichSu (String idHoKhau, String thaoTac, String noiDung) {
        String idLichSu = model.SinhNgauNhien.sinhIdLichSu(thaoTac);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LichSu lichSu = new LichSu(idLichSu, thaoTac, noiDung, idHoKhau, LocalDateTime.now().format(formatter));
        dsLichSu.add(lichSu);
        DBUtils.themLichSu(lichSu);
    }
}