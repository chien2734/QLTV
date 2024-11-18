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
    public String addPhieuTra(PhieuTraDTO phieuTraDTO) {
       if(phieuTraDAL.hasID(phieuTraDTO.getId())){
            return "Mã này đã tồn tại, vui lòng nhập mã khác!";
        }
        boolean isAdded = phieuTraDAL.addPhieuTra(phieuTraDTO);
        if(isAdded){
            ds.add(phieuTraDTO);
            return "Tạo phiếu trả " + phieuTraDTO.getId() +" thành công!";
        }
        return "Tạo phiếu trả không thành công!";
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

    // Tìm kiếm phiếu trả theo mã phiếu mượn
    public PhieuTraDTO searchPhieuTraByMaPhieuMuon(String maPhieuMuon) {
        for(PhieuTraDTO pt : ds){
            if(pt.getId().equals(maPhieuMuon)){
                return pt;
            }
        }
        return null;
    }

    // Tìm kiếm phiếu trả theo mã thẻ
    public ArrayList<PhieuTraDTO> searchPhieuTraByMaThe(String maThe) {
        return (ArrayList<PhieuTraDTO>) ds.stream()
                .filter(pt -> pt.getMaThe().equals(maThe))
                .toList();
    }

    // Tìm kiếm phiếu trả theo ngày trả
    public ArrayList<PhieuTraDTO> searchPhieuTraByNgayTra(java.sql.Date ngayTra) {
        return (ArrayList<PhieuTraDTO>) ds.stream()
                .filter(pt -> pt.getNgayTra().equals(ngayTra))
                .toList();
    }
}
