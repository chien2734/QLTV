
package DTO;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ThanhVienDTO {
    private String id;
    private String ten;
    private String CCCD;
    private String sdt;
    private Date ngayDK;
    private Date hanSD;
    private double phiDuyTri;
    private String trangThai;

    public ThanhVienDTO() {
    }

    public ThanhVienDTO(String id, String ten, String CCCD, String sdt, Date ngayDK, double phiDuyTri, String trangThai) {
        this.id = id;
        this.ten = ten;
        this.CCCD = CCCD;
        this.sdt = sdt;
        this.ngayDK = ngayDK;
        this.phiDuyTri = phiDuyTri;
        this.trangThai = trangThai;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Date getNgayDK() {
        return ngayDK;
    }

    public void setNgayDK(Date ngayDK) {
        this.ngayDK = ngayDK;
    }

    public Date getHanSD() {
        return hanSD;
    }

    public void setHanSD(Date ngayDK) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(ngayDK);
    calendar.add(Calendar.YEAR, 1);//han su dung la 1 nam
    String hansd = dateFormat.format(calendar.getTimeInMillis());
    this.hanSD = Date.valueOf(hansd);
    }

    public double getPhiDuyTri() {
        return phiDuyTri;
    }

    public void setPhiDuyTri(double phiDuyTri) {
        this.phiDuyTri = phiDuyTri;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
