
package DTO;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PhieuMuonDTO {
    private String id;
    private String maThe;
    private Date ngayMuon;
    private Date hanTra;
    private Double tienCoc;
    private String trangThai;

    public PhieuMuonDTO() {
    }

    public PhieuMuonDTO(String id, String maThe, Date ngayMuon, String trangThai) {
        this.id = id;
        this.maThe = maThe;
        this.ngayMuon = ngayMuon;
        this.trangThai = trangThai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaThe() {
        return maThe;
    }

    public void setMaThe(String maThe) {
        this.maThe = maThe;
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

    public Double getTienCoc() {
        return tienCoc;
    }

    public void setTienCoc(Double tienCoc) {
        this.tienCoc = tienCoc;
    }
    
    public void setHanTra(Date ngayMuon) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ngayMuon);
        calendar.add(Calendar.DAY_OF_MONTH, 21); // Cộng thêm 21 ngày vào ngày mượn
        String hantra = dateFormat.format(calendar.getTimeInMillis());
        this.hanTra = Date.valueOf(hantra);
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
    
    
}
