package BUS;

import DAL.CT_PhieuMuonDAL;
import DTO.CT_PhieuMuonDTO;
import java.util.List;

public class CT_PhieuMuonBUS {

    private CT_PhieuMuonDAL ctPhieuMuonDAL;

    public CT_PhieuMuonBUS() {
        ctPhieuMuonDAL = new CT_PhieuMuonDAL();
    }

    // Thêm chi tiết phiếu mượn
    public boolean addCT_PhieuMuon(CT_PhieuMuonDTO ctPhieuMuonDTO) {
        return ctPhieuMuonDAL.addCT_PhieuMuon(ctPhieuMuonDTO);
    }

    // Cập nhật chi tiết phiếu mượn
    public boolean updateCT_PhieuMuon(CT_PhieuMuonDTO ctPhieuMuonDTO) {
        return ctPhieuMuonDAL.updateCT_PhieuMuon(ctPhieuMuonDTO);
    }

    // Xóa chi tiết phiếu mượn
    public boolean deleteCT_PhieuMuon(String maPhieuMuon, String maSach) {
        return ctPhieuMuonDAL.deleteCT_PhieuMuon(maPhieuMuon, maSach);
    }

    // Lấy tất cả chi tiết phiếu mượn
    public List<CT_PhieuMuonDTO> getAllCT_PhieuMuon() {
        return ctPhieuMuonDAL.getAllCT_PhieuMuon();
    }

    // Lấy chi tiết phiếu mượn theo mã phiếu mượn và mã sách
    public CT_PhieuMuonDTO getCT_PhieuMuonById(String maPhieuMuon, String maSach) {
        return ctPhieuMuonDAL.getCT_PhieuMuonById(maPhieuMuon, maSach);
    }

    // Tìm kiếm chi tiết phiếu mượn theo mã phiếu mượn
    public List<CT_PhieuMuonDTO> searchCT_PhieuMuonByMaPhieuMuon(String maPhieuMuon) {
        return ctPhieuMuonDAL.searchCT_PhieuMuonByMaPhieuMuon(maPhieuMuon);
    }
}
