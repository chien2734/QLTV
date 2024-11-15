package BUS;

import DAL.PhieuMuonDAL;
import DTO.PhieuMuonDTO;
import java.sql.Date;
import java.util.List;

public class PhieuMuonBUS {

    private PhieuMuonDAL phieuMuonDAL;

    public PhieuMuonBUS() {
        phieuMuonDAL = new PhieuMuonDAL();
    }

    // Thêm phiếu mượn mới
    public boolean addPhieuMuon(PhieuMuonDTO phieuMuonDTO) {
        return phieuMuonDAL.addPhieuMuon(phieuMuonDTO);
    }

    // Cập nhật phiếu mượn
    public boolean updatePhieuMuon(PhieuMuonDTO phieuMuonDTO) {
        return phieuMuonDAL.updatePhieuMuon(phieuMuonDTO);
    }

    // Xóa phiếu mượn
    public boolean deletePhieuMuon(String id) {
        return phieuMuonDAL.deletePhieuMuon(id);
    }

    // Lấy tất cả phiếu mượn
    public List<PhieuMuonDTO> getAllPhieuMuon() {
        return phieuMuonDAL.getAllPhieuMuon();
    }

    // Tìm kiếm phiếu mượn theo mã phiếu mượn
    public List<PhieuMuonDTO> searchPhieuMuonByMaPhieuMuon(String maPhieuMuon) {
        return phieuMuonDAL.searchPhieuMuonByMaPhieuMuon(maPhieuMuon);
    }

    // Tìm kiếm phiếu mượn theo mã thẻ
    public List<PhieuMuonDTO> searchPhieuMuonByMaThe(String maThe) {
        return phieuMuonDAL.searchPhieuMuonByMaThe(maThe);
    }

    // Tìm kiếm phiếu mượn theo ngày mượn
    public List<PhieuMuonDTO> searchPhieuMuonByNgayMuon(Date ngayMuon) {
        return phieuMuonDAL.searchPhieuMuonByNgayMuon(ngayMuon);
    }

    // Tìm kiếm phiếu mượn theo ngày trả
    public List<PhieuMuonDTO> searchPhieuMuonByHanTra(Date hanTra) {
        return phieuMuonDAL.searchPhieuMuonByHanTra(hanTra);
    }
}
