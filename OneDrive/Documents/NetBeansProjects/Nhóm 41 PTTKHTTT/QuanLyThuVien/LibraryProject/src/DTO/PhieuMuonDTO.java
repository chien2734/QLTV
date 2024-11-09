/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class PhieuMuonDTO {
    /*
    create table PhieuMuon(
            MaPhieu		varchar(10) primary key,
            MaTheMuon	varchar(10) foreign key references DocGia(TENDN),
            TenDG		nvarchar(50),
            NgayDK		datetime,
            HanNhan		datetime,
            NgayMuon	datetime,
            HanTra		datetime,
            TienCoc		int,		-- Tự động tính tổng GiaMuon
            TrangThai	int			-- "Quá hạn mượn" (có thể xoá), "Có thể mượn" (cho phép xác nhận mượn), "Đã nhận sách"
    )
    */
    
    private String maPhieu;
    private String maTheMuon;
    private String tenDG;
    private Date ngayDK;
    private Date hanNhan;
    private Date ngayMuon;
    private Date hanTra;
    private int tienCoc;
    private String trangThai;

    public PhieuMuonDTO(String maPhieu, String maTheMuon, String tenDG, Date ngayMuon, Date hanTra, int tienCoc, String trangThai) {
        this.maPhieu = maPhieu;
        this.maTheMuon = maTheMuon;
        this.tenDG = tenDG;
        this.ngayMuon = ngayMuon;
        this.hanTra = hanTra;
        this.tienCoc = tienCoc;
        this.trangThai = trangThai;
    }

    public PhieuMuonDTO(String maPhieu, String maTheMuon, String tenDG, Date ngayDK, Date hanNhan, Date ngayMuon, Date hanTra, int tienCoc, String trangThai) {
        this.maPhieu = maPhieu;
        this.maTheMuon = maTheMuon;
        this.tenDG = tenDG;
        this.ngayDK = ngayDK;
        this.hanNhan = hanNhan;
        this.ngayMuon = ngayMuon;
        this.hanTra = hanTra;
        this.tienCoc = tienCoc;
        this.trangThai = trangThai;
    }


    public PhieuMuonDTO(String maphieu) {
        this.maPhieu = maphieu;
    }

    public PhieuMuonDTO() {
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public String getMaTheMuon() {
        return maTheMuon;
    }

    public void setMaTheMuon(String maTheMuon) {
        this.maTheMuon = maTheMuon;
    }

    public String getTenDG() {
        return tenDG;
    }

    public void setTenDG(String tenDG) {
        this.tenDG = tenDG;
    }

    public Date getNgayDK() {
        return ngayDK;
    }

    public void setNgayDK(Date ngayDK) {
        this.ngayDK = ngayDK;
    }

    public Date getHanNhan() {
        return hanNhan;
    }

    public void setHanNhan(Date ngayDK) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ngayDK);
        calendar.add(Calendar.DAY_OF_MONTH, 3);        // Hạn trả là 10 ngày kể từ ngày mượn
        String hanNhan = dateFormat.format(calendar.getTimeInMillis());
        this.hanNhan = Date.valueOf(hanNhan);
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

    public void setHanTra(Date hanTra) {
        this.hanTra = hanTra;
    }


    public int getTienCoc() {
        return tienCoc;
    }

    public void setTienCoc(int tienCoc) {
        this.tienCoc = tienCoc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        String ngaymuon = dateFormat.format(calendar.getTimeInMillis());
        Date ngayMuon = java.sql.Date.valueOf(ngaymuon);
        if (this.hanNhan != null && this.ngayMuon != null && this.ngayMuon.after(this.hanTra)) {
            this.trangThai = "Quá hạn mượn";
        } else if (this.hanTra != null && ngayMuon.after(hanTra) && !this.trangThai.equals("Đã trả hết")) {
            this.trangThai = "Quá hạn trả";
        } else this.trangThai = trangThai;
        
    }

    @Override
    public String toString() {
        return "PhieuMuonDTO{" + "maPhieu=" + maPhieu + ", maTheMuon=" + maTheMuon + ", tenDG=" + tenDG + ", ngayDK=" + ngayDK + ", hanNhan=" + hanNhan + ", ngayMuon=" + ngayMuon + ", hanTra=" + hanTra + ", tienCoc=" + tienCoc + ", trangThai=" + trangThai + '}';
    }
    
    
    
}

