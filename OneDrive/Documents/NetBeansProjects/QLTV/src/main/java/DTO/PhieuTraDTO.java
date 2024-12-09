
package DTO;

import java.sql.Date;

public class PhieuTraDTO {
    private String id;
    private String maPhieuMuon;
    private String maThe;
    private Date ngayMuon;
    private Date ngayTra;
    private double phiDenBu;
    private double phiTreHan;
    private String trangThai;

    public PhieuTraDTO() {
    }

    public PhieuTraDTO(String id, String maPhieuMuon, String maThe, Date ngayMuon, Date ngayTra, double phiDenBu, double phiTreHan, String trangThai) {
        this.id = id;
        this.maPhieuMuon = maPhieuMuon;
        this.maThe = maThe;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.phiDenBu = phiDenBu;
        this.phiTreHan = phiTreHan;
        this.trangThai = trangThai;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public void setMaPhieuMuon(String maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public String getMaThe() {
        return maThe;
    }

    public void setMaThe(String maThe) {
        this.maThe = maThe;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public double getPhiDenBu() {
        return phiDenBu;
    }

    public void setPhiDenBu(double phiDenBu) {
        this.phiDenBu = phiDenBu;
    }

    public double getPhiTreHan() {
        return phiTreHan;
    }

    public void setPhiTreHan(double phiTreHan) {
        this.phiTreHan = phiTreHan;
    }
    
    
}
