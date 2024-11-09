package DTO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.*;
import java.util.Objects;
/**
 *
 * @author HUY LONG
 */
public class CardDTO {
    private String maTheMuon;
    private String tenDN;
    private Date ngayDK;
    private int trangThai;

    public CardDTO() {
    }
    
    public CardDTO(String tenDN) {
        this.tenDN = tenDN;
    }
    
    public CardDTO(String maTheMuon, String tenDN, Date ngayDK, int trangThai) {
        this.maTheMuon = maTheMuon;
        this.tenDN = tenDN;
        this.ngayDK = ngayDK;
        this.trangThai = trangThai;
    }

    public String getMaTheMuon() {
        return maTheMuon;
    }

    public void setMaTheMuon(String maTheMuon) {
        this.maTheMuon = maTheMuon;
    }

    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String TenDN) {
        this.tenDN = TenDN;
    }

    public Date getNgayDK() {
        return ngayDK;
    }

    public void setNgayDK(Date ngayDK) {
        this.ngayDK = ngayDK;
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
        hash = 89 * hash + Objects.hashCode(this.maTheMuon);
        hash = 89 * hash + Objects.hashCode(this.tenDN);
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
        final CardDTO other = (CardDTO) obj;
        if (!Objects.equals(this.maTheMuon, other.maTheMuon)) {
            return false;
        }
        return Objects.equals(this.tenDN, other.tenDN);
    }
    
    
}