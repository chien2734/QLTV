/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.PhieuTraDAO;
import DTO.PhieuMuonDTO;
import DTO.PhieuTraDTO;
import java.util.ArrayList;
import sqlite.DAOInterface;

/**
 *
 * @author PC
 */
public class PhieuTraBLL implements DAOInterface<PhieuTraDTO>{
    PhieuTraDAO ptDAO = new PhieuTraDAO();

    @Override
    public int insert(PhieuTraDTO t) {
        return ptDAO.insert(t);
    }

    @Override
    public int update(PhieuTraDTO t) {
        return ptDAO.update(t);
    }

    @Override
    public int delete(PhieuTraDTO t) {
        return ptDAO.delete(t);
    }

    @Override
    public ArrayList<PhieuTraDTO> selectAll() {
        return ptDAO.selectAll();
    }

    @Override
    public PhieuTraDTO selectById(PhieuTraDTO t) {
        return ptDAO.selectById(t);
    }

    @Override
    public ArrayList<PhieuTraDTO> selectByCondition(String condition) {
        return ptDAO.selectByCondition(condition);    }
    
}
