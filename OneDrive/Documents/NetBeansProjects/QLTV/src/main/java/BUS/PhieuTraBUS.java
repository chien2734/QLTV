package BUS;

import DAL.PhieuTraDAL;
import DTO.PhieuTraDTO;
import java.util.List;

public class PhieuTraBUS {

    private PhieuTraDAL phieuTraDAL;

    public PhieuTraBUS() {
        phieuTraDAL = new PhieuTraDAL();
    }

    // Thêm phiếu trả mới
    public boolean addPhieuTra(PhieuTraDTO phieuTraDTO) {
        return phieuTraDAL.addPhieuTra(phieuTraDTO);
    }

    // Cập nhật phiếu trả
    public boolean updatePhieuTra(PhieuTraDTO phieuTraDTO) {
        return phieuTraDAL.updatePhieuTra(phieuTraDTO);
    }

    // Xóa phiếu trả
    public boolean deletePhieuTra(String id) {
        return phieuTraDAL.deletePhieuTra(id);
    }

    // Lấy tất cả phiếu trả
    public List<PhieuTraDTO> getAllPhieuTra() {
        return phieuTraDAL.getAllPhieuTra();
    }

    // Tìm kiếm phiếu trả theo mã phiếu mượn
    public List<PhieuTraDTO> searchPhieuTraByMaPhieuMuon(String maPhieuMuon) {
        return phieuTraDAL.searchPhieuTraByMaPhieuMuon(maPhieuMuon);
    }

    // Tìm kiếm phiếu trả theo mã thẻ
    public List<PhieuTraDTO> searchPhieuTraByMaThe(String maThe) {
        return phieuTraDAL.searchPhieuTraByMaThe(maThe);
    }

    // Tìm kiếm phiếu trả theo ngày trả
    public List<PhieuTraDTO> searchPhieuTraByNgayTra(java.sql.Date ngayTra) {
        return phieuTraDAL.searchPhieuTraByNgayTra(ngayTra);
    }
}
