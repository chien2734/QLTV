package BUS;

import DAL.CT_PhieuTraDAL;
import DTO.CT_PhieuTraDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class CT_PhieuTraBUS {

    private CT_PhieuTraDAL ctPhieuTraDAL;
    private ArrayList<CT_PhieuTraDTO> ds;
    public CT_PhieuTraBUS() throws SQLException {
        ctPhieuTraDAL = new CT_PhieuTraDAL();
        ds = ctPhieuTraDAL.getAllCT_PhieuTra();
    }

    // Thêm chi tiết phiếu trả
    public boolean addCT_PhieuTra(CT_PhieuTraDTO ctPhieuTraDTO) {
        boolean isAdded = ctPhieuTraDAL.addCT_PhieuTra(ctPhieuTraDTO);
        if(isAdded){
            ds.add(ctPhieuTraDTO);
        }
        return isAdded;
    }

    // Cập nhật chi tiết phiếu trả
    public boolean updateCT_PhieuTra(CT_PhieuTraDTO ctPhieuTraDTO) {
        boolean isUpdated = ctPhieuTraDAL.updateCT_PhieuTra(ctPhieuTraDTO);
        if (isUpdated) {
            // Cập nhật danh sách
            for (int i = 0; i < ds.size(); i++) {
                if (ds.get(i).getMaPhieuTra().equals(ctPhieuTraDTO.getMaPhieuTra()) &&
                    ds.get(i).getMaSach().equals(ctPhieuTraDTO.getMaSach())) {
                    ds.set(i, ctPhieuTraDTO);
                    break;
                }
            }
        }
        return isUpdated;
    }

    // Xóa chi tiết phiếu trả
    public boolean deleteCT_PhieuTra(String maPhieuTra, String maSach) {
        boolean isDeleted = ctPhieuTraDAL.deleteCT_PhieuTra(maPhieuTra, maSach);
        if (isDeleted) {
            // Xóa khỏi danh sách
            ds.removeIf(ctpm -> ctpm.getMaPhieuTra().equals(maPhieuTra) &&
                                ctpm.getMaSach().equals(maSach));
        }
        return isDeleted;
    }

    // Tìm kiếm chi tiết phiếu trả theo mã phiếu trả
    public ArrayList<CT_PhieuTraDTO> searchCT_PhieuTraByMaPhieuTra(String maPhieuTra) {
        return (ArrayList<CT_PhieuTraDTO>) ds.stream()
                .filter(ctpt -> ctpt.getMaPhieuTra().equals(maPhieuTra))
                .toList();
    }
}
