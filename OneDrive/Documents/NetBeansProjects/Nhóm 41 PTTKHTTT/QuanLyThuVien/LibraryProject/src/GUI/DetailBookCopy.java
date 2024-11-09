/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BLL.BookBLL;
import BLL.BookCopyBLL;
import DTO.BookCopyDTO;
import DTO.BookDTO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class DetailBookCopy extends JFrame {

    public JLabel jLabel_numBookCopy;
    public JTable jTable_detailBookCopy;
    public JScrollPane jScrollPane_tableBookCopy;
    public DefaultTableModel tblModel;
    public JPopupMenu jPopupMenu_infoCopy;
    public JMenuItem jMenu_xoaSach;
    public JMenuItem jMenu_infoCopy;
    public String maDS;
    public BookCopyBLL bcBLL = new BookCopyBLL();
    public BookBLL bBLL = new BookBLL();

    public DetailBookCopy(String maDS) {
        this.maDS = maDS;
        this.init();
        System.out.println("----: " + this.maDS);
    }

    public void init() {
        jLabel_numBookCopy = new JLabel();
        jPopupMenu_infoCopy = new JPopupMenu();
        jMenu_infoCopy = new JMenuItem("Cập nhật bản sách");
        jMenu_xoaSach = new JMenuItem("Xóa sách");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(new Dimension(704, 425));
        getContentPane().setBackground(Color.white);
        setTitle("Chi tiết sách");
        setLocationRelativeTo(null);
        this.setLayout(null);

        jLabel_numBookCopy.setFont(new Font("Roboto", 0, 20));
        jLabel_numBookCopy.setBounds(20, 20, 300, 40);
        this.add(jLabel_numBookCopy);

        jTable_detailBookCopy = new JTable();
        jScrollPane_tableBookCopy = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã sách", "Tình trạng", "Hình thức", "Giá", "Trạng Thái"};
        tblModel.setColumnIdentifiers(header);
        jTable_detailBookCopy.setModel(tblModel);
        jTable_detailBookCopy.getTableHeader().setFont(new Font("Roboto", 1, 20));
        jTable_detailBookCopy.getTableHeader().setBackground(new Color(187, 222, 251));
        jTable_detailBookCopy.setDefaultEditor(Object.class, null);
        jScrollPane_tableBookCopy.setViewportView(jTable_detailBookCopy);
        ArrayList<BookCopyDTO> listSachCopy = bcBLL.getBookCopyByCondition("AND S.MADS = N'" + this.maDS + "'");
        for (BookCopyDTO s : listSachCopy) {
            System.out.println("trạng thái???" + s.toString());
            tblModel.addRow(new Object[]{s.getMaSach(), s.getTinhTrang(), s.getLoaiSach(), s.getGia(), s.getTrangThai()});
        }

        jTable_detailBookCopy.setFont(new Font("Roboto", 0, 18));
        jTable_detailBookCopy.setRowHeight(50);
        jScrollPane_tableBookCopy.setBounds(20, 80, 650, 250);
        this.add(jScrollPane_tableBookCopy);

        jMenu_infoCopy.setBackground(Color.white);
        jMenu_infoCopy.setFont(new Font("Roboto", 0, 18));
        jMenu_infoCopy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jMenuCopyActionPerformed(evt);
            }
        });
        jPopupMenu_infoCopy.add(jMenu_infoCopy);

        jMenu_xoaSach.setBackground(Color.white);
        jMenu_xoaSach.setFont(new Font("Roboto", 0, 18));
        jMenu_xoaSach.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jMenuDelActionPerformed(evt);
            }
        });
        jPopupMenu_infoCopy.add(jMenu_xoaSach);
        jTable_detailBookCopy.setComponentPopupMenu(jPopupMenu_infoCopy);

        jLabel_numBookCopy.setText("Tổng số lượng bản sách: " + jTable_detailBookCopy.getRowCount());

        this.setVisible(true);
    }

    public void jMenuCopyActionPerformed(ActionEvent evt) {
        int selecRow = jTable_detailBookCopy.getSelectedRow();
        String maSach = (String) jTable_detailBookCopy.getValueAt(selecRow, 0);
        String tinhTrang = (String) jTable_detailBookCopy.getValueAt(selecRow, 1);
        String loai = (String) jTable_detailBookCopy.getValueAt(selecRow, 2);
        int gia = (int) jTable_detailBookCopy.getValueAt(selecRow, 3);
        String trangThai = (String) jTable_detailBookCopy.getValueAt(selecRow, 4);
        int giaSach = Integer.valueOf(gia);
        new UpdateBookCopy(new BookCopyDTO(maSach, maDS, loai, giaSach, trangThai, tinhTrang), maDS);
    }

    public void jMenuDelActionPerformed(ActionEvent evt) {
        int selecRow = jTable_detailBookCopy.getSelectedRow();
        BookDTO b = bBLL.getBookById(new BookDTO(maDS));
        if (selecRow != -1) {
            String tinhTrang = (String) jTable_detailBookCopy.getValueAt(selecRow, 1);
            if (!tinhTrang.equals("Đã mượn")) {
                int yes = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa sách này?", "warning", JOptionPane.YES_NO_OPTION);
                if (yes == JOptionPane.YES_OPTION) {
                    String maSach = (String) jTable_detailBookCopy.getValueAt(selecRow, 0);
                    BookCopyDTO book = bcBLL.getBookCopyById(new BookCopyDTO(maSach));
                    if (book.getTrangThai().equals("Đã mượn")) {
                        JOptionPane.showMessageDialog(null, "Sách đang được mượn, không thể xóa", "warning", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        int dong = bcBLL.deleteBookCopy(book);
                        if (dong != 1) {
                            JOptionPane.showMessageDialog(null, "Xóa sách không thành công", "del", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Xóa sách thành công", "del", JOptionPane.INFORMATION_MESSAGE);
                            b.setSoLuong(b.getSoLuong() - 1);
                            bBLL.updateBook(b);
                            dispose();
                        }
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn sách cần xóa", "warning", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
