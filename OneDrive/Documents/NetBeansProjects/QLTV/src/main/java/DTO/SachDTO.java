package DTO;


public class SachDTO {
    private String id;
    private String tenSach;
    private String theloai;
    private String tacGia;
    private String NXB;
    private int namXB;
    private int soLuong;
    private String trangthai;
    public SachDTO() {
    }

    public SachDTO(String id, String tenSach, String theloai, String tacGia, String NXB, int namXB, int soLuong) {
        this.id = id;
        this.tenSach = tenSach;
        this.theloai = theloai;
        this.tacGia = tacGia;
        this.NXB = NXB;
        this.namXB = namXB;
        this.soLuong = soLuong;
    }

    public int getNamXB() {
        return namXB;
    }

    public void setNamXB(int namXB) {
        this.namXB = namXB;
    }

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNXB() {
        return NXB;
    }

    public void setNXB(String NXB) {
        this.NXB = NXB;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int soLuong) {
        String trangThai;
        if(soLuong > 0){
            trangThai = "Còn sách";
        } else{
            trangThai = "Không còn sách";
        }
        this.trangthai = trangThai;
    }

}
