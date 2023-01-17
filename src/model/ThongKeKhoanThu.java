package model;

public class ThongKeKhoanThu {
    private String tenKT;
    private String tienDaThu;
    private String soHoDaThu;
    private String tienChuaThu;
    private String soHoChuaThu;
    private String tongHo;
    private String soTien1Ho;

    public ThongKeKhoanThu() {
    }

    public ThongKeKhoanThu(String tenKT, String tienDaThu, String soHoDaThu, String tienChuaThu, String soHoChuaThu, String tongHo, String soTien1Ho) {
        this.tenKT = tenKT;
        this.tienDaThu = tienDaThu;
        this.soHoDaThu = soHoDaThu;
        this.tienChuaThu = tienChuaThu;
        this.soHoChuaThu = soHoChuaThu;
        this.tongHo = tongHo;
        this.soTien1Ho = soTien1Ho;
    }

    public String getTenKT() {
        return tenKT;
    }

    public void setTenKT(String tenKT) {
        this.tenKT = tenKT;
    }

    public String getTienDaThu() {
        return tienDaThu;
    }

    public void setTienDaThu(String tienDaThu) {
        this.tienDaThu = tienDaThu;
    }

    public String getSoHoDaThu() {
        return soHoDaThu;
    }

    public void setSoHoDaThu(String soHoDaThu) {
        this.soHoDaThu = soHoDaThu;
    }

    public String getTienChuaThu() {
        return tienChuaThu;
    }

    public void setTienChuaThu(String tienChuaThu) {
        this.tienChuaThu = tienChuaThu;
    }

    public String getSoHoChuaThu() {
        return soHoChuaThu;
    }

    public void setSoHoChuaThu(String soHoChuaThu) {
        this.soHoChuaThu = soHoChuaThu;
    }

    public String getTongHo() {
        return tongHo;
    }

    public void setTongHo(String tongHo) {
        this.tongHo = tongHo;
    }

    public String getSoTien1Ho() {
        return soTien1Ho;
    }

    public void setSoTien1Ho(String soTien1Ho) {
        this.soTien1Ho = soTien1Ho;
    }
}
