package model;

public class ChiTietDongGop {
    private int idDongGop;
    private String idHoKhau;
    private String tenChuHo;
    private int soTien;

    public ChiTietDongGop() {
    }

    public ChiTietDongGop(int idDongGop, String idHoKhau, String tenChuHo, int soTien) {
        this.idDongGop = idDongGop;
        this.idHoKhau = idHoKhau;
        this.tenChuHo = tenChuHo;
        this.soTien = soTien;
    }

    public int getIdDongGop() {
        return idDongGop;
    }

    public void setIdDongGop(int idDongGop) {
        this.idDongGop = idDongGop;
    }

    public String getIdHoKhau() {
        return idHoKhau;
    }

    public void setIdHoKhau(String idHoKhau) {
        this.idHoKhau = idHoKhau;
    }

    public String getTenChuHo() {
        return tenChuHo;
    }

    public void setTenChuHo(String tenChuHo) {
        this.tenChuHo = tenChuHo;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }
}
