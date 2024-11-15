
package DTO;

public class CT_PhieuTraDTO {
    private String maPhieuTra;
    private String maSach;
    private String tenSach;
    private int soLuong;
    private String trangThai;

    public CT_PhieuTraDTO() {
    }

    public CT_PhieuTraDTO(String maPhieuTra, String maSach, String tenSach, int soLuong, String trangThai) {
        this.maPhieuTra = maPhieuTra;
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
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

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
