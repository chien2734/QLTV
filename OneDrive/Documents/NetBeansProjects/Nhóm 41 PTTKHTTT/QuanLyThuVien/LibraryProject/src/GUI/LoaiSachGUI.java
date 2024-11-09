/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BLL.BookBLL;
import DTO.BookDTO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import BLL.LoaiSachBUS;
import DTO.NXBDTO;
import DTO.TheLoaiDTO;


/**
 *
 * @author ADMIN
 */
public class LoaiSachGUI extends JPanel {

    public JLabel jLabel_tenTheLoai;
    public JLabel jLabel_tenNXB;

    public JTextField jTextField_tenTheLoai;
    public JTextField jTextField_tenNXB;

    public DefaultTableModel tblModel_theLoai;
    public JTable jTable_theLoai;
    public JScrollPane jScrollPane_tableTheLoai;

    public DefaultTableModel tblModel_NXB;
    public JTable jTable_NXB;
    public JScrollPane jScrollPane_tableNXB;

    public JButton jButton_addTheLoai;
    public JButton jButton_addNXB;
    public JButton jButton_searchTL;
    public LoaiSachBUS lsBLL = new LoaiSachBUS();
    public BookBLL iuBLL = new BookBLL();
    public JButton jButton_searchNXB;

    public int numberTheLoai;
    public int numberNXB ;
    
    public JPanel jPanel_info;

    public JPopupMenu jPopupMenu_TL;
    public JPopupMenu jPopupMenu_NXB;
    public JMenuItem jMenuItem_TLdel;
    public JMenuItem jMenuItem_NXBdel;

    public LoaiSachGUI() {
        numberTheLoai = lsBLL.getTheLoaiAll().size();
        numberNXB = lsBLL.getNXBAll().size();
        this.init();
    }

