package BUS;

import DAL.TacGiaDAL;
import DTO.TacGiaDTO;
import java.util.List;

public class TacGiaBUS {
    
    private TacGiaDAL tacGiaDAL;
    
    // Constructor
    public TacGiaBUS() {
        tacGiaDAL = new TacGiaDAL();
    }
    
    // Thêm tác giả mới
    public boolean addTacGia(TacGiaDTO tacGiaDTO) {
        return tacGiaDAL.addTacGia(tacGiaDTO);
    }
    
    // Cập nhật thông tin tác giả
    public boolean updateTacGia(TacGiaDTO tacGiaDTO) {
        return tacGiaDAL.updateTacGia(tacGiaDTO);
    }
    
    // Xóa tác giả
    public boolean deleteTacGia(String id) {
        return tacGiaDAL.deleteTacGia(id);
    }
    
    // Lấy tất cả tác giả
    public List<TacGiaDTO> getAllTacGia() {
        return tacGiaDAL.getAllTacGia();
    }
    
    // Tìm kiếm tác giả theo tên
    public List<TacGiaDTO> searchTacGiaByName(String name) {
        return tacGiaDAL.searchTacGiaByName(name);
    }
    
    // Lấy tác giả theo mã
    public TacGiaDTO getTacGiaById(String id) {
        return tacGiaDAL.getTacGiaById(id);
    }
}
