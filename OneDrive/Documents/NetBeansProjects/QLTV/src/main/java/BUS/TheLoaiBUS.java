package BUS;

import DAL.TheLoaiDAL;
import DTO.TheLoaiDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class TheLoaiBUS {
    
    private TheLoaiDAL theLoaiDAL;
    private ArrayList<TheLoaiDTO> ds;
    // Constructor
    public TheLoaiBUS() throws SQLException {
        theLoaiDAL = new TheLoaiDAL();
        ds = theLoaiDAL.getAllTheLoai();
    }
    
    // Thêm thể loại mới
    public String addTheLoai(TheLoaiDTO theLoaiDTO) {
        if(theLoaiDAL.hasID(theLoaiDTO.getId())){
            return "Mã này đã tồn tại, vui lòng nhập mã khác!";
        }
        boolean isAdded = theLoaiDAL.addTheLoai(theLoaiDTO);
        if(isAdded){
            ds.add(theLoaiDTO);
            return "Thêm thể loại " + theLoaiDTO.getTen() +" thành công!";
        }
        return "Thêm thể loại không thành công!";
    }
    
    // Cập nhật thể loại
    public String updateTheLoai(TheLoaiDTO theLoaiDTO) {
        boolean isUpdated = theLoaiDAL.updateTheLoai(theLoaiDTO);
        if (isUpdated) {
            // Cập nhật danh sách
            for (int i = 0; i < ds.size(); i++) {
                if (ds.get(i).getId().equals(theLoaiDTO.getId())) {
                    ds.set(i, theLoaiDTO);
                    break;
                }
            }
            return "Cập nhật thông tin thể loại "+theLoaiDTO.getTen()+" thành công!";
        }
        return "Cập nhật không thành công!";
    }
    
    public int getSoLuongSachofTheLoai(String id){
        return theLoaiDAL.getSoLuongSachofTheLoai(id);
    }
    // Xóa thể loại
    public String deleteTheLoai(String id) {
         boolean isDeleted = theLoaiDAL.deleteTheLoai(id);
        if(isDeleted){
            ds.removeIf(tl -> tl.getId().equals(id));
            return "Xóa mã thể loại "+id+" thành công!";
        }
        return "Xóa không thành công!";
    }
    
    // Lấy tất cả thể loại
    public ArrayList<TheLoaiDTO> getAllTheLoai() {
        return ds;
    }
    
    // Tìm kiếm thể loại theo tên
    public ArrayList<TheLoaiDTO> searchTheLoaiByName(String name) {
        ArrayList<TheLoaiDTO> result = new ArrayList<>();
        for(TheLoaiDTO tl:ds){
            if(tl.getTen().toLowerCase().contains(name.toLowerCase())){
                result.add(tl);
            }
        }
        return result;   
    }
    
    // Lấy thể loại theo mã
    public TheLoaiDTO getTheLoaiById(String id) {
        for(TheLoaiDTO tl : ds){
            if(tl.getId().equals(id)){
                return tl;
            }
        }
        return null;
    }
    
    public int getSLCL(String id){
        return theLoaiDAL.getSoLuongSachConlai(id);
    }
    
    public int getSoLuongSachConLai(String id){
        int a = getSoLuongSachofTheLoai(id);
        int b = getSLCL(id);
        return a - b >= 0 ? a-b : 0 ;    }
}
