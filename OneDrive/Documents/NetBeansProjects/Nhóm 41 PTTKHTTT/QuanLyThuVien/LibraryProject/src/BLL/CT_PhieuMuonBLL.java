
package BLL;

import DAL.CT_PhieuMuonDAO;
import DTO.CT_PhieuMuonDTO;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class CT_PhieuMuonBLL {
    public CT_PhieuMuonBLL() {
        
    }
    
    CT_PhieuMuonDAO ctpmDAL = new CT_PhieuMuonDAO();
    
    public ArrayList<CT_PhieuMuonDTO> getDetailBorrowAll() {
        return ctpmDAL.selectAll();
    }
    
    public ArrayList<CT_PhieuMuonDTO> getDetailBorrowByCondition(String condition) {
        return ctpmDAL.selectByCondition(condition);
    }
    
    public CT_PhieuMuonDTO getDetailBorrowById(CT_PhieuMuonDTO t) {
        return ctpmDAL.selectById(t);
    }
    
    public int insertDetailBorrow(CT_PhieuMuonDTO t) {
        return ctpmDAL.insert(t);
    }
    
    public int updateDetailBorrow(CT_PhieuMuonDTO t) {
        return ctpmDAL.update(t);
    }
    
    public int deleteDetailBorrow(CT_PhieuMuonDTO t) {
        return ctpmDAL.delete(t);
    }
    
    public ArrayList<CT_PhieuMuonDTO> getDetailBorrowByMaPhieu(String maphieu) {
        return ctpmDAL.selectByMaPhieu(maphieu);
    }    
    
    public CT_PhieuMuonDTO getDetailBorrowByMaPhieu1(String maphieu, String maSach) {
        return ctpmDAL.selectByMaPhieu1(maphieu, maSach);
    } 
}
