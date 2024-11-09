/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.CT_PhieuTraDAO;
import DTO.CT_PhieuTraDTO;
import java.util.ArrayList;


public class CT_PhieuTraBLL {
    public CT_PhieuTraBLL() {
        
    }
    
    CT_PhieuTraDAO ctptDAL = new CT_PhieuTraDAO();
    
    public ArrayList<CT_PhieuTraDTO> getSelectedAll() {
        return ctptDAL.selectAll();
    }
    
    public ArrayList<CT_PhieuTraDTO> getSelectedByCondition(String condition) {
        return ctptDAL.selectByCondition(condition);
    }
    
    public CT_PhieuTraDTO getSelectedById(CT_PhieuTraDTO t) {
        return ctptDAL.selectById(t);
    }
    
    public CT_PhieuTraDTO getSelectedByMaPhieu1(String maphieu, String masach) {
        return ctptDAL.selectByMaPhieu1(maphieu, masach);
    }
    
    public ArrayList<CT_PhieuTraDTO> getSelectedByMaPhieu(CT_PhieuTraDTO t) {
        return ctptDAL.selectByMaPhieu(t);
    }
    
    public int insert(CT_PhieuTraDTO t) {
        return ctptDAL.insert(t);
    }
    
    public int update(CT_PhieuTraDTO t) {
        return ctptDAL.update(t);
    }
    
    public int delete(CT_PhieuTraDTO t) {
        return ctptDAL.delete(t);
    }
      
}
