package model;

import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class NhanKhau {
    private SimpleStringProperty idNhanKhau;
    private SimpleStringProperty idHoKhau;
    private SimpleStringProperty hoTen;
    private SimpleStringProperty biDanh;
    private SimpleStringProperty ngaySinh;
    private SimpleStringProperty nguyenQuan;
    private SimpleStringProperty danToc;
    private SimpleStringProperty ngheNghiep;
    private SimpleStringProperty noiLamViec;
    private SimpleStringProperty soCCCD;
    private SimpleStringProperty ngayCap;
    private SimpleStringProperty noiCap;
    private Date thoiGianDKThuongTru;
    private SimpleStringProperty diaChiThuongTru;
    private SimpleStringProperty quanHeChuHo;
    // khởi tạo nhân khẩu
    public NhanKhau(SimpleStringProperty idNhanKhau, SimpleStringProperty idHoKhau, SimpleStringProperty hoTen, SimpleStringProperty biDanh, SimpleStringProperty ngaySinh, SimpleStringProperty nguyenQuan, SimpleStringProperty danToc, SimpleStringProperty ngheNghiep, SimpleStringProperty noiLamViec, SimpleStringProperty soCCCD, SimpleStringProperty ngayCap, SimpleStringProperty noiCap, Date thoiGianDKThuongTru, SimpleStringProperty diaChiThuongTru, SimpleStringProperty quanHeChuHo) {
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
    }

    // các getter và setter tuong ung
    public String getIdNhanKhau() {
        return idNhanKhau.get();
    }

    public SimpleStringProperty idNhanKhauProperty() {
        return idNhanKhau;
    }

    public void setIdNhanKhau(String idNhanKhau) {
        this.idNhanKhau.set(idNhanKhau);
    }

    public String getIdHoKhau() {
        return idHoKhau.get();
    }

    public SimpleStringProperty idHoKhauProperty() {
        return idHoKhau;
    }

    public void setIdHoKhau(String idHoKhau) {
        this.idHoKhau.set(idHoKhau);
    }

    public String getHoTen() {
        return hoTen.get();
    }

    public SimpleStringProperty hoTenProperty() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen.set(hoTen);
    }

    public String getBiDanh() {
        return biDanh.get();
    }

    public SimpleStringProperty biDanhProperty() {
        return biDanh;
    }

    public void setBiDanh(String biDanh) {
        this.biDanh.set(biDanh);
    }

    public String getNgaySinh() {
        return ngaySinh.get();
    }

    public SimpleStringProperty ngaySinhProperty() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh.set(ngaySinh);
    }

    public String getNguyenQuan() {
        return nguyenQuan.get();
    }

    public SimpleStringProperty nguyenQuanProperty() {
        return nguyenQuan;
    }

    public void setNguyenQuan(String nguyenQuan) {
        this.nguyenQuan.set(nguyenQuan);
    }

    public String getDanToc() {
        return danToc.get();
    }

    public SimpleStringProperty danTocProperty() {
        return danToc;
    }

    public void setDanToc(String danToc) {
        this.danToc.set(danToc);
    }

    public String getNgheNghiep() {
        return ngheNghiep.get();
    }

    public SimpleStringProperty ngheNghiepProperty() {
        return ngheNghiep;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep.set(ngheNghiep);
    }

    public String getNoiLamViec() {
        return noiLamViec.get();
    }

    public SimpleStringProperty noiLamViecProperty() {
        return noiLamViec;
    }

    public void setNoiLamViec(String noiLamViec) {
        this.noiLamViec.set(noiLamViec);
    }

    public String getSoCCCD() {
        return soCCCD.get();
    }

    public SimpleStringProperty soCCCDProperty() {
        return soCCCD;
    }

    public void setSoCCCD(String soCCCD) {
        this.soCCCD.set(soCCCD);
    }

    public String getNgayCap() {
        return ngayCap.get();
    }

    public SimpleStringProperty ngayCapProperty() {
        return ngayCap;
    }

    public void setNgayCap(String ngayCap) {
        this.ngayCap.set(ngayCap);
    }

    public String getNoiCap() {
        return noiCap.get();
    }

    public SimpleStringProperty noiCapProperty() {
        return noiCap;
    }

    public void setNoiCap(String noiCap) {
        this.noiCap.set(noiCap);
    }

    public Date getThoiGianDKThuongTru() {
        return thoiGianDKThuongTru;
    }

    public void setThoiGianDKThuongTru(Date thoiGianDKThuongTru) {
        this.thoiGianDKThuongTru = thoiGianDKThuongTru;
    }

    public String getDiaChiThuongTru() {
        return diaChiThuongTru.get();
    }

    public SimpleStringProperty diaChiThuongTruProperty() {
        return diaChiThuongTru;
    }

    public void setDiaChiThuongTru(String diaChiThuongTru) {
        this.diaChiThuongTru.set(diaChiThuongTru);
    }

    public String getQuanHeChuHo() {
        return quanHeChuHo.get();
    }

    public SimpleStringProperty quanHeChuHoProperty() {
        return quanHeChuHo;
    }

    public void setQuanHeChuHo(String quanHeChuHo) {
        this.quanHeChuHo.set(quanHeChuHo);
    }
}
