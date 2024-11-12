
package DTO;

import java.sql.Date;

public class CT_PhieuTraDTO {
    private String maPhieuTra;
    private String maSach;
    private String tenSach;
    private Date ngayTra;
    private String trangThai;

    public CT_PhieuTraDTO() {
    }

    public CT_PhieuTraDTO(String maPhieuTra, String maSach, String tenSach, Date ngayTra, String trangThai) {
        this.maPhieuTra = maPhieuTra;
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.ngayTra = ngayTra;
        this.trangThai = trangThai;
    }

    public String getMaPhieuTra() {
        return maPhieuTra;
    }

    public void setMaPhieuTra(String maPhieuTra) {
        this.maPhieuTra = maPhieuTra;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
