/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;

/**
 *
 * @author PC
 */
public class PhieuTraDTO {
//    create table PhieuTra(
//	MaPhieuTra	varchar(20)	primary key,
//	MaPhieuMuon	varchar(10) foreign key references PhieuMuon(MaPhieu),
//	MaTheMuon	varchar(10) foreign key references THEMUON(MaTheMuon),
//	NgayMuon	datetime,
//	TongTienPhat	int,
//	TrangThai	int
//)
    
    private String maPT;
    private String maPM;
    private String mathe;
    private Date ngayMuon;
    private int tienPhat;
    private int trangThai;  // 0: xoá, 1: Đã trả hết

    public PhieuTraDTO() {
    }

    public PhieuTraDTO(String maPT, String maPM, String mathe, Date ngayMuon, int tienPhat, int trangThai) {
        this.maPT = maPT;
        this.maPM = maPM;
        this.mathe = mathe;
        this.ngayMuon = ngayMuon;
        this.tienPhat = tienPhat;
        this.trangThai = trangThai;
    }

    public String getMaPT() {
        return maPT;
    }

    public void setMaPT(String maPT) {
        this.maPT = maPT;
    }

    public String getMaPM() {
        return maPM;
    }

    public void setMaPM(String maPM) {
        this.maPM = maPM;
    }

    public String getMathe() {
        return mathe;
    }

    public void setMathe(String mathe) {
        this.mathe = mathe;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public int getTienPhat() {
        return tienPhat;
    }

    public void setTienPhat(int tienPhat) {
        this.tienPhat = tienPhat;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
