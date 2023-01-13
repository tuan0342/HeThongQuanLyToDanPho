package model;

import java.util.Date;
import java.util.List;

public class KhoanThu {
    public KhoanThu () {

    };

    public KhoanThu(String idKhoanThu, String tenKhoanThu, int soTien, Date ngay) {
        this.idKhoanThu = idKhoanThu;
        this.tenKhoanThu = tenKhoanThu;
        this.soTien = soTien;
        this.ngay = ngay;
    }
    private String idKhoanThu;
    private String tenKhoanThu;
    private int soTien;
    private Date ngay;

    public String getIdKhoanThu() {
        return idKhoanThu;
    }

    public void setIdKhoanThu(String idKhoanThu) {
        this.idKhoanThu = idKhoanThu;
    }

    public String getTenKhoanThu() {
        return tenKhoanThu;
    }

    public void setTenKhoanThu(String tenKhoanThu) {
        this.tenKhoanThu = tenKhoanThu;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }
}
