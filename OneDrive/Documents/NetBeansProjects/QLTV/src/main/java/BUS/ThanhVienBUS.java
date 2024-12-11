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
    
    public String updateTrangThaiThe(ThanhVienDTO thanhVien) {
        boolean isUpdated = thanhVienDAL.updateTrangThaiThe(thanhVien);
        if (isUpdated) {
            for (int i = 0; i < ds.size(); i++) {
                if (ds.get(i).getId().equals(thanhVien.getId())) {
                    ds.set(i, thanhVien);
                    break;
                }
            }
            return "Cập nhật trạng thái thẻ "+thanhVien.getId()+"  thành công";
        }
        return "Cập nhật không thành công!";
    }
    
    public boolean hasID(String mathe){
        return thanhVienDAL.hasID(mathe);
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
        ArrayList<ThanhVienDTO> result = new ArrayList<>();
        for(ThanhVienDTO tv : ds){
            if(tv.getTen().toLowerCase().contains(name.toLowerCase())){
                result.add(tv) ;
            }
        }
        return result;
    }
    
    public String generateNewTVCode() {
         if (ds == null || ds.isEmpty()) {
            return "TV001";
        }
        String lastPMCode = ds.get(ds.size()-1).getId();
        int currentId = Integer.parseInt(lastPMCode.substring(2));
        int nextId = currentId + 1;
        return String.format("TV%03d", nextId);
    }
    
    public ArrayList<ThanhVienDTO> searchThanhVienBySDT(String sdt) {
        ArrayList<ThanhVienDTO> result = new ArrayList<>();
        for(ThanhVienDTO tv : ds){
            if(tv.getSdt().contains(sdt)){
                result.add(tv) ;
            }
        }
        return result;
    }
    
    public ArrayList<ThanhVienDTO> searchThanhVienByCCCD(String cccd) {
        ArrayList<ThanhVienDTO> result = new ArrayList<>();
        for(ThanhVienDTO tv : ds){
            if(tv.getCCCD().contains(cccd)){
                result.add(tv) ;
            }
        }
        return result;
    }
    
    // Lấy thành viên theo mã
    public ThanhVienDTO searchThanhVienById(String id) {
        for(ThanhVienDTO tv : ds){
            if(tv.getId().equals(id)){
                return tv;
            }
        }
        return null;
    }
    
    public int getSum(){
        return ds == null ? 0 : ds.size();
    }
    
    public ArrayList<Object[]> getDocGiaBYNgayDK(int month, int year){
        return thanhVienDAL.getDocGiaByNgayDK(month, year);
    }
    
    public ArrayList<Object[]> getDocGiaQuaHan(){
        return thanhVienDAL.getDocGiaQuaHan();
    }
    
    public ArrayList<Object[]> getDocGiaKhoaThe(){
        return thanhVienDAL.getDocGiaKhoaThe();
    }
    
    public ArrayList<Object[]> getTop10(){
        return thanhVienDAL.getTop10();
    }
}
