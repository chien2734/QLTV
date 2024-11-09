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
public class CT_PhieuTraDTO {
    //    create table CT_PhieuTra (
//	MaPhieuTra	varchar(20),
//	MaSach		varchar(10),
//	TenSach		nvarchar(20),
//	NgayTra		datetime,
//	TinhTrang	nvarchar(50),			-- "nguyên vẹn", "hư hỏng nhẹ", "hư hỏng nặng", "mất" 
//	TienPhat	int,					-- Tính tự động: "Hư hỏng nhẹ": GiaSach*10%, "Hư hỏng nặng": GiaSach*80%, "Mất": GiaSach*120% 
//	primary key (MaPhieuTra, MaSach),
//	foreign key (MaPhieuTra) references PhieuTra(MaPhieuTra),
//	foreign key (MaSach) references Sach(MaSach),
//    )
    
    private String maPT;
    private String masach;
    private String tenSach;
    private Date ngayTra;
    private String tinhTrang;
    private int tienPhat;

    public CT_PhieuTraDTO() {
    }

    public CT_PhieuTraDTO(String maPT, String masach, String tenSach, Date ngayTra, String tinhTrang, int tienPhat) {
        this.maPT = maPT;
        this.masach = masach;
        this.tenSach = tenSach;
        this.ngayTra = ngayTra;
        this.tinhTrang = tinhTrang;
        this.tienPhat = tienPhat;
    }

    public CT_PhieuTraDTO(String maPhieu, String maSach) {
        this.maPT = maPT;
        this.masach = masach;
    }

    public CT_PhieuTraDTO(String mapt) {
        this.maPT = maPT;
    }

    public String getMaPT() {
        return maPT;
    }

    public void setMaPT(String maPT) {
        this.maPT = maPT;
    }

    public String getMasach() {
        return masach;
    }

    public void setMasach(String masach) {
        this.masach = masach;
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

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public int getTienPhat() {
        return tienPhat;
    }

    public void setTienPhat(int tienPhat) {
        this.tienPhat = tienPhat;
    }
    
}
