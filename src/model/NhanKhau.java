package model;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.Date;

public class NhanKhau {
    private String idNhanKhau;
    private String idHoKhau;
    private String hoTen;
    private boolean gioiTinh;
    private String biDanh;
    private Date ngaySinh;
    private String nguyenQuan;
    private String danToc;
    private String ngheNghiep;
    private String noiLamViec;
    private String soCCCD;
    private Date ngayCap;
    private String noiCap;
    private Date thoiGianDKThuongTru;
    private String diaChiThuongTru;
    private String quanHeChuHo;
    private CheckBox chonHayKhong;
    private TextField thuCanDien;


    private int chuHo;
    // khởi tạo nhân khẩu
    public NhanKhau () {
        setChuHo(0);
    }

    public NhanKhau(String idNhanKhau, String idHoKhau, String hoTen, boolean gioiTinh, String biDanh, Date ngaySinh, String nguyenQuan, String danToc, String ngheNghiep, String noiLamViec, String soCCCD, Date ngayCap, String noiCap, Date thoiGianDKThuongTru, String diaChiThuongTru, String quanHeChuHo, int chuHo) {
        this.idNhanKhau = idNhanKhau;
        this.idHoKhau = idHoKhau;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
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

    public String getSoCCCD() {
        return soCCCD;
    }

    public void setSoCCCD(String soCCCD) {
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

    public boolean timTheoHoKhauNhungNhanKhauKhongPhaiChuHo (String idHoKhau) {
        if (getIdHoKhau().compareTo(idHoKhau) == 0 && getChuHo() != 1) return true;
        return false;
    }

    public boolean timTheoNhanKhau (String idNhanKhau) {
        if (getIdNhanKhau().compareTo(idNhanKhau) == 0) return true;
        return false;
    }

    public boolean timTheoTenNhanKhau (String ten) {
        if (getHoTen().compareTo(ten) == 0) return true;
        return false;
    }

    public boolean timChuHo () {
        if (getChuHo() != 0) return true;
        return false;
    }
    public String getGioiTinh () {
        if (gioiTinh) return "Nam";
        else return "Nữ";
    }

    public boolean getGioiTinhBool () {
        return this.gioiTinh;
    }

    public void setGioiTinh (boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public CheckBox getChonHayKhong() {
        return chonHayKhong;
    }

    public void setChonHayKhong(CheckBox chonHayKhong) {
        this.chonHayKhong = chonHayKhong;
    }

    public TextField getThuCanDien() {
        return thuCanDien;
    }

    public void setThuCanDien(TextField thuCanDien) {
        this.thuCanDien = thuCanDien;
    }

    public void canThiMoiGoi () {
        chonHayKhong = new CheckBox();
        thuCanDien = new TextField();
    }

    @Override
    public String toString() {
        return "NhanKhau{" +
                "idNhanKhau=" + idNhanKhau +
                ", idHoKhau=" + idHoKhau +
                ", hoTen=" + hoTen +
                ", gioiTinh=" + getGioiTinh()+
                ", biDanh=" + biDanh +
                ", ngaySinh=" + ngaySinh +
                ", nguyenQuan=" + nguyenQuan +
                ", danToc=" + danToc +
                ", ngheNghiep=" + ngheNghiep +
                ", noiLamViec=" + noiLamViec +
                ", soCCCD=" + soCCCD +
                ", ngayCap=" + ngayCap +
                ", noiCap=" + noiCap +
                ", thoiGianDKThuongTru=" + thoiGianDKThuongTru +
                ", diaChiThuongTru=" + diaChiThuongTru +
                ", quanHeChuHo=" + quanHeChuHo +
                '}';
    }
}
