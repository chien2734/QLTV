package BUS;

import DAL.PhieuTraDAL;
import DTO.PhieuTraDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class PhieuTraBUS {

    private PhieuTraDAL phieuTraDAL;
    private ArrayList<PhieuTraDTO> ds;
    public PhieuTraBUS() throws SQLException {
        phieuTraDAL = new PhieuTraDAL();
        ds = phieuTraDAL.getAllPhieuTra();
    }

    // Thêm phiếu trả mới
    public boolean addPhieuTra(PhieuTraDTO phieuTraDTO) {
       if(phieuTraDAL.hasID(phieuTraDTO.getId())){
           return false;
       }
        boolean isAdded = phieuTraDAL.addPhieuTra(phieuTraDTO);
        if(isAdded){
            ds.add(phieuTraDTO);
        }
        return isAdded;
    }

    // Cập nhật phiếu trả
    public String updatePhieuTra(PhieuTraDTO phieuTraDTO) {
        boolean isUpdated = phieuTraDAL.updatePhieuTra(phieuTraDTO);
        if (isUpdated) {
            // Cập nhật danh sách
            for (int i = 0; i < ds.size(); i++) {
                if (ds.get(i).getId().equals(phieuTraDTO.getId())) {
                    ds.set(i, phieuTraDTO);
                    break;
                }
            }
            return "Cập nhật thông tin phiếu trả "+phieuTraDTO.getId()+" thành công!";
        }
        return "Cập nhật không thành công!";
    }

    // Xóa phiếu trả
    public String deletePhieuTra(String id) {
         boolean isDeleted = phieuTraDAL.deletePhieuTra(id);
        if(isDeleted){
            ds.removeIf(pt -> pt.getId().equals(id));
            return "Xóa mã phiếu trả "+id+" thành công!";
        }
        return "Xóa không thành công!";
    }

    // Lấy tất cả phiếu trả
    public ArrayList<PhieuTraDTO> getAllPhieuTra() {
        return ds;
    }

    public int getSum(){
        return ds == null ? 0 : ds.size();
    }
    // Tìm kiếm phiếu trả theo mã phiếu mượn
    public ArrayList<PhieuTraDTO> getPhieuTraByID(String id){
        ArrayList<PhieuTraDTO> result = new ArrayList<>();
        for(PhieuTraDTO pt : ds){
            if(pt.getId().contains(id)){
                result.add(pt);
            }
        }
        return result;
    }
    
    public ArrayList<PhieuTraDTO> searchPhieuTraByMaPhieuMuon(String maPhieuMuon) {
        ArrayList<PhieuTraDTO> result = new ArrayList<>();
        for(PhieuTraDTO pt : ds){
            if(pt.getMaPhieuMuon().contains(maPhieuMuon)){
                result.add(pt);
            }
        }
        return result;
    }

    // Tìm kiếm phiếu trả theo mã thẻ
    public ArrayList<PhieuTraDTO> searchPhieuTraByMaThe(String maThe) {
        ArrayList<PhieuTraDTO> result = new ArrayList<>();
        for(PhieuTraDTO pt : ds){
            if(pt.getMaThe().contains(maThe)){
                result.add(pt);
            }
        }
        return result;
    }

    // Tìm kiếm phiếu trả theo ngày trả
    public ArrayList<PhieuTraDTO> searchPhieuTraByNgayTra(java.sql.Date ngayTra) {
        ArrayList<PhieuTraDTO> result = new ArrayList<>();
        for(PhieuTraDTO pt : ds){
            if(pt.getNgayTra().equals(ngayTra)){
                result.add(pt);
            }
        }
        return result;
    }
    
    public int TinhSoNgay(java.sql.Date ngayTra, java.sql.Date hanTra){
        return phieuTraDAL.TinhSoNgay(ngayTra, hanTra);
    }
    
    public ArrayList<Object[]> CacLoaiPhi(){
        return phieuTraDAL.CacLoaiPhi();
    }
}