    public void init() {

        jLabel_tenTheLoai = new JLabel("Tên loại");
        jLabel_tenNXB = new JLabel("Tên NXB");

        jTextField_tenTheLoai = new JTextField(200);
        jTextField_tenNXB = new JTextField(200);

        jButton_addTheLoai = new JButton("Thêm thể loại");
        jButton_addNXB = new JButton("Thêm NXB");
        jButton_searchTL = new JButton("Tìm kiếm TL");
        jButton_searchNXB = new JButton("Tìm kiếm NXB");

        jTable_theLoai = new JTable();
        jScrollPane_tableTheLoai = new JScrollPane();

        jTable_NXB = new JTable();
        jScrollPane_tableNXB = new JScrollPane();

        jPopupMenu_TL = new JPopupMenu();
        jPopupMenu_NXB = new JPopupMenu();

        setBackground(Color.WHITE);
        setSize(new Dimension(this.getMaximumSize().width, this.getMaximumSize().height));
        setLayout(null);

        jPanel_info = new JPanel();
        jPanel_info.setBackground(new Color(213, 226, 234));
        jPanel_info.setBorder(BorderFactory.createTitledBorder(null, "Thêm sách", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Roboto", 1, 18), new Color(255, 255, 255)));
        jPanel_info.setLayout(null);

        tblModel_theLoai = new DefaultTableModel();
        String[] header = new String[]{"Mã loại", "Tên thể loại"};
        tblModel_theLoai.setColumnIdentifiers(header);
        jTable_theLoai.setModel(tblModel_theLoai);
        jTable_theLoai.getTableHeader().setFont(new Font("Roboto", 1, 20));
        jTable_theLoai.getTableHeader().setBackground(new Color(187, 222, 251));
        jTable_theLoai.getColumnModel().getColumn(1).setPreferredWidth(300);
        jTable_theLoai.setDefaultEditor(Object.class, null);
        jScrollPane_tableTheLoai.setViewportView(jTable_theLoai);

        ArrayList<TheLoaiDTO> listTheLoai = lsBLL.getTheLoaiAll();
        for (TheLoaiDTO s : listTheLoai) {
            tblModel_theLoai.addRow(new Object[]{s.getMaLoai(), s.getTenLoai()});
        }

        jTable_theLoai.setFont(new Font("Roboto", 0, 18));
        //setRowHeight: chỉnh độ rộng của từng dòng trong bảng
        jTable_theLoai.setRowHeight(50);
        jScrollPane_tableTheLoai.setBounds(60, 50, 600, 600);
        this.add(jScrollPane_tableTheLoai);

        tblModel_NXB = new DefaultTableModel();
        String[] headerNXB = new String[]{"Mã NXB", "Tên NXB"};
        tblModel_NXB.setColumnIdentifiers(headerNXB);
        jTable_NXB.setModel(tblModel_NXB);
        jTable_NXB.getTableHeader().setFont(new Font("Roboto", 1, 20));
        jTable_NXB.getTableHeader().setBackground(new Color(187, 222, 251));
        jTable_NXB.getColumnModel().getColumn(1).setPreferredWidth(300);
        jTable_NXB.setDefaultEditor(Object.class, null);
        jScrollPane_tableNXB.setViewportView(jTable_NXB);

        ArrayList<NXBDTO> listNXB = lsBLL.getNXBAll();
        for (NXBDTO s : listNXB) {
            tblModel_NXB.addRow(new Object[]{s.getMaNXB(), s.getTenNXB()});
        }

        jTable_NXB.setFont(new Font("Roboto", 0, 18));
        //setRowHeight: chỉnh độ rộng của từng dòng trong bảng
        jTable_NXB.setRowHeight(50);
        jScrollPane_tableNXB.setBounds(900, 50, 600, 600);
        this.add(jScrollPane_tableNXB);

        jLabel_tenTheLoai.setFont(new Font("Roboto", 0, 18));
        jPanel_info.add(jLabel_tenTheLoai);
        jLabel_tenTheLoai.setBounds(50, 60, 100, 40);

        jTextField_tenTheLoai.setFont(new Font("Roboto", 0, 18));
        jPanel_info.add(jTextField_tenTheLoai);
        jTextField_tenTheLoai.setBounds(180, 55, 300, 40);

        jButton_addTheLoai.setBackground(new Color(187, 222, 251));
        jButton_addTheLoai.setFont(new Font("Roboto", 1, 18));
        jPanel_info.add(jButton_addTheLoai);
        jButton_addTheLoai.setBounds(300, 160, 200, 40);
        jButton_addTheLoai.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addTheLoai();
            }
        });

