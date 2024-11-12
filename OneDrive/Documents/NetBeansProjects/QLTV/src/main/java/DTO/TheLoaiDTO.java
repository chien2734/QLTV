
package DTO;

public class TheLoaiDTO {
    private String id;
    private String ten;
    private int soLuong;

    public TheLoaiDTO() {
    }

    public TheLoaiDTO(String id, String ten, int soLuong) {
        this.id = id;
        this.ten = ten;
        this.soLuong = soLuong;
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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
}
