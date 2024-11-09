/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author ADMIN
 */
public class CartUserDTO {
    /*
    create table GioDK (
	MaTheMuon	varchar(10),
	MaSach		varchar(10),
	TenSach		nvarchar(255),
        GiaMuon         int
        TinhTrang       int,
	primary key (MaTheMuon, MaSach),
	foreign key (MaTheMuon) references TheMuon(MaTheMuon),
	foreign key (MaSach) references Sach(MaSach)
    )
    */
    
    private String maTheMuon;
    private String maSach;
    private String tenSach;
    private int giaMuon;

    public CartUserDTO(String maSach) {
        this.maSach = maSach;
    }

    public CartUserDTO(String maTheMuon, String maSach, String tenSach) {
        this.maTheMuon = maTheMuon;
        this.maSach = maSach;
        this.tenSach = tenSach;
    }

    public CartUserDTO(String maTheMuon, String maSach, String tenSach, int giaMuon) {
        this.maTheMuon = maTheMuon;
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giaMuon = giaMuon;
    }

    public int getGiaMuon() {
        return giaMuon;
    }

    public void setGiaMuon(int giaMuon) {
        this.giaMuon = giaMuon;
    }

    public String getMaTheMuon() {
        return maTheMuon;
    }

    public void setMaTheMuon(String maTheMuon) {
        this.maTheMuon = maTheMuon;
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

    @Override
    public String toString() {
        return "CardUserDTO{" + "maTheMuon=" + maTheMuon + ", maSach=" + maSach + ", tenSach=" + tenSach + '}';
    }
    
    
}