        jButton_searchTL.setBackground(new Color(187, 222, 251));
        jButton_searchTL.setFont(new Font("Roboto", 1, 18));
        jPanel_info.add(jButton_searchTL);
        jButton_searchTL.setBounds(20, 160, 200, 40);
        jButton_searchTL.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                searchTL();
            }
        });

        jLabel_tenNXB.setFont(new Font("Roboto", 0, 18));
        jPanel_info.add(jLabel_tenNXB);
        jLabel_tenNXB.setBounds(870, 60, 100, 40);

        jTextField_tenNXB.setFont(new Font("Roboto", 0, 18));
        jPanel_info.add(jTextField_tenNXB);
        jTextField_tenNXB.setBounds(1000, 55, 300, 40);

        jButton_addNXB.setBackground(new Color(187, 222, 251));
        jButton_addNXB.setFont(new Font("Roboto", 1, 18));
        jPanel_info.add(jButton_addNXB);
        jButton_addNXB.setBounds(1200, 160, 150, 40);
        jButton_addNXB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addNXB();
            }
        });

        jButton_searchNXB.setBackground(new Color(187, 222, 251));
        jButton_searchNXB.setFont(new Font("Roboto", 1, 18));
        jPanel_info.add(jButton_searchNXB);
        jButton_searchNXB.setBounds(1000, 160, 170, 40);
        jButton_searchNXB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                searchNXB();
            }
        });

        jMenuItem_TLdel = new JMenuItem("Xoá thể loại");
        jMenuItem_TLdel.setBackground(Color.white);
        jMenuItem_TLdel.setFont(new Font("Roboto", 0, 18));
        jMenuItem_TLdel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deleteTL();
            }
        });

        jMenuItem_NXBdel = new JMenuItem("Xóa NXB");
        jMenuItem_NXBdel.setBackground(Color.white);
        jMenuItem_NXBdel.setFont(new Font("Roboto", 0, 18));
        jMenuItem_NXBdel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deleteNXB();
            }
        });

        jPopupMenu_TL.add(jMenuItem_TLdel);
        jTable_theLoai.setComponentPopupMenu(jPopupMenu_TL);
        jPopupMenu_NXB.add(jMenuItem_NXBdel);
        jTable_NXB.setComponentPopupMenu(jPopupMenu_NXB);

        this.add(jPanel_info);
        jPanel_info.setBounds(0, 700, this.getMaximumSize().width, this.getMaximumSize().height);
    }

    public void addTheLoai() {
        String nameTheLoai = jTextField_tenTheLoai.getText();
        TheLoaiDTO t = new TheLoaiDTO(nameTheLoai);
        TheLoaiDTO theLoai = lsBLL.getTheLoaiById(t);
        boolean check = kiemTraBieuThuc(nameTheLoai);
        if (theLoai == null) {
            if (check) {
                numberTheLoai++;
                String maTL = "TL00" + numberTheLoai;
                t.setMaLoai(maTL);
                t.setTrangThai(1);
                int dong = lsBLL.insertTheLoai(t);
                JOptionPane.showMessageDialog(null, "Thêm thể loại thành công", "add", JOptionPane.INFORMATION_MESSAGE);
                jTextField_tenTheLoai.setText("");
                loadDataforTable(lsBLL.getTheLoaiAll());
            } else {
                JOptionPane.showMessageDialog(null, "Thể loại không hợp lệ", "error", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Thể loại đã tồn tại", "error", JOptionPane.INFORMATION_MESSAGE);
            jTextField_tenTheLoai.setText("");
        }
    }

    public boolean kiemTraBieuThuc(String s) {
        String checkName = "[^0-9]{2,}";
        Pattern pattern = Pattern.compile(checkName);
        Matcher matcherName = pattern.matcher(s);
        return matcherName.matches();
    }

    public void addNXB() {
        String nameNXB = jTextField_tenNXB.getText();
        NXBDTO t = new NXBDTO(nameNXB);
        NXBDTO nxb = lsBLL.getNXBById(t);
        boolean check = kiemTraBieuThuc(nameNXB);
        if (nxb == null) {
            if (check) {
                numberNXB++;
                String maNXB = "";
                if (numberNXB < 10) {
                    maNXB = "NXB000" + numberNXB;
                } else {
                    maNXB = "NXB00" + numberNXB;
                }
                t.setMaNXB(maNXB);
                t.setTrangThai(1);
                int dong = lsBLL.insertNXB(t);
                JOptionPane.showMessageDialog(null, "Thêm NXB thành công", "add", JOptionPane.INFORMATION_MESSAGE);
                jTextField_tenTheLoai.setText("");
                loadDataforTableNXB(lsBLL.getNXBAll());
            } else {
                JOptionPane.showMessageDialog(null, "NXB không hợp lệ", "error", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "NXB đã tồn tại", "error", JOptionPane.INFORMATION_MESSAGE);
            jTextField_tenNXB.setText("");
        }
    }

    public void searchTL() {
        String nameTheLoai = jTextField_tenTheLoai.getText();
        if (nameTheLoai.equals("")) {
            int ques = JOptionPane.showConfirmDialog(null, "Không có dữ liệu để hiển thị. Bạn muốn hiển thị toàn bộ?", "error", JOptionPane.YES_NO_OPTION);
            if (ques == JOptionPane.YES_OPTION) {
                ArrayList<TheLoaiDTO> listTimKiemTL = lsBLL.getTheLoaiAll();
                loadDataforTable(listTimKiemTL);
            } else {
                JOptionPane.showMessageDialog(null, "Yêu cầu nhập dữ liệu để tìm kiếm", "info", JOptionPane.INFORMATION_MESSAGE);
            }

        } else {
            String sql = "";
            if (!nameTheLoai.equals("")) {
                sql += " TrangThai = 1 AND TenLoai LIKE N'%" + nameTheLoai + "%'";
            }
            ArrayList<TheLoaiDTO> listTimKiemTL = lsBLL.getTheLoaiByCondition(sql);
            loadDataforTable(listTimKiemTL);
        }

    }

    public void loadDataforTable(ArrayList<TheLoaiDTO> list) {
        try {
            tblModel_theLoai.setRowCount(0);
            for (TheLoaiDTO s : list) {
                tblModel_theLoai.addRow(new Object[]{s.getMaLoai(), s.getTenLoai()});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchNXB() {
        String nameNXB = jTextField_tenNXB.getText();
        if (nameNXB.equals("")) {
            int ques = JOptionPane.showConfirmDialog(null, "Không có dữ liệu để hiển thị. Bạn muốn hiển thị toàn bộ?", "error", JOptionPane.YES_NO_OPTION);
            if (ques == JOptionPane.YES_OPTION) {
                ArrayList<NXBDTO> listTimKiemNXB = lsBLL.getNXBAll();
                loadDataforTableNXB(listTimKiemNXB);
            } else {
                JOptionPane.showMessageDialog(null, "Yêu cầu nhập dữ liệu để tìm kiếm", "info", JOptionPane.INFORMATION_MESSAGE);
            }

        } else {
            String sql = "";
            if (!nameNXB.equals("")) {
                sql += " TrangThai = 1 AND TenNXB LIKE N'%" + nameNXB + "%'";
            }
            ArrayList<NXBDTO> listTimKiemNXB = lsBLL.getNXBByCondition(sql);
            loadDataforTableNXB(listTimKiemNXB);
        }

    }

    public void loadDataforTableNXB(ArrayList<NXBDTO> list) {
        try {
            tblModel_NXB.setRowCount(0);
            for (NXBDTO s : list) {
                tblModel_NXB.addRow(new Object[]{s.getMaNXB(), s.getTenNXB()});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTL() {
        int selecR = jTable_theLoai.getSelectedRow();
        if (selecR != -1) {
            String maTheLoai = (String) jTable_theLoai.getValueAt(selecR, 1);
            ArrayList<BookDTO> listBook = iuBLL.getBookAll();
            int count = 0;
            for (BookDTO book : listBook) {
                if (book.getTenLoai().equals(maTheLoai)) {
                    JOptionPane.showMessageDialog(null, "Thể loại đang được sử dụng.", "error", JOptionPane.INFORMATION_MESSAGE);
                    break;
                } else {
                    count++;
                }
            }
            if (count == listBook.size()) {
                System.out.println(maTheLoai);
                lsBLL.deleteTheLoai(new TheLoaiDTO(maTheLoai));
                JOptionPane.showMessageDialog(null, "Xóa thành công.", "error", JOptionPane.INFORMATION_MESSAGE);
                loadDataforTable(lsBLL.getTheLoaiAll());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn thể loại để xóa", "error", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void deleteNXB() {
        int selecR = jTable_NXB.getSelectedRow();
        if (selecR != -1) {
            String maNXB = (String) jTable_NXB.getValueAt(selecR, 1);
            ArrayList<BookDTO> listBook = iuBLL.getBookAll();
            int count = 0;
            for (BookDTO book : listBook) {
                if (book.getnxb().equals(maNXB)) {
                    JOptionPane.showMessageDialog(null, "NXB đang được sử dụng.", "error", JOptionPane.INFORMATION_MESSAGE);
                    break;
                } else {
                    count++;
                }
            }
            if (count == listBook.size()) {
                lsBLL.deleteNXB(new NXBDTO(maNXB));
                JOptionPane.showMessageDialog(null, "Xóa thành công.", "error", JOptionPane.INFORMATION_MESSAGE);
                loadDataforTableNXB(lsBLL.getNXBAll());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn NXB để xóa", "error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}