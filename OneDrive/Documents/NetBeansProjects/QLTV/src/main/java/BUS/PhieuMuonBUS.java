package BUS;

import DAL.PhieuMuonDAL;
import DTO.PhieuMuonDTO;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class PhieuMuonBUS {

    private PhieuMuonDAL phieuMuonDAL;
    private ArrayList<PhieuMuonDTO> ds;
    public PhieuMuonBUS() throws SQLException {
        phieuMuonDAL = new PhieuMuonDAL();
        ds = phieuMuonDAL.getAllPhieuMuon();
    }

    // Thêm phiếu mượn mới
    public String addPhieuMuon(PhieuMuonDTO phieuMuonDTO) {
       if(phieuMuonDAL.hasID(phieuMuonDTO.getId())){
            return "Mã này đã tồn tại, vui lòng nhập mã khác!";
        }
        boolean isAdded = phieuMuonDAL.addPhieuMuon(phieuMuonDTO);
        if(isAdded){
            ds.add(phieuMuonDTO);
            return "Tạo phiếu mượn " + phieuMuonDTO.getId() +" thành công!";
        }
        return "Tạo phiếu mượn không thành công!";
    }

    // Cập nhật phiếu mượn
    public String updatePhieuMuon(PhieuMuonDTO phieuMuonDTO) {
        boolean isUpdated = phieuMuonDAL.updatePhieuMuon(phieuMuonDTO);
        if (isUpdated) {
            // Cập nhật danh sách
            for (int i = 0; i < ds.size(); i++) {
                if (ds.get(i).getId().equals(phieuMuonDTO.getId())) {
                    ds.set(i, phieuMuonDTO);
                    break;
                }
            }
            return "Cập nhật thông tin phiếu mượn "+phieuMuonDTO.getId()+" thành công!";
        }
        return "Cập nhật không thành công!";
    }

    // Xóa phiếu mượn
    public String deletePhieuMuon(String id) {
        boolean isDeleted = phieuMuonDAL.deletePhieuMuon(id);
        if(isDeleted){
            ds.removeIf(pt -> pt.getId().equals(id));
            return "Xóa mã phiếu mượn "+id+" thành công!";
        }
        return "Xóa không thành công!";
    }

    // Lấy tất cả phiếu mượn
    public ArrayList<PhieuMuonDTO> getAllPhieuMuon() {
        return ds;
    }

    // Tìm kiếm phiếu mượn theo mã phiếu mượn
    public PhieuMuonDTO searchPhieuMuonByMaPhieuMuon(String maPhieuMuon) {
        for(PhieuMuonDTO pm : ds){
            if(pm.getId().equals(maPhieuMuon)){
                return pm;
            }
        }
        return null;
    }

    // Tìm kiếm phiếu mượn theo mã thẻ
    public ArrayList<PhieuMuonDTO> searchPhieuMuonByMaThe(String maThe) {
        return (ArrayList<PhieuMuonDTO>) ds.stream()
                .filter(pm -> pm.getMaThe().equals(maThe))
                .toList();
    }

    // Tìm kiếm phiếu mượn theo ngày mượn
    public ArrayList<PhieuMuonDTO> searchPhieuMuonByNgayMuon(Date ngayMuon) {
        return (ArrayList<PhieuMuonDTO>) ds.stream()
                .filter(pm -> pm.getNgayMuon().equals(ngayMuon))
                .toList();
    }

    // Tìm kiếm phiếu mượn theo ngày trả
    public ArrayList<PhieuMuonDTO> searchPhieuMuonByHanTra(Date hanTra) {
        return (ArrayList<PhieuMuonDTO>) ds.stream()
                .filter(pm -> pm.getHanTra().equals(hanTra))
                .toList();
    }
}
