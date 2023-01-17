package model;

public class ChiTietKhoanThu {
    private int idKhoanThu;
    private String idHoKhau;
    private String tenChuHo;
    private int soTien;
    private int trangThai;

    public ChiTietKhoanThu() {
    }

    public ChiTietKhoanThu(int idKhoanThu, String idHoKhau, String tenChuHo, int soTien, int trangThai) {
        this.idKhoanThu = idKhoanThu;
        this.idHoKhau = idHoKhau;
        this.tenChuHo = tenChuHo;
        this.soTien = soTien;
        this.trangThai = trangThai;
    }

    public String getTenChuHo() {
        return tenChuHo;
    }

    public void setTenChuHo(String tenChuHo) {
        this.tenChuHo = tenChuHo;
    }

    public int getIdKhoanThu() {
        return idKhoanThu;
    }

    public void setIdKhoanThu(int idKhoanThu) {
        this.idKhoanThu = idKhoanThu;
    }

    public String getIdHoKhau() {
        return idHoKhau;
    }

    public void setIdHoKhau(String idHoKhau) {
        this.idHoKhau = idHoKhau;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "ChiTietKhoanThu{" +
                "idKhoanThu=" + idKhoanThu +
                ", idHoKhau='" + idHoKhau + '\'' +
                ", tenChuHo='" + tenChuHo + '\'' +
                ", soTien=" + soTien +
                ", trangThai=" + trangThai +
                '}';
    }
}
