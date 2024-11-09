/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author ADMIN
 */
public class NXBDTO {
    public String maNXB;
    public String tenNXB;
    public int trangThai;

    public NXBDTO(String tenNXB) {
        this.tenNXB = tenNXB;
    }
    
    

    public NXBDTO(String maNXB, String tenNXB, int trangThai) {
        this.maNXB = maNXB;
        this.tenNXB = tenNXB;
        this.trangThai = trangThai;
    }

    public String getMaNXB() {
        return maNXB;
    }

    public void setMaNXB(String maNXB) {
        this.maNXB = maNXB;
    }

    public String getTenNXB() {
        return tenNXB;
    }

    public void setTenNXB(String tenNXB) {
        this.tenNXB = tenNXB;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "NXBDTO{" + "maNXB=" + maNXB + ", tenNXB=" + tenNXB + ", trangThai=" + trangThai + '}';
    }
    
    
}
