package model;

public class DongGop {
    private int idKhoanDG;
    private String tenKhoanDG;
    private String ngayBD;
    private String ngayKT;

    public DongGop() {
    }

    public DongGop(int idKhoanDG, String tenKhoanDG, String ngayBD, String ngayKT) {
        this.idKhoanDG = idKhoanDG;
        this.tenKhoanDG = tenKhoanDG;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
    }

    public int getIdKhoanDG() {
        return idKhoanDG;
    }

    public void setIdKhoanDG(int idKhoanDG) {
        this.idKhoanDG = idKhoanDG;
    }

    public String getTenKhoanDG() {
        return tenKhoanDG;
    }

    public void setTenKhoanDG(String tenKhoanDG) {
        this.tenKhoanDG = tenKhoanDG;
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
