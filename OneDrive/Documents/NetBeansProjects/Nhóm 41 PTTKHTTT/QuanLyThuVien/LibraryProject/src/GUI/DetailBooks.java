/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DAL.CartUserDAO;
import DAL.CardDAO;
import DTO.CartUserDTO;
import DTO.CardDTO;
import GUI.*;
import DAL.BookCopyDAO;
import DAL.BookDAO;
import DAL.CT_PhieuMuonDAO;
import DAL.PhieuMuonDAO;
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
import DAL.ReaderDAO;
import DTO.CT_PhieuMuonDTO;
import DTO.PhieuMuonDTO;
import DTO.ReaderDTO;

/**
 *
 * @author ADMIN
 */
public class DetailBooks extends JFrame {

    public JLabel jLabel_numBookCopy;
    public JTable jTable_detailBookCopy;
    public JScrollPane jScrollPane_tableBookCopy;
    public DefaultTableModel tblModel;
    public JPopupMenu jPopupMenu_book;
    public JMenuItem jMenu_bookAdd;
    public String maDS;
    public String maTheMuon = null;
    public SearchUserGUI sg;

    public DetailBooks(String maDS, String tenDN, SearchUserGUI sg) {
        this.maDS = maDS;
        this.sg = sg;
        System.out.println(maDS + " - " + tenDN);
        ReaderDTO dg = ReaderDAO.getInstance().selectById(tenDN);
        if (dg.getTrangThai() == 1) {
            CardDTO tm = CardDAO.getInstance().selectByIdMaDG(new CardDTO(tenDN));
            this.maTheMuon = tm.getMaTheMuon();
        }
        this.init();
    }

    public void init() {
        jLabel_numBookCopy = new JLabel();
        jPopupMenu_book = new JPopupMenu();
        jMenu_bookAdd = new JMenuItem("Thêm sách vào giỏ");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(new Dimension(704, 425));
        getContentPane().setBackground(Color.white);
        setTitle("Thông tin chi tiết các bản copy");
        setLocationRelativeTo(null);
        this.setLayout(null);

        jLabel_numBookCopy.setFont(new Font("Roboto", 0, 20));
        jLabel_numBookCopy.setBounds(20, 20, 300, 40);
        this.add(jLabel_numBookCopy);

        jTable_detailBookCopy = new JTable();
        jScrollPane_tableBookCopy = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã sách", "Tình trạng", "Hình thức", "Giá mượn"};
        tblModel.setColumnIdentifiers(header);
        jTable_detailBookCopy.setModel(tblModel);
        jTable_detailBookCopy.getTableHeader().setFont(new Font("Roboto", 1, 20));
        jTable_detailBookCopy.getTableHeader().setBackground(new Color(187, 222, 251));
        jTable_detailBookCopy.setDefaultEditor(Object.class, null);
        jScrollPane_tableBookCopy.setViewportView(jTable_detailBookCopy);
        ArrayList<BookCopyDTO> listSachCopy = BookCopyDAO.getInstance().selectByCondition("AND S.MADS = N'" + this.maDS + "' AND (S.TrangThai = N'Nguyên vẹn' OR S.TrangThai = N'Hư hỏng nhẹ')");
        for (BookCopyDTO s : listSachCopy) {
            tblModel.addRow(new Object[]{s.getMaSach(), s.getTinhTrang(), s.getLoaiSach(), s.getGiaMuon()});
        }

        jTable_detailBookCopy.setFont(new Font("Roboto", 0, 18));
        jTable_detailBookCopy.setRowHeight(50);
        jScrollPane_tableBookCopy.setBounds(20, 80, 650, 250);
        this.add(jScrollPane_tableBookCopy);

        jMenu_bookAdd.setBackground(Color.white);
        jMenu_bookAdd.setFont(new Font("Roboto", 0, 18));
        jMenu_bookAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jMenuBookAddActionPerformed(evt);
            }
        });
        jPopupMenu_book.add(jMenu_bookAdd);
        jTable_detailBookCopy.setComponentPopupMenu(jPopupMenu_book);

        jLabel_numBookCopy.setText("Tổng số lượng bản sách: " + jTable_detailBookCopy.getRowCount());

        this.setVisible(true);
    }

    public void jMenuBookAddActionPerformed(ActionEvent evt) {
        if (maTheMuon != null) {
            int num = CartUserDAO.getInstance().selectByCondition("MaTheMuon = '" + maTheMuon + "'").size() + 1;
            System.out.println("tong số sách trong giỏ" + CartUserDAO.getInstance().selectAll().size());

            int selecRow = jTable_detailBookCopy.getSelectedRow();
            String maSach = (String) jTable_detailBookCopy.getValueAt(selecRow, 0);
            String tinhTrang = (String) jTable_detailBookCopy.getValueAt(selecRow, 1);
            String loai = (String) jTable_detailBookCopy.getValueAt(selecRow, 2);
            int giaMuon = (int) jTable_detailBookCopy.getValueAt(selecRow, 3);
            System.out.println("Tạo chi tiết mới: " + new BookCopyDTO(maSach, maDS, loai, giaMuon, tinhTrang).toString() + "mã thẻ: " + maTheMuon);

            boolean ktraTonTai = false;

            ArrayList<CartUserDTO> card = CartUserDAO.getInstance().selectByCondition("MaTheMuon = '" + maTheMuon + "'");
            for (CartUserDTO c : card) {
                if (c.getMaTheMuon().equals(maTheMuon)) {
                    System.out.println("thẻ mượn khi ấn thêm vào giỏ: " + c.getMaTheMuon());
                    if (maSach.equals(c.getMaSach())) {
                        System.out.println("Đã có sách này trong giỏ");
                        ktraTonTai = true;
                        break;
                    }
                }
            }
            if (ktraTonTai) {
                JOptionPane.showMessageDialog(null, "Sách đã có trong giỏ hàng của bạn. Vui lòng chọn sách khác!", "info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (num <= 5) {
                    if (tinhTrang.equals("Hiện có")) {
                        if (loai.equals("Đọc tại chỗ")) {
                            JOptionPane.showMessageDialog(null, "Sách chỉ được sử dụng tại thư viện!", "error", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            BookDTO book = BookDAO.getInstance().selectById(new BookDTO(maDS));
                            BookCopyDTO books = BookCopyDAO.getInstance().selectById(new BookCopyDTO(maSach));
                            CartUserDTO sach = new CartUserDTO(maTheMuon, maSach, book.getTenSach(), (int) books.getGiaMuon());
                            int dong = CartUserDAO.getInstance().insert(sach);
                            dispose();
                            sg.loadDataforTableCard(CartUserDAO.getInstance().selectByCondition("MaTheMuon = '" + maTheMuon + "'"));
                            if (dong != 0) {
                                JOptionPane.showMessageDialog(null, "Thêm vào giỏ thành công!", "info", JOptionPane.INFORMATION_MESSAGE);
                            }

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Sách không có sẵn.", "warning", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Không được mượn quá 5 cuốn.", "warning", JOptionPane.INFORMATION_MESSAGE);
                }

            }

        } else {
            JOptionPane.showMessageDialog(null, "Mã thẻ chưa được duyệt.", "warning", JOptionPane.INFORMATION_MESSAGE);

        }
    }
}
