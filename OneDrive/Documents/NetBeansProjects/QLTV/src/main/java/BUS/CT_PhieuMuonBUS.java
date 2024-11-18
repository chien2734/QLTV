package BUS;

import DAL.CT_PhieuMuonDAL;
import DTO.CT_PhieuMuonDTO;
import java.util.ArrayList;

public class CT_PhieuMuonBUS {

    private CT_PhieuMuonDAL ctPhieuMuonDAL;
    private ArrayList<CT_PhieuMuonDTO> ds;
    public CT_PhieuMuonBUS() {
        ctPhieuMuonDAL = new CT_PhieuMuonDAL();
        ds = ctPhieuMuonDAL.getAllCT_PhieuMuon();
    }

    // Thêm chi tiết phiếu mượn
    public boolean addCT_PhieuMuon(CT_PhieuMuonDTO ctPhieuMuonDTO) {
        boolean isAdded = ctPhieuMuonDAL.addCT_PhieuMuon(ctPhieuMuonDTO);
        if (isAdded) {
            ds.add(ctPhieuMuonDTO); // Đồng bộ danh sách
        }
        return isAdded;
    }

    

    // Cập nhật chi tiết phiếu mượn
    public boolean updateCT_PhieuMuon(CT_PhieuMuonDTO ctPhieuMuonDTO) {
        boolean isUpdated = ctPhieuMuonDAL.updateCT_PhieuMuon(ctPhieuMuonDTO);
        if (isUpdated) {
            // Cập nhật danh sách
            for (int i = 0; i < ds.size(); i++) {
                if (ds.get(i).getMaPhieuMuon().equals(ctPhieuMuonDTO.getMaPhieuMuon()) &&
                    ds.get(i).getMaSach().equals(ctPhieuMuonDTO.getMaSach())) {
                    ds.set(i, ctPhieuMuonDTO);
                    break;
                }
            }
        }
        return isUpdated;
}


    // Xóa chi tiết phiếu mượn
    public boolean deleteCT_PhieuMuon(String maPhieuMuon, String maSach) {
        boolean isDeleted = ctPhieuMuonDAL.deleteCT_PhieuMuon(maPhieuMuon, maSach);
        if (isDeleted) {
            // Xóa khỏi danh sách
            ds.removeIf(ctpm -> ctpm.getMaPhieuMuon().equals(maPhieuMuon) &&
                                ctpm.getMaSach().equals(maSach));
        }
        return isDeleted;
    }


    // Lấy tất cả chi tiết phiếu mượn
    public ArrayList<CT_PhieuMuonDTO> getAllCT_PhieuMuon() {
        return ds;
    }

    // Lấy chi tiết phiếu mượn theo mã phiếu mượn 
    public ArrayList<CT_PhieuMuonDTO> getCT_PhieuMuonByPhieuMuonId(String maPhieuMuon) {
    return (ArrayList<CT_PhieuMuonDTO>) ds.stream()
            .filter(ctpm -> ctpm.getMaPhieuMuon().equals(maPhieuMuon))
            .toList();
    }

    
}
