package model;

public class HoKhau {
    private String idHoKhau;
    private String tenChuHo;
    private String diaChi;
    private int soLuongNhanKhau;
    private NhanKhau ChuHo;     // khoi tao ho khau
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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getSoLuongNhanKhau() {
        return soLuongNhanKhau;
    }

    public void setSoLuongNhanKhau(int soLuongNhanKhau) {
        this.soLuongNhanKhau = soLuongNhanKhau;
    }

    public NhanKhau getChuHo() {
        return ChuHo;
    }

    public void setChuHo(NhanKhau chuHo) {
        ChuHo = chuHo;
        setTenChuHo(chuHo.getHoTen());
    }

    public HoKhau () {
    }

    public HoKhau(String idHoKhau, String tenChuHo, String diaChi, int soLuongNhanKhau, NhanKhau chuHo) {
        this.idHoKhau = idHoKhau;
        this.tenChuHo = tenChuHo;
        this.diaChi = diaChi;
        this.soLuongNhanKhau = soLuongNhanKhau;
        ChuHo = chuHo;
    }

    public HoKhau(String idHoKhau, String tenChuHo, String diaChi, int soLuongNhanKhau) {
        this.idHoKhau = idHoKhau;
        this.tenChuHo = tenChuHo;
        this.diaChi = diaChi;
        this.soLuongNhanKhau = soLuongNhanKhau;
    }
}
