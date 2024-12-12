package BUS;

import DAL.TacGiaDAL;
import DTO.TacGiaDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class TacGiaBUS {
    
    private TacGiaDAL tacGiaDAL;
    private ArrayList<TacGiaDTO> ds;
    // Constructor
    public TacGiaBUS() throws SQLException {
        tacGiaDAL = new TacGiaDAL();
        ds = tacGiaDAL.getAllTacGia();
    }
    
    // Thêm tác giả mới
    public String addTacGia(TacGiaDTO tacGiaDTO) {
        if(tacGiaDAL.hasID(tacGiaDTO.getId())){
            return "Mã này đã tồn tại, vui lòng nhập mã khác!";
        }
        boolean isAdded = tacGiaDAL.addTacGia(tacGiaDTO);
        if(isAdded){
            ds.add(tacGiaDTO);
            return "Thêm tác giả " + tacGiaDTO.getTen() +" thành công!";
        }
        return "Thêm tác giả không thành công!";
    }
    
    
    public String updateTacGia(TacGiaDTO tacGiaDTO) {
        boolean isUpdated = tacGiaDAL.updateTacGia(tacGiaDTO);
        if (isUpdated) {
            // Cập nhật danh sách
            for (int i = 0; i < ds.size(); i++) {
                if (ds.get(i).getId().equals(tacGiaDTO.getId())) {
                    ds.set(i, tacGiaDTO);
                    break;
                }
            }
            return "Cập nhật thông tin tác giả "+tacGiaDTO.getTen()+" thành công!";
        }
        return "Cập nhật không thành công!";
        
    }
    
    // Xóa tác giả
    public String deleteTacGia(String id) {
        boolean isDeleted = tacGiaDAL.deleteTacGia(id);
        if(isDeleted){
            ds.removeIf(tg -> tg.getId().equals(id));
            return "Xóa mã tác giả "+id+" thành công!";
        }
        return "Xóa không thành công!";
    }
    
    // Lấy tất cả tác giả
    public ArrayList<TacGiaDTO> getAllTacGia() {
        return ds;
    }
    
    // Tìm kiếm tác giả theo tên
    public ArrayList<TacGiaDTO> searchTacGiaByName(String name) {
        ArrayList<TacGiaDTO> result = new ArrayList<>();
        for(TacGiaDTO tg:ds){
            if(tg.getTen().toLowerCase().contains(name.toLowerCase())){
                result.add(tg);
            }
        }
        return result;               
    }
    
    // Lấy tác giả theo mã
    public TacGiaDTO getTacGiaById(String id) {
        for(TacGiaDTO tg : ds){
            if(tg.getId().equals(id)){
                return tg;
            }
        }
        return null;
    }
    
    public int getSoLuongSachofTacGia(String id){
        return tacGiaDAL.getSoLuongSachofTacGia(id);
    }
    
    public int getSSLCL(String id){
               return tacGiaDAL.getSoLuongSachConlai(id);
 
    }
    public int getSoLuongSachConLai(String id){
        int a = getSoLuongSachofTacGia(id);
        int b =  getSSLCL(id);
        return a - b >= 0 ? a-b : 0 ;
    }
}
