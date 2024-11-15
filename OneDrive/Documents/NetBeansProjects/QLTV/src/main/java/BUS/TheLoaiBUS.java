package BUS;

import DAL.TheLoaiDAL;
import DTO.TheLoaiDTO;
import java.util.List;

public class TheLoaiBUS {
    
    private TheLoaiDAL theLoaiDAL;
    
    // Constructor
    public TheLoaiBUS() {
        theLoaiDAL = new TheLoaiDAL();
    }
    
    // Thêm thể loại mới
    public boolean addTheLoai(TheLoaiDTO theLoaiDTO) {
        return theLoaiDAL.addTheLoai(theLoaiDTO);
    }
    
    // Cập nhật thể loại
    public boolean updateTheLoai(TheLoaiDTO theLoaiDTO) {
        return theLoaiDAL.updateTheLoai(theLoaiDTO);
    }
    
    // Xóa thể loại
    public boolean deleteTheLoai(String id) {
        return theLoaiDAL.deleteTheLoai(id);
    }
    
    // Lấy tất cả thể loại
    public List<TheLoaiDTO> getAllTheLoai() {
        return theLoaiDAL.getAllTheLoai();
    }
    
    // Tìm kiếm thể loại theo tên
    public List<TheLoaiDTO> searchTheLoaiByName(String name) {
        return theLoaiDAL.searchTheLoaiByName(name);
    }
    
    // Lấy thể loại theo mã
    public TheLoaiDTO getTheLoaiById(String id) {
        return theLoaiDAL.getTheLoaiById(id);
    }
}
