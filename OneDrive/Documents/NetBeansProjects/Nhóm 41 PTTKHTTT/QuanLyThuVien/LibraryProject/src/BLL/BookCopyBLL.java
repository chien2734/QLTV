/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.BookCopyDAO;
import DTO.BookCopyDTO;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class BookCopyBLL {
//    private AddBook ab;
    
    public BookCopyBLL() {
    }

    BookCopyDAO bcDAO  = new BookCopyDAO();
    
    public ArrayList<BookCopyDTO> getBookCopyAll() {
        return bcDAO.selectAll();
    }
    
    public ArrayList<BookCopyDTO> getBookCopyByCondition(String condition) {
        return bcDAO.selectByCondition(condition);
    }
    
    public BookCopyDTO getBookCopyByCondition1(String mads, int top) {
        return bcDAO.selectByCondition1(mads, top);
    }
    
    public BookCopyDTO getBookCopyById(BookCopyDTO t) {
        return bcDAO.selectById(t);
    }
    
    public int insertBookCopy(BookCopyDTO t) {
        return bcDAO.insert(t);
    }
    
    public int deleteBookCopy(BookCopyDTO t) {
        return bcDAO.delete(t);
    }
    
    public int updateBook(BookCopyDTO t) {
        return bcDAO.update(t);
    }
    
}
