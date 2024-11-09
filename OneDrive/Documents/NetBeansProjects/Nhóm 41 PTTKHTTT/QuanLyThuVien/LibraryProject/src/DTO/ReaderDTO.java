/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Objects;

/**
 *
 * @author HUY LONG
 */
public class ReaderDTO {
    private String tenDN;
    private String tenDG;
    private String matKhau;
    private String SDT;
    private String email;
    private String diaChi;
    private int trangThai;

    public ReaderDTO() {
    }

    public ReaderDTO(String tenDN) {
        this.tenDN = tenDN;
    }

    public ReaderDTO(String tenDN, String tenDG, String matKhau, String SDT, String email, String diaChi, int trangThai) {
        this.tenDN = tenDN;
        this.tenDG = tenDG;
        this.matKhau = matKhau;
        this.SDT = SDT;
        this.email = email;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
    }

    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getTenDG() {
        return tenDG;
    }

    public void setTenDG(String tenDG) {
        this.tenDG = tenDG;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.tenDN);
        hash = 17 * hash + Objects.hashCode(this.matKhau);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReaderDTO other = (ReaderDTO) obj;
        if (!Objects.equals(this.tenDN, other.tenDN)) {
            return false;
        }
        return Objects.equals(this.matKhau, other.matKhau);
    }
    
    
}
