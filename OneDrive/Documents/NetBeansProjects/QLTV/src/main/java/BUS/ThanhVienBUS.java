package BUS;

import DAL.ThanhVienDAL;
import DTO.ThanhVienDTO;
import java.util.List;

public class ThanhVienBUS {
    
    private ThanhVienDAL thanhVienDAL;
    
    // Constructor
    public ThanhVienBUS() {
        thanhVienDAL = new ThanhVienDAL();
    }
    
    // Thêm thành viên mới
    public boolean addThanhVien(ThanhVienDTO thanhVienDTO) {
        return thanhVienDAL.addThanhVien(thanhVienDTO);
    }
    
    // Cập nhật thành viên
    public boolean updateThanhVien(ThanhVienDTO thanhVienDTO) {
        return thanhVienDAL.updateThanhVien(thanhVienDTO);
    }
    
    // Xóa thành viên
    public boolean deleteThanhVien(String id) {
        return thanhVienDAL.deleteThanhVien(id);
    }
    
    // Lấy tất cả thành viên
    public List<ThanhVienDTO> getAllThanhVien() {
        return thanhVienDAL.getAllThanhVien();
    }
    
    // Tìm kiếm thành viên theo tên
    public List<ThanhVienDTO> searchThanhVienByName(String name) {
        return thanhVienDAL.searchThanhVienByName(name);
    }
    
    // Lấy thành viên theo mã
    public ThanhVienDTO getThanhVienById(String id) {
        return thanhVienDAL.getThanhVienById(id);
    }
}
