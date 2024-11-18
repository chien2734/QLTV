package BUS;

import DAL.ThanhVienDAL;
import DTO.ThanhVienDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class ThanhVienBUS {
    
    private ThanhVienDAL thanhVienDAL;
    private ArrayList<ThanhVienDTO> ds;
    // Constructor
    public ThanhVienBUS() throws SQLException {
        thanhVienDAL = new ThanhVienDAL();
        ds = thanhVienDAL.getAllThanhVien();
    }
    
    // Thêm thành viên mới
    public String addThanhVien(ThanhVienDTO thanhVienDTO) {
        if(thanhVienDAL.hasID(thanhVienDTO.getId())){
            return "Mã này đã tồn tại, vui lòng nhập mã khác!";
        }
        boolean isAdded = thanhVienDAL.addThanhVien(thanhVienDTO);
        if(isAdded){
            ds.add(thanhVienDTO);
            return "Thêm thành viên " + thanhVienDTO.getTen().toUpperCase() +" thành công!";
        }
        return "Thêm thành viên không thành công!";

    }
    
    // Cập nhật thành viên
    public String updateThanhVien(ThanhVienDTO thanhVienDTO) {
        boolean isUpdated = thanhVienDAL.updateThanhVien(thanhVienDTO);
        if (isUpdated) {
            // Cập nhật danh sách
            for (int i = 0; i < ds.size(); i++) {
                if (ds.get(i).getId().equals(thanhVienDTO.getId())) {
                    ds.set(i, thanhVienDTO);
                    break;
                }
            }
            return "Cập nhật thông tin thành viên "+thanhVienDTO.getTen().toUpperCase()+" thành công!";
        }
        return "Cập nhật không thành công!";
    }
    
    // Xóa thành viên
    public String deleteThanhVien(String id) {
       boolean isDeleted = thanhVienDAL.deleteThanhVien(id);
        if(isDeleted){
            ds.removeIf(tv -> tv.getId().equals(id));
            return "Xóa mã thành viên "+id+" thành công!";
        }
        return "Xóa không thành công!";
    }
    
    // Lấy tất cả thành viên
    public ArrayList<ThanhVienDTO> getAllThanhVien() {
        return ds;
    }
    
    // Tìm kiếm thành viên theo tên
    public ArrayList<ThanhVienDTO> searchThanhVienByName(String name) {
        return (ArrayList<ThanhVienDTO>) ds.stream()
                .filter(tv -> tv.getTen().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }
    
    // Lấy thành viên theo mã
    public ThanhVienDTO getThanhVienById(String id) {
        for(ThanhVienDTO tv : ds){
            if(tv.getId().equals(id)){
                return tv;
            }
        }
        return null;
    }
}
