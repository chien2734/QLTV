/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.ReaderDAO;
import DTO.ReaderDTO;
import java.util.ArrayList;

/**
 *
 * @author HUY LONG
 */
public class ReaderBUS {

    private ReaderDAO readerDAO = new ReaderDAO();

    public ArrayList<ReaderDTO> getAllReaders() {
        return readerDAO.getAllReaders();
    }

    public String addReader(ReaderDTO reader) {
        if (readerDAO.hasTenDN(reader.getTenDN())) {
            return "Tên đăng nhập đã tồn tại !!!";
        }

        if (readerDAO.addReader(reader)) {
            return "Thêm thành công !";
        }

        return "Thêm thất bại !";
    }

    public String removeReader(ReaderDTO reader) {
        if (readerDAO.removeReader(reader)) {
            return "Xóa thành công !";
        }

        return "Xóa thất bại !";
    }

    public String updateReader(ReaderDTO reader) {
        if (readerDAO.updateReader(reader)) {
            return "Cập nhật thành công !";
        }

        return "Cập nhật thất bại !";
    }

    public String lockReader(ReaderDTO reader) {
        if (readerDAO.lockReader(reader)) {
            return "Khóa thành công !";
        }

        return "Khóa thất bại !";
    }

    public String unlockReader(ReaderDTO reader) {
        if (readerDAO.unlockReader(reader)) {
            return "Kích hoạt thành công !";
        }

        return "Kích hoạt thất bại !";
    }

    public String changePassword(ReaderDTO reader) {
        if(readerDAO.updateReader(reader)){
            return "Đổi mật khẩu thành công !";
        }
        
        return "Đổi mật khẩu thất bại !";
    }
    public ReaderDTO selectById(String tendn) {
        return readerDAO.selectById(tendn);
    }

    public ArrayList<ReaderDTO> getReadersByCondition(String condition) {
        return readerDAO.getReadersByCondition(condition);
    }
}
