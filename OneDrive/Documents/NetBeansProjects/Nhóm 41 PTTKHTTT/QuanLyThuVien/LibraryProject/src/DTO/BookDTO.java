/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import BLL.BookCopyBLL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class BookDTO {

    private String maDS;
    private String tenSach;
    private String tacGia;
    private String tenLoai;
    private String nxb;
    private int NamXB;
    private int soLuong;
    private int trangThai;
    
    private BookCopyBLL  bookBLL = new BookCopyBLL();
    
    public BookDTO(String maDS) {
        this.maDS = maDS;
    }

    public BookDTO(String maDS, String tenSach, String tacGia, String tenLoai, String nxb, int NamXB, int soLuong, int trangThai) {
        this.maDS = maDS;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.tenLoai = tenLoai;
        this.nxb = nxb;
        this.NamXB = NamXB;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    public BookDTO(String maDS, String tenSach, String tacGia, String nxb, String tenLoai) {
        this.maDS = maDS;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.nxb = nxb;
        this.tenLoai = tenLoai;
    }
    
    public BookDTO(String maDS, String tenSach, String tacGia, String tenLoai, String nxb, String namXB) {
        this.maDS = maDS;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.nxb = nxb;
        this.tenLoai = tenLoai;
        this.NamXB = Integer.valueOf(namXB);
    }

    public BookDTO() {
    }

    public String getMaDS() {
        return maDS;
    }

    public void setMaDS(String maDS) {
        this.maDS = maDS;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getnxb() {
        return nxb;
    }

    public void setnxb(String nxb) {
        this.nxb = nxb;
    }

    public int getNamXB() {
        return NamXB;
    }

    public void setNamXB(int NamXB) {
        this.NamXB = NamXB;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "DauSach{" + "maDS=" + maDS + ", tenSach=" + tenSach + ", tenLoai=" + tenLoai + ", tacGia=" + tacGia + ", nxb=" + nxb + ", NamXB=" + NamXB + ", soLuong=" + soLuong + ", trangThai=" + trangThai + '}';
    }

}
