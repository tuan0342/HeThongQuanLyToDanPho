package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class HoKhau {
    private SimpleStringProperty idHoKhau;
    private SimpleStringProperty tenChuHo;
    private SimpleStringProperty diaChi;
    private SimpleIntegerProperty soLuongNhanKhau;
     // khoi tao ho khau
    public HoKhau() {

    }
    public HoKhau(String idHoKhau, String tenChuHo, String diaChi, int soLuongNhanKhau) {
        this.idHoKhau = new SimpleStringProperty(idHoKhau);
        this.tenChuHo = new SimpleStringProperty(diaChi);
        this.diaChi = new SimpleStringProperty(diaChi);
        this.soLuongNhanKhau = new SimpleIntegerProperty(soLuongNhanKhau);
    }
    //phuong thuc getter và setter tuong ung

    public String getIdHoKhau() {
        return idHoKhau.get();
    }

    public SimpleStringProperty idHoKhauProperty() {
        return idHoKhau;
    }

    public void setIdHoKhau(String idHoKhau) {
        this.idHoKhau.set(idHoKhau);
    }

    public String getTenChuHo() {
        return tenChuHo.get();
    }

    public SimpleStringProperty tenChuHoProperty() {
        return tenChuHo;
    }

    public void setTenChuHo(String tenChuHo) {
        this.tenChuHo.set(tenChuHo);
    }

    public String getDiaChi() {
        return diaChi.get();
    }

    public SimpleStringProperty diaChiProperty() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi.set(diaChi);
    }

    public int getSoLuongNhanKhau() {
        return soLuongNhanKhau.get();
    }

    public SimpleIntegerProperty soLuongNhanKhauProperty() {
        return soLuongNhanKhau;
    }

    public void setSoLuongNhanKhau(int soLuongNhanKhau) {
        this.soLuongNhanKhau.set(soLuongNhanKhau);
    }
}
