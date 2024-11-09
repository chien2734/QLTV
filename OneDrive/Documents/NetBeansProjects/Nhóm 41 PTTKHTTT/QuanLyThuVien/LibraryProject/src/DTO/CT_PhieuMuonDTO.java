/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import BLL.BookCopyBLL;
import DAL.*;

/**
 *
 * @author ADMIN
 */
public class CT_PhieuMuonDTO {
    /*
    create table CT_PhieuMuon(
	MaPhieu	varchar(10),
	MaSach	varchar(10),
	TenSach	varchar(255),
        GiaMuon         int,
	TrangThai	int,
	primary key (MaPhieu, MaSach),
	foreign key (MaPhieu) references PhieuMuon(MaPhieu),
	foreign key (MaSach) references Sach(MaSach)
    )
    */
    
    private String maPhieu;
    private String maSach;
    private String tenSach;
    private int giaMuon;
    private String trangThai;
    private BookCopyBLL bookBLL = new BookCopyBLL();

    public CT_PhieuMuonDTO(String maPhieu, String maSach, String tenSach, int giaMuon, String trangThai) {
        this.maPhieu = maPhieu;
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giaMuon = giaMuon;
        this.trangThai = trangThai;
    }

    public CT_PhieuMuonDTO(String maphieu) {
        this.maPhieu = maphieu;
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
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

    public int getGiaMuon() {
        return giaMuon;
    }

    public void setGiaMuon(int giaMuon) {
        this.giaMuon = giaMuon;
    }
    
    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai() {
        BookCopyDTO book = bookBLL.getBookCopyById(new BookCopyDTO(maSach));
        this.trangThai = book.getTrangThai();
    }

    @Override
    public String toString() {
        return "CT_PhieuMuonDAO{" + "maPhieu=" + maPhieu + ", maSach=" + maSach + ", tenSach=" + tenSach + ", giaMuon" + giaMuon + ", trangThai=" + trangThai + '}';
    }
    
    
}
