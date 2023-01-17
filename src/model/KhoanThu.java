package model;

import java.util.Date;
import java.util.List;

public class KhoanThu {

    private int idKhoanThu;
    private String tenKhoanThu;
    private int soTien;
    private String ngayBD;
    private String ngayKT;

    public KhoanThu () {
    };

    public KhoanThu(int idKhoanThu, String tenKhoanThu, String ngayBD, String ngayKT, int soTien) {
        this.idKhoanThu = idKhoanThu;
        this.tenKhoanThu = tenKhoanThu;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.soTien = soTien;
    }


    public int getIdKhoanThu() {
        return idKhoanThu;
    }

    public void setIdKhoanThu(int idKhoanThu) {
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

    public String getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(String ngayBD) {
        this.ngayBD = ngayBD;
    }

    public String getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(String ngayKT) {
        this.ngayKT = ngayKT;
    }
}
