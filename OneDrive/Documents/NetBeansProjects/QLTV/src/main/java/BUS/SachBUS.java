package BUS;

import DAL.SachDAL;
import DTO.SachDTO;
import java.util.List;

public class SachBUS {

    private SachDAL sachDAL;

    // Constructor
    public SachBUS() {
        sachDAL = new SachDAL();
    }

    // Thêm sách mới
    public boolean addSach(SachDTO sachDTO) {
        return sachDAL.addSach(sachDTO);
    }

    // Cập nhật thông tin sách
    public boolean updateSach(SachDTO sachDTO) {
        return sachDAL.updateSach(sachDTO);
    }

    // Xóa sách
    public boolean deleteSach(String id) {
        return sachDAL.deleteSach(id);
    }

    // Lấy tất cả sách
    public List<SachDTO> getAllSach() {
        return sachDAL.getAllSach();
    }

    // Tìm kiếm sách theo tên
    public List<SachDTO> searchSachByName(String name) {
        return sachDAL.searchSachByName(name);
    }

    // Lấy sách theo mã
    public SachDTO getSachById(String id) {
        return sachDAL.getSachById(id);
    }

    // Lấy sách theo thể loại
    public List<SachDTO> getSachByCategory(String categoryId) {
        return sachDAL.getSachByCategory(categoryId);
    }

    // Lấy sách theo tác giả
    public List<SachDTO> getSachByAuthor(String authorId) {
        return sachDAL.getSachByAuthor(authorId);
    }

    // Lấy sách theo nhà xuất bản
    public List<SachDTO> getSachByPublisher(String publisherId) {
        return sachDAL.getSachByPublisher(publisherId);
    }
}
