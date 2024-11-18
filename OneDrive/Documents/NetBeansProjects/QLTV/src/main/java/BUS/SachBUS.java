package BUS;

import DAL.SachDAL;
import DTO.SachDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class SachBUS {

    private SachDAL sachDAL;
    private ArrayList<SachDTO> ds ;
    // Constructor
    public SachBUS() throws SQLException {
        ds = sachDAL.getAllSach();
    }

    // Thêm sách mới
    public String addSach(SachDTO sachDTO) {
        if(sachDAL.hasID(sachDTO.getId())){
            return "Mã này đã tồn tại, vui lòng nhập mã khác!";
        }
        boolean isAdded = sachDAL.addSach(sachDTO);
        if(isAdded){
            ds.add(sachDTO);
            return "Thêm sách " + sachDTO.getTenSach() +" thành công!";
        }
        return "Thêm sách không thành công!";
    }

    // Cập nhật thông tin sách
    public String updateSach(SachDTO sachDTO) {
        boolean isUpdated = sachDAL.updateSach(sachDTO);
        if (isUpdated) {
            // Cập nhật danh sách
            for (int i = 0; i < ds.size(); i++) {
                if (ds.get(i).getId().equals(sachDTO.getId())) {
                    ds.set(i, sachDTO);
                    break;
                }
            }
            return "Cập nhật thông tin sách "+sachDTO.getTenSach()+" thành công!";
        }
        return "Cập nhật không thành công!";
        
    }

    // Xóa sách
    public String deleteSach(String id) {
        boolean isDeleted = sachDAL.deleteSach(id);
        if(isDeleted){
            ds.removeIf(sach -> sach.getId().equals(id));
            return "Xóa mã sách "+id+" thành công!";
        }
        return "Xóa không thành công!";
    }

    // Lấy tất cả sách
    public ArrayList<SachDTO> getAllSach() {
        return ds;
    }

    //Lay theo ten
    public ArrayList<SachDTO> searchSachByName(String name) {
        return (ArrayList<SachDTO>) ds.stream()
                .filter(sach -> sach.getTenSach().contains(name))
                .toList();
    }

    // Lấy sách theo mã
    public SachDTO getSachById(String id) {
        for(SachDTO sach : ds){
            if(sach.getTenSach().equals(id)){
                return sach;
            }
        }
        return null;
    }

    // Lấy sách theo thể loại
    public ArrayList<SachDTO> getSachByCategory(String categoryId) {
        return (ArrayList<SachDTO>) ds.stream()
                .filter(sach -> sach.getTheloai().equals(categoryId))
                .toList();
    }

    // Lấy sách theo tác giả
    public ArrayList<SachDTO> getSachByAuthor(String authorId) {
        return (ArrayList<SachDTO>) ds.stream()
                .filter(sach -> sach.getTacGia().equals(authorId))
                .toList();
    }

    // Lấy sách theo nhà xuất bản
    public ArrayList<SachDTO> getSachByPublisher(String publisherId) {
       return (ArrayList<SachDTO>) ds.stream()
                .filter(sach -> sach.getNXB().equals(publisherId))
                .toList();
    }
}
