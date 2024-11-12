
package DTO;

public class TacGiaDTO {
    private String id;
    private String ten;
    private int soLuongSach;

    public TacGiaDTO() {
    }

    public TacGiaDTO(String id, String ten, int soLuongSach) {
        this.id = id;
        this.ten = ten;
        this.soLuongSach = soLuongSach;
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

    public int getSoLuongSach() {
        return soLuongSach;
    }

    public void setSoLuongSach(int soLuongSach) {
        this.soLuongSach = soLuongSach;
    }
    
}
