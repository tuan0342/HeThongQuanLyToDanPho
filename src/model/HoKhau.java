package model;

import javafx.beans.property.SimpleStringProperty;

public class HoKhau {
    private SimpleStringProperty idHoKhau;
    private SimpleStringProperty tenChuHo;
    private SimpleStringProperty diaChi;
    private SimpleStringProperty soLuongNhanKhau;
     // khoi tao ho khau
    public HoKhau(SimpleStringProperty idHoKhau, SimpleStringProperty tenChuHo, SimpleStringProperty diaChi, SimpleStringProperty soLuongNhanKhau) {
        this.idHoKhau = idHoKhau;
        this.tenChuHo = tenChuHo;
        this.diaChi = diaChi;
        this.soLuongNhanKhau = soLuongNhanKhau;
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

    public String getSoLuongNhanKhau() {
        return soLuongNhanKhau.get();
    }

    public SimpleStringProperty soLuongNhanKhauProperty() {
        return soLuongNhanKhau;
    }

    public void setSoLuongNhanKhau(String soLuongNhanKhau) {
        this.soLuongNhanKhau.set(soLuongNhanKhau);
    }
}
