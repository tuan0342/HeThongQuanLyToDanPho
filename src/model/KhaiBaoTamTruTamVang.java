package model;

import java.io.PrintWriter;
import java.util.Date;

public class KhaiBaoTamTruTamVang {
    private int IdKhaiBao;
    private String Loai;
    private String HoTen;
    private Date NgaySinh;
    private String GioiTinh;
    private String QuocTich;
    private String SoCCCD;
    private Date NgayDangKy;
    private Date NgayKetThuc;
    private String DiaChiThuongTru;
    private String DiaChiTamTruTamVang;
    private String LyDo;

    public KhaiBaoTamTruTamVang() {

    }

    public KhaiBaoTamTruTamVang(int idKhaiBao, String loai, String hoTen, Date ngaySinh, String gioiTinh, String quocTich, String soCCCD, Date ngayDangKy, Date ngayKetThuc, String diaChiThuongTru, String diaChiTamTruTamVang, String lyDo) {
        IdKhaiBao = idKhaiBao;
        Loai = loai;
        HoTen = hoTen;
        NgaySinh = ngaySinh;
        GioiTinh = gioiTinh;
        QuocTich = quocTich;
        SoCCCD = soCCCD;
        NgayDangKy = ngayDangKy;
        NgayKetThuc = ngayKetThuc;
        DiaChiThuongTru = diaChiThuongTru;
        DiaChiTamTruTamVang = diaChiTamTruTamVang;
        LyDo = lyDo;
    }

    public int getIdKhaiBao() {
        return IdKhaiBao;
    }

    public void setIdKhaiBao(int idKhaiBao) {
        IdKhaiBao = idKhaiBao;
    }

    public String getLoai() {
        return Loai;
    }

    public void setLoai(String loai) {
        Loai = loai;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getQuocTich() {
        return QuocTich;
    }

    public void setQuocTich(String quocTich) {
        QuocTich = quocTich;
    }

    public String getSoCCCD() {
        return SoCCCD;
    }

    public void setSoCCCD(String soCCCD) {
        SoCCCD = soCCCD;
    }

    public Date getNgayDangKy() {
        return NgayDangKy;
    }

    public void setNgayDangKy(Date ngayDangKy) {
        NgayDangKy = ngayDangKy;
    }

    public Date getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        NgayKetThuc = ngayKetThuc;
    }

    public String getDiaChiThuongTru() {
        return DiaChiThuongTru;
    }

    public void setDiaChiThuongTru(String diaChiThuongTru) {
        DiaChiThuongTru = diaChiThuongTru;
    }

    public String getDiaChiTamTruTamVang() {
        return DiaChiTamTruTamVang;
    }

    public void setDiaChiTamTruTamVang(String diaChiTamTruTamVang) {
        DiaChiTamTruTamVang = diaChiTamTruTamVang;
    }

    public String getLyDo() {
        return LyDo;
    }

    public void setLyDo(String lyDo) {
        LyDo = lyDo;
    }
}
