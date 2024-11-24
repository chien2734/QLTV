package BUS;

import DAL.CT_PhieuTraDAL;
import DTO.CT_PhieuTraDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class CT_PhieuTraBUS {

    private CT_PhieuTraDAL ctPhieuTraDAL;
    public CT_PhieuTraBUS() throws SQLException {
        ctPhieuTraDAL = new CT_PhieuTraDAL();
    }

    // Thêm chi tiết phiếu trả
    public boolean addCT_PhieuTra(ArrayList<CT_PhieuTraDTO> dsCTPT) {
        boolean check = true;
        for(CT_PhieuTraDTO ctpt : dsCTPT){
            if(!ctPhieuTraDAL.addCT_PhieuTra(ctpt)){
                check = false;
            }
        }
        return check;
    }

    // Cập nhật chi tiết phiếu trả
    public boolean updateCT_PhieuTra(CT_PhieuTraDTO ctPhieuTraDTO) {
        return ctPhieuTraDAL.updateCT_PhieuTra(ctPhieuTraDTO);     
    }

    // Xóa chi tiết phiếu trả
    public boolean deleteCT_PhieuTra(String maPhieuTra, String maSach) {
        return ctPhieuTraDAL.deleteCT_PhieuTra(maPhieuTra, maSach);
    }

    // Tìm kiếm chi tiết phiếu trả theo mã phiếu trả
    public ArrayList<CT_PhieuTraDTO> searchCT_PhieuTraByMaPhieuTra(String maPhieuTra) {
        ArrayList<CT_PhieuTraDTO> result = new ArrayList<>();
        for(CT_PhieuTraDTO ctpt : ctPhieuTraDAL.getAllCT_PhieuTra()){
            if(ctpt.getMaPhieuTra().equals(maPhieuTra)){
            result.add(ctpt);
            }
        }
       return result; 
    }
}
