package model;

import java.time.LocalDate;
import java.util.Date;

public class NhanKhau {
    private String idNhanKhau;
    private String idHoKhau;
    private String hoTen;
    private String biDanh;
    private Date ngaySinh;
    private String nguyenQuan;
    private String danToc;
    private String ngheNghiep;
    private String noiLamViec;
    private int soCCCD;
    private Date ngayCap;
    private String noiCap;
    private Date thoiGianDKThuongTru;
    private String diaChiThuongTru;
    private String quanHeChuHo;

    private int chuHo;
    // khởi tạo nhân khẩu
    public NhanKhau () {
        setChuHo(0);
    }

    public NhanKhau(String idNhanKhau, String idHoKhau, String hoTen, String biDanh, Date ngaySinh, String nguyenQuan, String danToc, String ngheNghiep, String noiLamViec, int soCCCD, Date ngayCap, String noiCap, Date thoiGianDKThuongTru, String diaChiThuongTru, String quanHeChuHo, int chuHo) {
        this.idNhanKhau = idNhanKhau;
        this.idHoKhau = idHoKhau;
        this.hoTen = hoTen;
        this.biDanh = biDanh;
        this.ngaySinh = ngaySinh;
        this.nguyenQuan = nguyenQuan;
        this.danToc = danToc;
        this.ngheNghiep = ngheNghiep;
        this.noiLamViec = noiLamViec;
        this.soCCCD = soCCCD;
        this.ngayCap = ngayCap;
        this.noiCap = noiCap;
        this.thoiGianDKThuongTru = thoiGianDKThuongTru;
        this.diaChiThuongTru = diaChiThuongTru;
        this.quanHeChuHo = quanHeChuHo;
        this.chuHo = chuHo;
    }

    public String getIdNhanKhau() {
        return idNhanKhau;
    }

    public void setIdNhanKhau(String idNhanKhau) {
        this.idNhanKhau = idNhanKhau;
    }

    public String getIdHoKhau() {
        return idHoKhau;
    }

    public void setIdHoKhau(String idHoKhau) {
        this.idHoKhau = idHoKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getBiDanh() {
        return biDanh;
    }

    public void setBiDanh(String biDanh) {
        this.biDanh = biDanh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getNguyenQuan() {
        return nguyenQuan;
    }

    public void setNguyenQuan(String nguyenQuan) {
        this.nguyenQuan = nguyenQuan;
    }

    public String getDanToc() {
        return danToc;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public String getNoiLamViec() {
        return noiLamViec;
    }

    public void setNoiLamViec(String noiLamViec) {
        this.noiLamViec = noiLamViec;
    }

    public int getSoCCCD() {
        return soCCCD;
    }

    public void setSoCCCD(int soCCCD) {
        this.soCCCD = soCCCD;
    }

    public Date getNgayCap() {
        return ngayCap;
    }

    public void setNgayCap(Date ngayCap) {
        this.ngayCap = ngayCap;
    }

    public String getNoiCap() {
        return noiCap;
    }

    public void setNoiCap(String noiCap) {
        this.noiCap = noiCap;
    }

    public Date getThoiGianDKThuongTru() {
        return thoiGianDKThuongTru;
    }

    public void setThoiGianDKThuongTru(Date thoiGianDKThuongTru) {
        this.thoiGianDKThuongTru = thoiGianDKThuongTru;
    }

    public String getDiaChiThuongTru() {
        return diaChiThuongTru;
    }

    public void setDiaChiThuongTru(String diaChiThuongTru) {
        this.diaChiThuongTru = diaChiThuongTru;
    }

    public String getQuanHeChuHo() {
        return quanHeChuHo;
    }

    public void setQuanHeChuHo(String quanHeChuHo) {
        this.quanHeChuHo = quanHeChuHo;
    }

    public int getChuHo() {
        return chuHo;
    }

    public void setChuHo(int chuHo) {
        this.chuHo = chuHo;
    }

    public boolean timTheoHoKhau (String idHoKhau) {
        if (getIdHoKhau().compareTo(idHoKhau) == 0) return true;
        return false;
    }
}
