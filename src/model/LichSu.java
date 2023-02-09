package model;

import java.util.Date;

public class LichSu {
    private String idLichSu;
    private String noiDungThayDoi;
    private String idHoKhau;
    private String ngayThang;

    public String getIdLichSu() {
        return idLichSu;
    }

    public void setIdLichSu(String idLichSu) {
        this.idLichSu = idLichSu;
    }

    public String getNoiDungThayDoi() {
        return noiDungThayDoi;
    }

    public void setNoiDungThayDoi(String noiDungThayDoi) {
        this.noiDungThayDoi = noiDungThayDoi;
    }

    public String getIdHoKhau() {
        return idHoKhau;
    }

    public void setIdHoKhau(String idHoKhau) {
        this.idHoKhau = idHoKhau;
    }

    public String getNgayThang() {
        return ngayThang;
    }

    public void setNgayThang(String ngayThang) {
        this.ngayThang = ngayThang;
    }

    public LichSu(String idLichSu, String noiDungThayDoi, String idHoKhau, String ngayThang) {
        this.idLichSu = idLichSu;
        this.noiDungThayDoi = noiDungThayDoi;
        this.idHoKhau = idHoKhau;
        this.ngayThang = ngayThang;
    }

    public boolean timTheoIdHoKhau (String idHoKhau) {
        if (getIdHoKhau().compareTo(idHoKhau) == 0) return true;
        return false;
    }

    public boolean timTheoIdLichSu (String idLichSu) {
        if (getIdLichSu().compareTo(idLichSu) == 0) return true;
        return false;
    }
}
