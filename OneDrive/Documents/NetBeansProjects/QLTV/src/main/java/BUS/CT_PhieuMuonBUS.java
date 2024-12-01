package BUS;

import DAL.CT_PhieuMuonDAL;
import DTO.CT_PhieuMuonDTO;
import java.util.ArrayList;

public class CT_PhieuMuonBUS {

    private CT_PhieuMuonDAL ctPhieuMuonDAL;
    public CT_PhieuMuonBUS() {
        ctPhieuMuonDAL = new CT_PhieuMuonDAL();
    }

    // Thêm chi tiết phiếu mượn
    public boolean addCT_PhieuMuon(ArrayList<CT_PhieuMuonDTO> dsCTPM) {
        boolean check = true;
        for(CT_PhieuMuonDTO ctpm : dsCTPM){
            if(!ctPhieuMuonDAL.addCT_PhieuMuon(ctpm)){
                check = false;
            }
        }
        return check;
    }

    // Cập nhật chi tiết phiếu mượn
    public boolean updateCT_PhieuMuon(CT_PhieuMuonDTO ctPhieuMuonDTO) {
        return ctPhieuMuonDAL.updateCT_PhieuMuon(ctPhieuMuonDTO);
                
    }

    // Xóa chi tiết phiếu mượn
    public boolean deleteCT_PhieuMuon(ArrayList<CT_PhieuMuonDTO> dsCTPM) {
        boolean check = true;
        for(CT_PhieuMuonDTO ctpm : dsCTPM){
            if(!ctPhieuMuonDAL.deleteCT_PhieuMuon(ctpm)){
                check = false;
            }
        }
        return check;
    }


    // Lấy tất cả chi tiết phiếu mượn
    public ArrayList<CT_PhieuMuonDTO> getAllCT_PhieuMuon() {
        return ctPhieuMuonDAL.getAllCT_PhieuMuon();
    }

    // Lấy chi tiết phiếu mượn theo mã phiếu mượn 
    public ArrayList<CT_PhieuMuonDTO> getCT_PhieuMuonByPhieuMuonId(String maPhieuMuon) {
        ArrayList<CT_PhieuMuonDTO> result = new ArrayList<>();
        for(CT_PhieuMuonDTO ctpm : ctPhieuMuonDAL.getAllCT_PhieuMuon()){
            if(ctpm.getMaPhieuMuon().equals(maPhieuMuon)){
                result.add(ctpm);
            }
        }
        return result;
    }  
}
