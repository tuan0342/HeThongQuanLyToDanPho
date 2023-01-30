package model;

public class ThongKeDongGop {
    String tenKhoanDG;
    String tongSoHo;
    String tongSoTien;

    public ThongKeDongGop() {
    }

    public ThongKeDongGop(String tenKhoanDG, String tongSoHo, String tongSoTien) {
        this.tenKhoanDG = tenKhoanDG;
        this.tongSoHo = tongSoHo;
        this.tongSoTien = tongSoTien;
    }

    public String getTenKhoanDG() {
        return tenKhoanDG;
    }

    public void setTenKhoanDG(String tenKhoanDG) {
        this.tenKhoanDG = tenKhoanDG;
    }

    public String getTongSoHo() {
        return tongSoHo;
    }

    public void setTongSoHo(String tongSoHo) {
        this.tongSoHo = tongSoHo;
    }

    public String getTongSoTien() {
        return tongSoTien;
    }

    public void setTongSoTien(String tongSoTien) {
        this.tongSoTien = tongSoTien;
    }
}
