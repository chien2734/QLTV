/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.PhieuMuonDAO;
import DTO.BookCopyDTO;
import DTO.BookDTO;
import DTO.CardDTO;
import DTO.PhieuMuonDTO;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author ADMIN
 */
public class PhieuMuonBLL {
    
    public PhieuMuonBLL() {
    }
    
    PhieuMuonDAO pmDAL = new PhieuMuonDAO();
    
    
    public ArrayList<PhieuMuonDTO> getBorrowAll() {
        return pmDAL.selectAll();
    }
    
    public ArrayList<PhieuMuonDTO> getBorrowByCondition(String condition) {
        return pmDAL.selectByCondition(condition);
    }
    
    public PhieuMuonDTO getBorrowById(PhieuMuonDTO t) {
        return pmDAL.selectById(t);
    }
    
    public int insertBorrow(PhieuMuonDTO t) {
        return pmDAL.insert(t);
    }
    
    public int insertBorrow1(PhieuMuonDTO t) {
        return pmDAL.insert1(t);
    }
    
    public int updateBorrow(PhieuMuonDTO t) {
        return pmDAL.update(t);
    }
    
    public int deleteBorrow(PhieuMuonDTO t) {
        return pmDAL.delete(t);
    }
    
}
