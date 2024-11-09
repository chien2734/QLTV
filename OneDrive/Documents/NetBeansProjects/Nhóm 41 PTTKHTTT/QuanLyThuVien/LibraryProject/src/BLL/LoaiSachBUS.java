/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import java.util.ArrayList;
import DAL.NXBDAO;
import DAL.TheLoaiDAO;
import DTO.NXBDTO;
import DTO.TheLoaiDTO;
import GUI.LoaiSachGUI;

/**
 *
 * @author ADMIN
 */
public class LoaiSachBUS{
//    public LoaiSachGUI loaiSach;
//    
//    public LoaiSachBUS(LoaiSachGUI loaiSach) {
//        this.loaiSach = loaiSach;
//    }
    
    public LoaiSachBUS() {
        
    }
    
    TheLoaiDAO theLoaiDAL = new TheLoaiDAO();

    public LoaiSachBUS(LoaiSachGUI aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ArrayList<TheLoaiDTO> getTheLoaiAll() {
        return theLoaiDAL.selectAll();
    }
    
    public ArrayList<TheLoaiDTO> getTheLoaiByCondition(String condition) {
        return theLoaiDAL.selectByCondition(condition);
    }
    
    public TheLoaiDTO getTheLoaiById(TheLoaiDTO t) {
        return theLoaiDAL.selectById(t);
    }
    
    public int insertTheLoai(TheLoaiDTO t) {
        return theLoaiDAL.insert(t);
    }
    
    public int deleteTheLoai(TheLoaiDTO t) {
        return theLoaiDAL.delete(t);
    }
    
    public int updateTheLoai(TheLoaiDTO t) {
        return theLoaiDAL.update(t);
    }
    
    NXBDAO nxbDAL = new NXBDAO();
    
    public ArrayList<NXBDTO> getNXBAll() {
        return nxbDAL.selectAll();
    }
    
    public ArrayList<NXBDTO> getNXBByCondition(String condition) {
        return nxbDAL.selectByCondition(condition);
    }
    
    public NXBDTO getNXBById(NXBDTO t) {
        return nxbDAL.selectById(t);
    }
    
    public int insertNXB(NXBDTO t) {
        return nxbDAL.insert(t);
    }
    
    public int deleteNXB(NXBDTO t) {
        return nxbDAL.delete(t);
    }
    
    public int update(NXBDTO t) {
        return nxbDAL.update(t);
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//           String src = e.getActionCommand();
//           if(src.equals("Thêm thể loại")) {
//               this.loaiSach.addTheLoai();
//           } else if(src.equals("Thêm NXB")) {
//               this.loaiSach.addNXB();
//           } else if(src.equals("Tìm kiếm TL")) {
//               this.loaiSach.searchTL();
//           } else if(src.equals("Tìm kiếm NXB")) {
//               this.loaiSach.searchNXB();
//           } else if(src.equals("Xoá thể loại")) {
//               this.loaiSach.deleteTL();
//           } else if(src.equals("Xóa NXB")) {
//               this.loaiSach.deleteNXB();
//           }
//    }
    
}
