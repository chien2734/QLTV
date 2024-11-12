
package DTO;

public class CT_PhieuMuonDTO {
    private String maPhieuMuon;
    private String maSach;
    private String tenSach;
    private int soLuong;
    private String trangThai;

    public CT_PhieuMuonDTO() {
    }

    public CT_PhieuMuonDTO(String maPhieuMuon, String maSach, String tenSach, int soLuong, String trangThai) {
        this.maPhieuMuon = maPhieuMuon;
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    public String getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public void setMaPhieuMuon(String maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
