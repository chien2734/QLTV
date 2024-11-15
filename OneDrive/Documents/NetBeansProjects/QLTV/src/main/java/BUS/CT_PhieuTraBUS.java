package BUS;

import DAL.CT_PhieuTraDAL;
import DTO.CT_PhieuTraDTO;
import java.util.List;

public class CT_PhieuTraBUS {

    private CT_PhieuTraDAL ctPhieuTraDAL;

    public CT_PhieuTraBUS() {
        ctPhieuTraDAL = new CT_PhieuTraDAL();
    }

    // Thêm chi tiết phiếu trả
    public boolean addCT_PhieuTra(CT_PhieuTraDTO ctPhieuTraDTO) {
        return ctPhieuTraDAL.addCT_PhieuTra(ctPhieuTraDTO);
    }

    // Cập nhật chi tiết phiếu trả
    public boolean updateCT_PhieuTra(CT_PhieuTraDTO ctPhieuTraDTO) {
        return ctPhieuTraDAL.updateCT_PhieuTra(ctPhieuTraDTO);
    }

    // Xóa chi tiết phiếu trả
    public boolean deleteCT_PhieuTra(String maPhieuTra, String maSach) {
        return ctPhieuTraDAL.deleteCT_PhieuTra(maPhieuTra, maSach);
    }

    // Lấy tất cả chi tiết phiếu trả
    public List<CT_PhieuTraDTO> getAllCT_PhieuTra() {
        return ctPhieuTraDAL.getAllCT_PhieuTra();
    }

    // Lấy chi tiết phiếu trả theo mã phiếu trả và mã sách
    public CT_PhieuTraDTO getCT_PhieuTraById(String maPhieuTra, String maSach) {
        return ctPhieuTraDAL.getCT_PhieuTraById(maPhieuTra, maSach);
    }

    // Tìm kiếm chi tiết phiếu trả theo mã phiếu trả
    public List<CT_PhieuTraDTO> searchCT_PhieuTraByMaPhieuTra(String maPhieuTra) {
        return ctPhieuTraDAL.searchCT_PhieuTraByMaPhieuTra(maPhieuTra);
    }
}
