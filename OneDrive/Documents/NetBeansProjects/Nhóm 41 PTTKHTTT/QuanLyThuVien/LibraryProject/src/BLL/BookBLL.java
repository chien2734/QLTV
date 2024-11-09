/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.BookDAO;
import DTO.BookDTO;
import java.util.ArrayList;


/**
 *
 * @author ADMIN
 */
public class BookBLL{

//    private InfoUpdateBook inUp;
//
//    public BookBLL(InfoUpdateBook inUp) {
//        this.inUp = inUp;
//    }
    public BookBLL() {
        
    }
    
    BookDAO bookDAL = new BookDAO();
    
    public ArrayList<BookDTO> getBookAll() {
        return bookDAL.selectAll();
    }
    
    public ArrayList<BookDTO> getBookAll1() {
        return bookDAL.selectAll1();
    }
    
    public ArrayList<BookDTO> getBookByCondition(String condition) {
        return bookDAL.selectByCondition(condition);
    }
    
    public BookDTO getBookById(BookDTO t) {
        return bookDAL.selectById(t);
    }
    
    public BookDTO getBookByIdMaDS(String mads) {
        return bookDAL.selectByIdMaDS(mads);
    }
    
    public int insertBook(BookDTO t) {
        return bookDAL.insert(t);
    }
    
    public int deleteBook(BookDTO t) {
        return bookDAL.delete(t);
    }
    
    public int updateBook(BookDTO t) {
        return bookDAL.update(t);
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        BookDTO book = null;
//        String src = e.getActionCommand();
//        if (src.equals("Tìm kiếm")) {
//            this.inUp.timKiemSach();
//        } else if (src.equals("Xóa")) {
////            this.inUp.setDeleteDB();
//        } else if (src.equals("Cập nhật")) {
//            this.inUp.setTextForUpdate();
//        } else if (src.equals("Thông tin các bản sách")) {
//            //hiển thị 1 jframe mới trong này, có hiển thị thêm thông tin về số lượng bản copy cũng như hiển thị mã sách chứ ko phải mã đầu sách
//            //trong cái này cũng sẽ có cả phần cập nhật thông tin sách copy, nếu muốn chỉnh sửa thì làm như update phía dưới của đầu sách
//            this.inUp.addDetailCopyBook();
//        }
//        if (src.equals("Update")) {
//            BookDTO bookNew = this.inUp.updateBook();
//            System.out.println("bookNew: " + bookNew.toString());
//            String sql = "AND DS.TrangThai = 1 AND TL.TenLoai = N'" + bookNew.getTenLoai() + "' AND DS.TenSach = N'"
//                    + bookNew.getTenSach() + "' AND DS.TacGia = N'" + bookNew.getTacGia() + "' AND N.TenNXB = N'"
//                    + bookNew.getMaNXB() + "' AND DS.NamXB = " + bookNew.getNamXB() + " AND DS.MaDS != '" + bookNew.getMaDS()+"'";
//            ArrayList<BookDTO> listBook = BookDAO.getInstance().selectByCondition(sql);
//            if (listBook.isEmpty()) {
//                String tenLoai = bookNew.getTenLoai();
//                TheLoaiDTO theLoai = TheLoaiDAO.getInstance().selectById(new TheLoaiDTO(tenLoai));
//                System.out.println("Đây là thể loại nè: " + theLoai);
//                bookNew.setTenLoai(theLoai.getMaLoai());
//
//                String tenNXB = bookNew.getMaNXB();
//                NXBDTO nxb = NXBDAO.getInstance().selectById(new NXBDTO(tenNXB));
//                bookNew.setMaNXB(nxb.getMaNXB());
//
//                System.out.println("Dữ liệu để up " + bookNew);
//                BookDAO.getInstance().update(bookNew);
//                ArrayList<BookDTO> list = BookDAO.getInstance().selectAll();
//                this.inUp.loadDataforTable(list);
//                JOptionPane.showMessageDialog(null, "Cập nhật sách thành công", "update", JOptionPane.INFORMATION_MESSAGE);
//                this.inUp.resetFrm();
//            } else {
//                JOptionPane.showMessageDialog(null, "Sách đã tồn tại, không thể cập nhật", "warning", JOptionPane.INFORMATION_MESSAGE);
//                this.inUp.resetFrm();
//            }
//        }
//    }

}
