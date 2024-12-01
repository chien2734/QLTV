
package DTO;

import java.sql.Date;


public class PhieuMuonDTO {
    private String id;
    private String maThe;
    private Date ngayMuon;
    private Date hanTra;
    private Double tienCoc;
    private String trangThai;
    private int soLanGiaHan;

    public PhieuMuonDTO() {
    }

    public PhieuMuonDTO(String id, String maThe, Date ngayMuon, Date hanTra, Double tienCoc, String trangThai, int soLanGiaHan) {
        this.id = id;
        this.maThe = maThe;
        this.ngayMuon = ngayMuon;
        this.hanTra = hanTra;
        this.tienCoc = tienCoc;
        this.trangThai = trangThai;
        this.soLanGiaHan = soLanGiaHan;
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getHanTra() {
        return hanTra;
    }

    public Double getTienCoc() {
        return tienCoc;
    }

    public void setTienCoc(Double tienCoc) {
        this.tienCoc = tienCoc;
    }
    
    public void setHanTra(Date hanTra) {
        this.hanTra = hanTra;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getSoLanGiaHan() {
        return soLanGiaHan;
    }

    public void setSoLanGiaHan(int soLanGiaHan) {
        this.soLanGiaHan = soLanGiaHan;
    }
    
    
    
}
